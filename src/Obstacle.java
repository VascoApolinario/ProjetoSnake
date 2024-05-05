import FigurasGeo.Ponto;

public class Obstacle extends Objeto {
    private boolean estado;

    public Obstacle(String tipo, String coordenadagrid) {


    }

    @Override
    void update() {
<<<<<<< Updated upstream
=======
        if (dinamico) {
            this.rotate(this.degree);
        }
    }

    @Override
    Poligono format(String formato) {
        String[] parts = formato.split(" ", 2);
        Class<?>  cl = null;
        try {
            cl = Class.forName("FigurasGeo." + parts[0]);
            System.out.println(parts[1]);
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
            return (Poligono) constructor.newInstance(parts[1]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
>>>>>>> Stashed changes

    }

    @Override
    void format(String formato) {

    }

    @Override
    void spawn(Ponto p) {

    }

    @Override
    void move() {

    }

    @Override
    void rotate() {

    }
}