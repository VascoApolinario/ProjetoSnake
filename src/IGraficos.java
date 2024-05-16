import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import FigurasGeo.Segmento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

/**
 * Interface que é responsável pela GUI do jogo. Incluí métodos para desenhar os vários elementos que compõem o jogo.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */
public interface IGraficos {
    /**
     * Desenha o painel do jogo.
     * @param width a largura do painel
     * @param height a altura do painel
     * @param inputHandler o manipulador de entrada do jogo
     */
    void drawPanel(int width, int height, InputHandler inputHandler);
    /**
     * Desenha a cobra no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawSnake(Graphics g);
    /**
     * Desenha os obstáculos no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawObstacles(Graphics g);
    /**
     * Desenha a comida no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawFood(Graphics g);
    /**
     * Desenha a comida numa célula específica do jogo.
     * @param g o objeto Graphics usado para desenhar
     * @param c a célula onde a comida está localizada
     * @param p o ponto onde a comida está localizada
     */
    void drawFood(Graphics g,Cell c,Ponto p);
    /**
     * Desenha a grade do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawGrid(Graphics g);
    /**
     * Desenha a pontuação do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawScore(Graphics g);
    /**
     * Desenha a direção da cobra no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawSnakeDirection(Graphics g);
    /**
     * Desenha a tela de fim de jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawGameOver(Graphics g);
    /**
     * Desenha a borda do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawBorder(Graphics g);

    /**
     * Repinta o painel.
     */
    void repaint();
    /**
     * Atualiza o fundo do jogo.
     * @param bg o novo fundo do jogo
     */
    void setBG(Background bg);

}


class Grafica extends JPanel implements IGraficos {
    private Background bg;
    private boolean renderFill;

    public Grafica(int width, int height,Background bg, InputHandler inputHandler, boolean renderFill) {
        this.bg = bg;
        this.renderFill = renderFill;
        drawPanel(width,height,inputHandler);
    }


    public void drawPanel(int width, int height,InputHandler inputHandler) {
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(255,255,255));
        this.setFocusable(true);
        this.addKeyListener(inputHandler);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!bg.getGameOver()) {
            //drawGrid(g);
            drawBorder(g);
            drawFood(g);
            drawSnake(g);
            drawObstacles(g);
            drawScore(g);
            drawSnakeDirection(g);
        }
        else
            drawGameOver(g);

    }


    @Override
    public void drawSnake(Graphics g) {
        Snake snake = bg.getSnake();
        int headSize = (int)snake.getHead().getSide();
        int x = (int)snake.getHead().getDownLeft().getX();
        int y = (int)snake.getHead().getDownLeft().getY();
        if(renderFill){
            g.setColor(new Color(19, 53, 2, 255));
            g.fillRect(x,y,headSize,headSize);
        }
        g.setColor(new Color(42, 128, 2, 255));
        g.drawRect(x,y,headSize,headSize);
        if(!snake.getTail().isEmpty()) {
            for (Quadrado t : snake.getTail()) {
                if(renderFill) {
                    g.setColor(new Color(7, 92, 0, 255));
                    g.fillRect((int) t.getDownLeft().getX(), (int) t.getDownLeft().getY(), (int) t.getSide(), (int) t.getSide());
                }
                g.setColor(new Color(24, 197, 15, 255));
                g.drawRect((int) t.getDownLeft().getX(), (int) t.getDownLeft().getY(), (int) t.getSide(), (int) t.getSide());
            }
        }

    }

    @Override
    public void drawObstacles(Graphics g) {
        ArrayList<Obstacle> obstacles = bg.getObstaculos();
        for (Obstacle o : obstacles) {
            Poligono po = o.getPoligono();
            if (renderFill) {
                g.setColor(new Color(0, 149, 255, 255));
                g.fillPolygon(po.getxv(), po.getyv(), po.getPontos().length);
            }
            g.setColor(new Color(1, 23, 255, 255));
            g.drawPolygon(po.getxv(),po.getyv(),po.getPontos().length);
        }
    }

    @Override
    public void drawFood(Graphics g) {
        ArrayList<Food> food = bg.getComida();
        for(Food f : food)
        {

            if(f instanceof CircleFood)
            {
                int radius = (int) ((CircleFood) f).getCirculo().getRadius();
                int x = (int) (((CircleFood) f).getCirculo().getCenter().getX()-radius);
                int y = (int) (((CircleFood) f).getCirculo().getCenter().getY()-radius);
                if(renderFill)
                {
                    g.setColor(new Color(246, 38, 38));
                    g.fillOval(x,y,2*radius,2*radius);
                }
                g.setColor(new Color(227, 8, 8));
                g.drawOval(x,y,2*radius,2*radius);
            }
            else if (f instanceof SquareFood) {
                int side = (int) ((SquareFood) f).getQuadrado().getSide();
                int x = (int) ((SquareFood) f).getQuadrado().getDownLeft().getX();
                int y = (int) ((SquareFood) f).getQuadrado().getDownLeft().getY();
                if(renderFill) {
                    g.setColor(new Color(246, 38, 38));
                    g.fillRect(x,y,side,side);
                }
                g.setColor(new Color(227, 8, 8));
                g.drawRect(x,y,side,side);

            }
        }
    }

    @Override
    public void drawFood(Graphics g, Cell c, Ponto p) {

    }


    @Override
    public void drawGrid(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < grid.getCells().length-1; x++) {
            for (int y = 0; y < grid.getCells()[x].length; y++) {
                Ponto ponto = grid.getCells()[x][y].getPontos()[0];
                int size = (int) grid.getCells()[x][y].getSide();
                g.setColor(new Color(0,0,0));
                g.drawRect((int)ponto.getX(),(int)ponto.getY(),size,size);
                if(x == 0 || y == 0 || x == grid.getCells().length-2 || y == grid.getCells()[x].length-1) {
                    g.setColor(new Color(43, 26, 2));
                    g.fillRect((int) ponto.getX(), (int) ponto.getY(), size, size);
                }

                if(!grid.getCells()[x][y].isEmpty()) {
                    g.setColor(new Color(172, 23, 177));
                    g.fillRect((int) ponto.getX(), (int) ponto.getY(), size, size);
                }


            }
        }
    }

    @Override
    public void drawScore(Graphics g) {
        int score = this.bg.getPlayer().getScore();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New",Font.PLAIN,getWidth()/30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Pontos: " +score + " ",this.getWidth()-metrics.stringWidth("Pontos: " +score + " "),this.getHeight()-g.getFont().getSize()/3);
    }


    @Override
    public void drawSnakeDirection(Graphics g) {
        int dir = this.bg.getSnake().getDirection();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New",Font.PLAIN,getWidth()/30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Dir H: " + dir + " ",0,this.getHeight()-g.getFont().getSize()/3);

    }

    @Override
    public void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Cascadia Code",Font.BOLD,getWidth()/10));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over" , (getWidth() - metrics.stringWidth("Game Over"))/2,getHeight()/2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Cascadia Code",Font.PLAIN,getWidth()/20));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + this.bg.getPlayer().getScore() , (getWidth() - metrics1.stringWidth("Score: " + this.bg.getPlayer().getScore()))/2, (int) (getHeight()/1.5));
    }

    @Override
    public void drawBorder(Graphics g) {
        Grid grid = this.bg.getGrid();
        g.setColor(Color.black);
        int x1 = (int) grid.getCells()[0][0].getTopRight().getX();
        int y1 = (int) grid.getCells()[0][0].getTopRight().getY();
        int x2 = (int) grid.getCells()[0][grid.getCells()[0].length-1].getDownLeft().getX();
        int y2 = y1;
        g.drawLine(x1,y1,x2,y2);
        x1 = x2;
        y2 = (int) grid.getCells()[grid.getCells().length-2][grid.getCells()[0].length-1].getDownLeft().getY();
        g.drawLine(x1,y1,x2,y2);
        y1 = y2;
        x2 = (int) grid.getCells()[0][0].getTopRight().getX();
        g.drawLine(x1,y1,x2,y2);
        x1 = x2;
        y2 = (int) grid.getCells()[0][0].getTopRight().getY();
        g.drawLine(x1,y1,x2,y2);
    }

    @Override
    public void setBG(Background bg) {
        this.bg = bg;
    }

}


/**
 * Classe encarregue na interface textual do jogo. Podendo ser rasterizada com contornos ou com preenchimento.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */
class Textual extends JPanel implements IGraficos{
    private Background bg;
    private boolean renderFill;

    /**
     * Construtor da classe Textual
     * @param width
     * @param height
     * @param bg
     * @param inputHandler
     * @param renderFill
     */
    public Textual(int width, int height,Background bg,InputHandler inputHandler, boolean renderFill)
    {
        this.bg = bg;
        this.renderFill = renderFill;
        drawPanel(width,height,inputHandler);
    }
    /**
     * Método para desenhar os componentes do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!bg.getGameOver()){
                drawGrid(g);
                drawScore(g);
                drawSnakeDirection(g);

        }
        else
            drawGameOver(g);
    }
    /**
     * Desenha o painel do jogo.
     * @param width a largura do painel
     * @param height a altura do painel
     * @param inputHandler o manipulador de entrada do jogo
     */
    @Override
    public void drawPanel(int width, int height, InputHandler inputHandler) {
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(255, 255, 255));
        this.setFocusable(true);
        this.addKeyListener(inputHandler);
    }

    /**
     * Desenha a cobra no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawSnake(Graphics g) {

    }
    /**
     * Desenha os obstáculos no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawObstacles(Graphics g) {

    }
    /**
     * Desenha a comida no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawFood(Graphics g) {

    }

    /**
     * Desenha a comida numa célula específica do jogo.
     * @param g o objeto Graphics usado para desenhar
     * @param c a célula onde a comida está localizada
     * @param p o ponto onde a comida está localizada
     */
    @Override
    public void drawFood(Graphics g,Cell c, Ponto p) {
        for(Food f : bg.getComida())
        {
            if(f instanceof CircleFood)
            {
                if(c.getCentroide().equals(((CircleFood) f).getCirculo().getCenter())) {
                    if (p.isInside(((CircleFood) f).getCirculo()))
                        g.drawString("F", (int) p.getX(), (int) p.getY());
                    else
                        g.drawString(".", (int) p.getX(), (int) p.getY());
                }
            }
            else if (f instanceof SquareFood) {
                if(c.getCentroide().equals(((SquareFood) f).getQuadrado().getCentroide())) {
                    if (p.isInside(((SquareFood) f).getQuadrado()))
                        g.drawString("F", (int) p.getX(), (int) p.getY());
                    else
                        g.drawString(".", (int) p.getX(), (int) p.getY());
                }
            }
        }

    }

    /**
     * Função responsavel por escolher entre o metodo de rasterização com preenchimento de obstaculos ou apenas com borders
     * @param g
     */
    @Override
    public void drawGrid(Graphics g) {
        if(!this.renderFill)
        {
            drawGridBorder(g);
        }
        else
        {
            drawGridFill(g);
        }
    }

    /**
     * Método para desenhar a grade do jogo com bordas.
     * @param g o objeto Graphics usado para desenhar
     */
    public void drawGridBorder(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < this.getWidth(); x+=10) {
            for (int y = 0; y < this.getHeight()- grid.getSquaresize(); y+=10) {
                Ponto p = new Ponto(x,y);
                Content conteudo = getContentFromPointArea(p);

                g.setColor(Color.BLACK);
                g.setFont(new Font("Cascadia Code",Font.PLAIN,10));
                switch (conteudo) {
                    case HEAD:
                        g.drawString("H",x,y);
                        break;
                    case TAIL:
                        g.drawString("T",x,y);
                        break;
                    case OBSTACLE:
                        g.drawString("O",x,y);
                        break;
                    case FOOD:
                        g.drawString("F",x,y);
                        break;
                    case EMPTY, DINAMICO:
                        g.drawString(".",x,y);
                        break;
                    case BORDER:
                        g.drawString("+",x,y);
                        break;
                    case EATING:
                        g.drawString("#",x,y);
                        break;
                }

            }
        }
    }

    /**
     * Para um ponto "p" verifica que elemento de jogo se encontra naquela zona
     * @param p
     * @return
     */
    private Content getContentFromPointArea(Ponto p) {
        Quadrado q =  new Quadrado(p, new Ponto(p.getX()+11,p.getY()+11));
        Cell c = bg.getGrid().returnCellFromPoint(p);
        for(Obstacle obstacle : bg.getObstaculos())
        {
            if(obstacle.getPoligono().polygonsIntercept(q))
            {
                return Content.OBSTACLE;
            }
        }
        for(Segmento seg : bg.getSnake().getHead().getArestas())
        {
            if(seg.pertenceSeg(p))
            {
                return Content.HEAD;
            }
        }
        for(Quadrado tail : bg.getSnake().getTail())
        {
            for(Segmento seg : tail.getArestas()) {
                if (seg.pertenceSeg(p))
                    return Content.TAIL;
            }
        }
        for(Food food : bg.getComida())
        {
            if(food instanceof SquareFood)
            {
                SquareFood sf = (SquareFood) food;
                if(q.polygonsIntercept(sf.getQuadrado()))
                    return Content.FOOD;
            }
            else
            {
                CircleFood cf = (CircleFood) food;
                if(cf.getCirculo().collidesWithPolygon(q))
                    return Content.FOOD;
            }
        }
        if(c.getContent() == Content.BORDER)
            return Content.BORDER;

        if(c.getContent() == Content.EATING)
            return Content.EATING;

        return Content.EMPTY;
    }

    /**
     * Método para desenhar a grade do jogo com preenchimento.
     * @param g o objeto Graphics usado para desenhar
     */
    public void drawGridFill(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < this.getWidth(); x+=10) {
            for (int y = 0; y < this.getHeight()- grid.getSquaresize(); y+=10) {
                Ponto p = new Ponto(x,y);
                Cell c = grid.returnCellFromPoint(p);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Cascadia Code",Font.PLAIN,10));
                switch (c.getContent()) {
                    case HEAD:
                        g.drawString("H",x,y);
                        break;
                    case TAIL:
                        g.drawString("T",x,y);
                        break;
                    case OBSTACLE:
                        g.drawString("O",x,y);
                        break;
                    case FOOD:
                        drawFood(g,c,p);
                        break;
                    case EMPTY, DINAMICO:
                        g.drawString(".",x,y);
                        break;
                    case BORDER:
                        g.drawString("+",x,y);
                        break;
                    case EATING:
                        g.drawString("#",x,y);
                        break;
                }

            }
        }
    }
    /**
     * Desenha a pontuação do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawScore(Graphics g) {
        int score = this.bg.getPlayer().getScore();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New",Font.PLAIN,getWidth()/30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Pontos: " +score + " ",this.getWidth()-metrics.stringWidth("Pontos: " +score + " "),this.getHeight()-g.getFont().getSize()/3);
    }

    /**
     * Desenha a direção da cobra no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawSnakeDirection(Graphics g) {
        int dir = this.bg.getSnake().getDirection();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New",Font.PLAIN,getWidth()/30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Dir H: " + dir + " ",0,this.getHeight()-g.getFont().getSize()/3);

    }
    /**
     * Desenha a tela de fim de jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Cascadia Code",Font.BOLD,getWidth()/10));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over" , (getWidth() - metrics.stringWidth("Game Over"))/2,getHeight()/2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Cascadia Code",Font.PLAIN,getWidth()/20));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + this.bg.getPlayer().getScore() , (getWidth() - metrics1.stringWidth("Score: " + this.bg.getPlayer().getScore()))/2, (int) (getHeight()/1.5));
    }
    /**
     * Desenha a borda do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawBorder(Graphics g) {

    }
    /**
     * Atualiza o fundo do jogo.
     * @param bg o novo fundo do jogo
     */
    @Override
    public void setBG(Background bg) {
        this.bg = bg;
    }


}





