
import FigurasGeo.*;

import java.util.ArrayList;

public class Snake extends Objeto {
    private Quadrado head;
    private ArrayList<Quadrado> tail;
    private int direction;
    boolean ate;
    /**
     * Construtor
     */
    public Snake(int headSize, int direction, Ponto spawn)
    {
        Quadrado cabeca = new FigurasGeo.Quadrado(headSize);
        this.direction = direction;
        this.head = (Quadrado) cabeca.moveCentroid((int)spawn.getX(),(int)spawn.getY());
        this.tail = new ArrayList<>();
        this.ate = false;
    }

    public Snake(int headSize, int direction)
    {
        this.head = new FigurasGeo.Quadrado(headSize);
        this.direction = direction;
        this.tail = new ArrayList<>();
        this.ate = false;
    }

    public Quadrado getHead() {
        return head;
    }

    public int getDirection() {
        return direction;
    }

    public ArrayList<Quadrado> getTail() {
        return tail;
    }

    @Override
    void update() {
        this.move();
        this.grow();
        if(collisionWithTail()) {
            System.out.println("GAME OVER!");
            die();
        }
    }

    @Override
    Poligono format(String formato) {
        return null;
    }
    /*
    @Override
    void spawn(Ponto p) {

    }*/

    /**
     * Metodo que faz a cobra mover-se
     */
    @Override
    public void move()
    {
        double x1 = this.head.getDownLeft().getX();
        double y1 = this.head.getDownLeft().getY();
        double x2 = this.head.getTopRight().getX();
        double y2 = this.head.getTopRight().getY();
        if(direction == 0)
            this.head = new Quadrado(new Ponto(x1 + getHead().getSide(),y1),new Ponto(x2+ getHead().getSide(),y2));
        else if (direction == 90) {
            this.head = new Quadrado(new Ponto(x1,y1+-getHead().getSide()),new Ponto(x2,y2-getHead().getSide()));
        }
        else if (direction == 180) {
            this.head = new Quadrado(new Ponto(x1 - getHead().getSide(),y1),new Ponto(x2 - getHead().getSide(),y2));
        }
        else
        {
            this.head = new Quadrado(new Ponto(x1,y1+getHead().getSide()),new Ponto(x2,y2+getHead().getSide()));
        }

        if(ate)
        {
            this.tail.add(new Quadrado(new Ponto(x1,y1),new Ponto(x2,y2)));
            this.ate = false;
        }
        else if(!tail.isEmpty() && !ate)
        {
            this.tail.add(new Quadrado(new Ponto(x1,y1),new Ponto(x2,y2)));
            this.tail.removeFirst();
        }
        /* o movimento da snake vai ser baseado em mover a cabeça para a direção correta (dependendo da direção)
        * ter atenção que a snake não se pode mexer 180 graus, só 90º de cada vez, e seguida
        * remover o primeiro elemento da cauda (o que está mais longe da cabeça)
        * e adicionar um elemento na posição antiga da cabeça,
        * */

    }

    /**
     * Metodo que faz a cobra virar de direção
     */
    @Override
    void rotate(int degrees) {
        if(Math.abs(this.direction% 180 - degrees %180)==90)
            this.direction = degrees;
    }

    @Override
    String tipo() {
        return "";
    }

    /**
     * Metodo que faz a cobra crescer de tamanho
     */
    public void grow()
    {
        this.ate = true;
    }

    /**
     * Metodo que verifica se a cobra consumiu comida. Caso tenha comido ate = 1;
     * @param f -> Comida a ser comida
     */
    public int eat(Food f)
    {

        return 1;     //TODO
    }

    /**
     * Metodo que verifica se a cobra colidiu com o proprio rabo
     * @return
     */
    public boolean collisionWithTail()
    {
        for(Quadrado t : this.tail)
        {
            if (this.head.isInside(t))
                return true;
        }

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
    public boolean checkCollisions(Grid currentGameGrid)
    {
        for(Quadrado t : this.tail)
        {
            if (this.head.isInside(t))
                return true;
        }
        return false;
    }

    /**
     * Metodo que mata a cobra
     */
    public void die()
    {
        this.tail.clear();
        //TODO
    }


}