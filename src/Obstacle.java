import FigurasGeo.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Classe responsável por representar obstaculos.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */

public class Obstacle extends Objeto {
    private boolean dinamico;
    private Poligono poligono;
    private int degree;

    /**
     * Construtor da classe Obstacle.
     * @param formato formato do obstaculo
     * @param dinamico booleano que representa se o obstaculo roda ou não sendo portanto dinamicou não
     * @param degree valor int que reprenta os graus de rotação do obstaculo caso dinamico
     */
    public Obstacle(String formato, boolean dinamico, int degree) {
        this.poligono = this.format(formato);
        this.dinamico = dinamico;
        this.degree = degree;
    }

    /**
     * Construtor da classe Obstacle.
     * @param formato formato do obstaculo
     * @param coordenadas coordenadas para onde criar o obstaculo
     * @param dinamico booleano que representa se o obstaculo roda ou não sendo portanto dinamicou não
     * @param degree valor int que reprenta os graus de rotação do obstaculo caso dinamico
     */
    public Obstacle(String formato,Ponto coordenadas, boolean dinamico, int degree) {
        this(formato,dinamico,degree);
        this.poligono.moveCentroid((int)coordenadas.getX(),(int)coordenadas.getY());
        //spawn(coordenadas);
    }

    /**
     * Metodo que atualiza o obstaculo.
     * @post obstaculo atualizado.
     */
    @Override
    void update() {
        if (dinamico) {
            this.rotate(this.degree);
        }
    }

    /**
     * Metodo que cria um poligono do obstaculo dado um formato
     * @param formato formato do poligono a ser criado
     * @return Poligono criado com o formato especificado.
     */
    @Override
    Poligono format(String formato) {
        String[] parts = formato.split(" ", 2);
        Class<?>  cl = null;
        try {
            cl = Class.forName("FigurasGeo." + parts[0]);
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

    }

    /**
     * Metodo não implementado herdado da classe abstrata Objeto para mover o obstaculo
     * @param g Grid onde o obstaculo se move
     */
    @Override
    void move(Grid g) {

    }

    /**
     * Metodo que efetua uma rotação do poligono do obstaculo
     * @param degrees graus a rodar o poligono
     * @post Poligono efetuou uma rotação de graus (degrees)
     */
    @Override
    void rotate(int degrees) {
        this.poligono = this.poligono.rotate(degrees);
    }

    /**
     * Metodo que retorna o tipo de objeto.
     * @return String "Obstaculo"
     */
    @Override
    public String tipo() {
        return "Obstaculo";
    }

    /**
     * Getter do atributo dinamico
     * @return dinamico
     */
    public boolean isDinamico() {
        return dinamico;
    }

    /**
     * Getter do atributo poligono
     * @return poligono
     */
    public Poligono getPoligono() {
        return poligono;
    }

    /**
     * Getter do atributo degree
     * @return degree
     */
    public int getDegree() {
        return degree;
    }
}

