
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

    public Snake(int headSize, int direction, Cell spawn)
    {
        Quadrado cabeca = new FigurasGeo.Quadrado(headSize);
        this.direction = direction;
        this.head = (Quadrado) cabeca.moveCentroid((int)spawn.getCentroide().getX(),(int)spawn.getCentroide().getY());
        spawn.setEmpty(false);
        spawn.setContent(Content.HEAD);
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
        //this.move();
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
    public void move(Grid g)
    {
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
    public void eat(Food f, Grid grid)
    {
        if(f instanceof SquareFood)
        {
            if(this.head.isInside(((SquareFood) f).getQuadrado())) {
                this.grow();
                f.consumir(grid);
            }
        }
        else if (f instanceof CircleFood) {
            if(this.head.isInside(((CircleFood) f).getCirculo())) {
                this.grow();
                f.consumir(grid);
            }
        }

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