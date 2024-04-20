import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.util.ArrayList;

public class Snake extends Objeto {
    private Quadrado head;
    private ArrayList<Quadrado> tail;
    private int direction;
    /**
     * Construtor
     */
    public Snake(int headSize, int direction, Ponto spawn)
    {
        this.head = new FigurasGeo.Quadrado(headSize);
        this.direction = direction;

        head.moveCentroid((int) spawn.getX(), (int) spawn.getY());
    }

    @Override
    void update() {

    }

    @Override
    void format(String formato) {

    }

    @Override
    void spawn(Ponto p) {

    }

    /**
     * Metodo que faz a cobra mover-se
     */
    @Override
    public void move()
    {
        //TODO
    }

    /**
     * Metodo que faz a cobra virar de direção
     */
    @Override
    void rotate() {

    }

    /**
     * Metodo que faz a cobra crescer de tamanho
     */
    public void grow()
    {
        //TODO
    }

    /**
     * Metodo que verifica se a cobra consumiu comida. Caso tenha comido é chamado o método grow.
     */
    public void eat()
    {
        //TODO
    }

    /**
     * Metodo que verifica se a cobra colidiu com o proprio rabo
     * @return
     */
    public boolean collisionWithTail()
    {
        //TODO
        return false;
    }

    /**
     * Metodo que verifica se a cobra colidiu com um obstáculo
     * @param o
     * @return
     */
    public boolean collisionWithObstacle(Obstacle o)
    {
        //TODO
        return false;
    }

    /**
     * Metodo que verifica todas as colisões.
     */
    public void checkCollisions()
    {
        //TODO
    }

    /**
     * Metodo que mata a cobra
     */
    public void die()
    {
        //TODO
    }


}