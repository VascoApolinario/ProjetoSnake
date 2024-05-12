
import FigurasGeo.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que representa a cobra
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @inv O lado da cabeça da cobra tem de ter o mesmo comprimento do lado da célula da grid
 */

public class Snake extends Objeto {
    private Quadrado head;
    private ArrayList<Quadrado> tail;
    private int direction;
    private boolean ate;
    private Status status;
    boolean increaseScore;
    private boolean rotateDelay;

    /**
     * Construtor da classe Snake
     * @param headSize tamanho do lado da cabeça
     * @param spawn célula onde a cobra irá dar spawn
     */
    public Snake(int headSize, Cell spawn)
    {
        if(headSize != spawn.getSide())
        {
            throw new IllegalArgumentException("Snake: vi");
        }
        Quadrado cabeca = new FigurasGeo.Quadrado(headSize);
        this.direction = getRandomDirection();
        this.head = (Quadrado) cabeca.moveCentroid((int)spawn.getCentroide().getX(),(int)spawn.getCentroide().getY());
        spawn.setEmpty(false);
        spawn.setContent(Content.HEAD);
        this.tail = new ArrayList<Quadrado>();
        this.ate = false;
        this.rotateDelay = false;
        this.status = Status.START;
    }

    /**
     * Construtor da classe Snake (usado nos testes JUnit)
     * @param headSize tamanho do lado da cabeça
     * @param spawn célula onde a cobra irá dar spawn
     * @param direction direção da snake
     */
    public Snake(int headSize, Cell spawn, int direction)
    {
        if(headSize != spawn.getSide())
        {
            throw new IllegalArgumentException("Snake: vi");
        }
        Quadrado cabeca = new FigurasGeo.Quadrado(headSize);
        this.direction = direction;
        this.head = (Quadrado) cabeca.moveCentroid((int)spawn.getCentroide().getX(),(int)spawn.getCentroide().getY());
        spawn.setEmpty(false);
        spawn.setContent(Content.HEAD);
        this.tail = new ArrayList<Quadrado>();
        this.ate = false;
        this.rotateDelay = false;
        this.status = Status.ALIVE;
    }

    /**
     * Método que devolve uma direção aleatória para a snake
     * @return 0, 90, 180 ou 270
     */
    public int getRandomDirection()
    {
        Random rand = new Random();
        int n = rand.nextInt(4);
        if(n == 0)
            return 0;
        else if(n == 1)
            return 90;
        else if(n == 2)
            return 180;
        else
            return 270;
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
        if(collisionWithTail()) {
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
     * Metodo que faz a cobra mover-se e atualiza a grid por onde passa.
     * @param g grid a ser atualizada
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

        if(this.rotateDelay)
            this.rotateDelay = false;

        /* o movimento da snake vai ser baseado em mover a cabeça para a direção correta (dependendo da direção)
        * ter atenção que a snake não se pode mexer 180 graus, só 90º de cada vez, e seguida
        * remover o primeiro elemento da cauda (o que está mais longe da cabeça)
        * e adicionar um elemento na posição antiga da cabeça,
        * */

    }

    /**
     * Metodo que faz a cobra virar de direção
     * @param degrees direção em graus desejada.
     */
    @Override
    void rotate(int degrees) {
        if(!this.rotateDelay) {
            if (Math.abs(this.direction % 180 - degrees % 180) == 90)
                this.direction = degrees;
            this.rotateDelay = true;
        }
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
     * Metodo que verifica se a cobra consumiu comida. Caso tenha chama o metodo grow();
     * @param f -> Comida a ser consumida
     */
    public void eat(Food f, Grid grid)
    {

        if(f instanceof SquareFood)
        {
            if(this.head.isInside(((SquareFood) f).getQuadrado())) {
                this.grow();
                grid.returnCellFromPoint(this.head.getCentroide()).setContent(Content.EATING);
                f.consumir(grid);
                increaseScore = true;
            }
        }
        else if (f instanceof CircleFood) {
            if(this.head.isInside(((CircleFood) f).getCirculo())) {
                this.grow();
                grid.returnCellFromPoint(this.head.getCentroide()).setContent(Content.EATING);
                f.consumir(grid);
                increaseScore = true;
            }
        }

    }

    /**
     * Metodo que verifica se a cobra colidiu com o proprio rabo
     * @return true se colidiu, false se não
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
     * Metodo que verifica todas as colisões.
     */
    public boolean checkCollisions(Grid currentGameGrid)
    {
        if (currentGameGrid.returnCellFromPoint(head.getCentroide()).getContent() == Content.OBSTACLE) {
            return true;
        }

        if (currentGameGrid.returnCellFromPoint(head.getCentroide()).getContent() == Content.BORDER) {
            return true;
        }
        if (!getTail().isEmpty())
        {
            for (Quadrado q : getTail())
            {
               if (currentGameGrid.returnCellFromPoint(q.getCentroide()).getContent() == Content.OBSTACLE) {
                   return true;
               }
            }
        }

        return false;
    }

    /**
     * Metodo que mata a cobra
     */
    public void die()
    {
        this.tail.clear();
        this.status = Status.DEAD;
        //TODO
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}