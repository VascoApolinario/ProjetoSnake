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
<<<<<<< Updated upstream
        //TODO
=======
        g.returnCellFromPoint(this.head.getCentroide()).updateCell(true,Content.EMPTY);
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
        if (this.checkCollisions(g)) {
            System.out.println("Game Over!");
            this.die();
        }
        g.returnCellFromPoint(this.head.getCentroide()).updateCell(false,Content.HEAD);
        if(ate)
        {
            this.tail.add(new Quadrado(new Ponto(x1,y1),new Ponto(x2,y2)));
            g.returnCellFromPoint(this.tail.getLast().getCentroide()).updateCell(false,Content.TAIL);
            this.ate = false;
        }
        else if(!tail.isEmpty() && !ate)
        {
            this.tail.add(new Quadrado(new Ponto(x1,y1),new Ponto(x2,y2)));
            g.returnCellFromPoint(this.tail.getLast().getCentroide()).updateCell(false,Content.TAIL);
            g.returnCellFromPoint(this.tail.getFirst().getCentroide()).updateCell(true,Content.EMPTY);
            this.tail.removeFirst();
        }

        /* o movimento da snake vai ser baseado em mover a cabeça para a direção correta (dependendo da direção)
        * ter atenção que a snake não se pode mexer 180 graus, só 90º de cada vez, e seguida
        * remover o primeiro elemento da cauda (o que está mais longe da cabeça)
        * e adicionar um elemento na posição antiga da cabeça,
        * */

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        //TODO
=======
        /*
        for(Quadrado t : this.tail)
        {
            if (this.head.isInside(t))
                return true;
        }
         */

        System.out.println(currentGameGrid.returnCellFromPoint(head.getCentroide()).getContent());
        if (currentGameGrid.returnCellFromPoint(head.getCentroide()).getContent() == Content.OBSTACLE) {
            return true;
        }
        return false;
>>>>>>> Stashed changes
    }

    /**
     * Metodo que mata a cobra
     */
    public void die()
    {
        //TODO
    }


}