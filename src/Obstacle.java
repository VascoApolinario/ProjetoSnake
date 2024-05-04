import FigurasGeo.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Obstacle extends Objeto {
    private boolean dinamico;
    private Poligono poligono;
    private int degree;

    public Obstacle(String formato, boolean dinamico, int degree) {
        this.poligono = this.format(formato);
        this.dinamico = dinamico;
        this.degree = degree;
    }

    public Obstacle(String formato,Ponto coordenadas, boolean dinamico, int degree) {
        this(formato,dinamico,degree);
        this.poligono.moveCentroid((int)coordenadas.getX(),(int)coordenadas.getY());
        //spawn(coordenadas);
    }

    public Obstacle(Cell[] centroides, boolean dinamico, int degree) {
        this(cellsToStringConstructor(centroides), dinamico,degree);
    }

    private static String cellsToStringConstructor(Cell[] centroides)
    {
        StringBuilder formato = new StringBuilder("Poligono");
        for (Cell centroide : centroides) {
            formato.append(" ");
            formato.append(centroide.toString());
            centroide.setEmpty(false);
            centroide.setContent(Content.OBSTACLE);
        }
        return formato.toString();
    }

    @Override
    void update() {
        if (dinamico) {
            this.rotate(this.degree);
        }
    }

    @Override
    Poligono format(String formato) {
        String[] aos = formato.split("", 2);
        Class<?>  cl = null;
        try {
            cl = Class.forName((aos[0]));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Constructor<?> constructor = null;
        try {
            constructor = cl.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            return (Poligono) constructor.newInstance(aos[1]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
    /*
    @Override
    void spawn(Ponto p) {
        this.poligono.moveCentroid((int)p.getX(),(int)p.getY());
    }*/

    @Override
    void move(Grid g) {

    }

    @Override
    void rotate(int degrees) {
        if(dinamico)
            this.poligono.rotate(degrees);
    }

    @Override
    String tipo() {
        return "Obstaculo";
    }

    public boolean isDinamico() {
        return dinamico;
    }

    public Poligono getPoligono() {
        return poligono;
    }

    public int getDegree() {
        return degree;
    }
}

