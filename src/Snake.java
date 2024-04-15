import java.util.ArrayList;

public class Snake {
    private Quadrado head;
    private ArrayList<Quadrado> tail;
    private int direction;
    /**
     * Construtor
     */
    public Snake(int headSize, int direction)
    {
        //this.head = new Quadrado();
        this.direction = direction;
    }

    /**
     * Metodo que faz a cobra mover-se
     */
    public void move()
    {
        //TODO
    }

    /**
     * Metodo que faz a cobra virar de direção
     */
    public void turn(int degrees)
    {
        //TODO
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