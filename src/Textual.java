import FigurasGeo.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe encarregue na interface textual do jogo. Podendo ser rasterizada com contornos ou com preenchimento.
 *
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */
class Textual extends JPanel implements IGraficos {
    private Background bg;
    private boolean renderFill;

    /**
     * Construtor da classe Textual
     *
     * @param width
     * @param height
     * @param bg
     * @param inputHandler
     * @param renderFill
     */
    public Textual(int width, int height, Background bg, InputHandler inputHandler, boolean renderFill) {
        this.bg = bg;
        this.renderFill = renderFill;
        drawPanel(width, height, inputHandler);
    }

    /**
     * Método para desenhar os componentes do jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!bg.getGameOver()) {
            drawGrid(g);
            drawScore(g);
            drawSnakeDirection(g);

        } else
            drawGameOver(g);
    }

    /**
     * Desenha o painel do jogo.
     *
     * @param width        a largura do painel
     * @param height       a altura do painel
     * @param inputHandler o manipulador de entrada do jogo
     */
    @Override
    public void drawPanel(int width, int height, InputHandler inputHandler) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(255, 255, 255));
        this.setFocusable(true);
        this.addKeyListener(inputHandler);
    }

    /**
     * Desenha a cobra no jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawSnake(Graphics g) {

    }

    /**
     * Desenha os obstáculos no jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawObstacles(Graphics g) {

    }

    /**
     * Desenha a comida no jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawFood(Graphics g) {

    }

    /**
     * Desenha a comida numa célula específica do jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     * @param c a célula onde a comida está localizada
     * @param p o ponto onde a comida está localizada
     */
    @Override
    public void drawFood(Graphics g, Cell c, Ponto p) {
        for (Food f : bg.getComida()) {
            if (f instanceof CircleFood) {
                if (c.getCentroide().equals(((CircleFood) f).getCirculo().getCenter())) {
                    if (p.isInside(((CircleFood) f).getCirculo()))
                        g.drawString("F", (int) p.getX(), (int) p.getY());
                    else
                        g.drawString(".", (int) p.getX(), (int) p.getY());
                }
            } else if (f instanceof SquareFood) {
                if (c.getCentroide().equals(((SquareFood) f).getQuadrado().getCentroide())) {
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
     *
     * @param g
     */
    @Override
    public void drawGrid(Graphics g) {
        if (!this.renderFill) {
            drawGridBorder(g);
        } else {
            drawGridFill(g);
        }
    }

    /**
     * Método para desenhar a grade do jogo com bordas.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    public void drawGridBorder(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < this.getWidth(); x += 10) {
            for (int y = 0; y < this.getHeight() - grid.getSquaresize(); y += 10) {
                Ponto p = new Ponto(x, y);
                Content conteudo = getContentFromPointArea(p);

                g.setColor(Color.BLACK);
                g.setFont(new Font("Cascadia Code", Font.PLAIN, 10));
                switch (conteudo) {
                    case HEAD:
                        g.drawString("H", x, y);
                        break;
                    case TAIL:
                        g.drawString("T", x, y);
                        break;
                    case OBSTACLE:
                        g.drawString("O", x, y);
                        break;
                    case FOOD:
                        g.drawString("F", x, y);
                        break;
                    case EMPTY, DINAMICO:
                        g.drawString(".", x, y);
                        break;
                    case BORDER:
                        g.drawString("+", x, y);
                        break;
                    case EATING:
                        g.drawString("#", x, y);
                        break;
                }

            }
        }
    }

    /**
     * Para um ponto "p" verifica que elemento de jogo se encontra naquela zona
     *
     * @param p
     * @return
     */
    private Content getContentFromPointArea(Ponto p) {
        Quadrado q = new Quadrado(p, new Ponto(p.getX() + 11, p.getY() + 11));
        Cell c = bg.getGrid().returnCellFromPoint(p);
        for (Obstacle obstacle : bg.getObstaculos()) {
            if (obstacle.getPoligono().polygonsIntercept(q)) {
                return Content.OBSTACLE;
            }
        }
        for (Segmento seg : bg.getSnake().getHead().getArestas()) {
            if (seg.pertenceSeg(p)) {
                return Content.HEAD;
            }
        }
        for (Quadrado tail : bg.getSnake().getTail()) {
            for (Segmento seg : tail.getArestas()) {
                if (seg.pertenceSeg(p))
                    return Content.TAIL;
            }
        }
        for (Food food : bg.getComida()) {
            if (food instanceof SquareFood) {
                SquareFood sf = (SquareFood) food;
                if (q.polygonsIntercept(sf.getQuadrado()))
                    return Content.FOOD;
            } else {
                CircleFood cf = (CircleFood) food;
                if (cf.getCirculo().collidesWithPolygon(q))
                    return Content.FOOD;
            }
        }
        if (c.getContent() == Content.BORDER)
            return Content.BORDER;

        if (c.getContent() == Content.EATING)
            return Content.EATING;

        return Content.EMPTY;
    }

    /**
     * Método para desenhar a grade do jogo com preenchimento.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    public void drawGridFill(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < this.getWidth(); x += 10) {
            for (int y = 0; y < this.getHeight() - grid.getSquaresize(); y += 10) {
                Ponto p = new Ponto(x, y);
                Cell c = grid.returnCellFromPoint(p);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Cascadia Code", Font.PLAIN, 10));
                switch (c.getContent()) {
                    case HEAD:
                        g.drawString("H", x, y);
                        break;
                    case TAIL:
                        g.drawString("T", x, y);
                        break;
                    case OBSTACLE:
                        g.drawString("O", x, y);
                        break;
                    case FOOD:
                        drawFood(g, c, p);
                        break;
                    case EMPTY, DINAMICO:
                        g.drawString(".", x, y);
                        break;
                    case BORDER:
                        g.drawString("+", x, y);
                        break;
                    case EATING:
                        g.drawString("#", x, y);
                        break;
                }

            }
        }
    }

    /**
     * Desenha a pontuação do jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawScore(Graphics g) {
        int score = this.bg.getPlayer().getScore();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.PLAIN, getWidth() / 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Pontos: " + score + " ", this.getWidth() - metrics.stringWidth("Pontos: " + score + " "), this.getHeight() - g.getFont().getSize() / 3);
    }

    /**
     * Desenha a direção da cobra no jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawSnakeDirection(Graphics g) {
        int dir = this.bg.getSnake().getDirection();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.PLAIN, getWidth() / 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Dir H: " + dir + " ", 0, this.getHeight() - g.getFont().getSize() / 3);

    }

    /**
     * Desenha a tela de fim de jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Cascadia Code", Font.BOLD, getWidth() / 10));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (getWidth() - metrics.stringWidth("Game Over")) / 2, getHeight() / 2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Cascadia Code", Font.PLAIN, getWidth() / 20));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + this.bg.getPlayer().getScore(), (getWidth() - metrics1.stringWidth("Score: " + this.bg.getPlayer().getScore())) / 2, (int) (getHeight() / 1.5));
    }

    /**
     * Desenha a borda do jogo.
     *
     * @param g o objeto Graphics usado para desenhar
     */
    @Override
    public void drawBorder(Graphics g) {

    }

    /**
     * Atualiza o fundo do jogo.
     *
     * @param bg o novo fundo do jogo
     */
    @Override
    public void setBG(Background bg) {
        this.bg = bg;
    }


}
