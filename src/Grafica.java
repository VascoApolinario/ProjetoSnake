import FigurasGeo.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Grafica extends JPanel implements IGraficos {
    private Background bg;
    private boolean renderFill;

    public Grafica(int width, int height, Background bg, InputHandler inputHandler, boolean renderFill) {
        this.bg = bg;
        this.renderFill = renderFill;
        drawPanel(width, height, inputHandler);
    }


    public void drawPanel(int width, int height, InputHandler inputHandler) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(255, 255, 255));
        this.setFocusable(true);
        this.addKeyListener(inputHandler);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!bg.getGameOver()) {
            //drawGrid(g);
            drawBorder(g);
            drawFood(g);
            drawSnake(g);
            drawObstacles(g);
            drawScore(g);
            drawSnakeDirection(g);
        } else
            drawGameOver(g);

    }


    @Override
    public void drawSnake(Graphics g) {
        Snake snake = bg.getSnake();
        int headSize = (int) snake.getHead().getSide();
        int x = (int) snake.getHead().getDownLeft().getX();
        int y = (int) snake.getHead().getDownLeft().getY();
        if (renderFill) {
            g.setColor(new Color(19, 53, 2, 255));
            g.fillRect(x, y, headSize, headSize);
        }
        g.setColor(new Color(42, 128, 2, 255));
        g.drawRect(x, y, headSize, headSize);
        if (!snake.getTail().isEmpty()) {
            for (Quadrado t : snake.getTail()) {
                if (renderFill) {
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
            g.drawPolygon(po.getxv(), po.getyv(), po.getPontos().length);
        }
    }

    @Override
    public void drawFood(Graphics g) {
        ArrayList<Food> food = bg.getComida();
        for (Food f : food) {

            if (f instanceof CircleFood) {
                int radius = (int) ((CircleFood) f).getCirculo().getRadius();
                int x = (int) (((CircleFood) f).getCirculo().getCenter().getX() - radius);
                int y = (int) (((CircleFood) f).getCirculo().getCenter().getY() - radius);
                if (renderFill) {
                    g.setColor(new Color(246, 38, 38));
                    g.fillOval(x, y, 2 * radius, 2 * radius);
                }
                g.setColor(new Color(227, 8, 8));
                g.drawOval(x, y, 2 * radius, 2 * radius);
            } else if (f instanceof SquareFood) {
                int side = (int) ((SquareFood) f).getQuadrado().getSide();
                int x = (int) ((SquareFood) f).getQuadrado().getDownLeft().getX();
                int y = (int) ((SquareFood) f).getQuadrado().getDownLeft().getY();
                if (renderFill) {
                    g.setColor(new Color(246, 38, 38));
                    g.fillRect(x, y, side, side);
                }
                g.setColor(new Color(227, 8, 8));
                g.drawRect(x, y, side, side);

            }
        }
    }

    @Override
    public void drawFood(Graphics g, Cell c, Ponto p) {

    }


    @Override
    public void drawGrid(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < grid.getCells().length - 1; x++) {
            for (int y = 0; y < grid.getCells()[x].length; y++) {
                Ponto ponto = grid.getCells()[x][y].getPontos()[0];
                int size = (int) grid.getCells()[x][y].getSide();
                g.setColor(new Color(0, 0, 0));
                g.drawRect((int) ponto.getX(), (int) ponto.getY(), size, size);
                if (x == 0 || y == 0 || x == grid.getCells().length - 2 || y == grid.getCells()[x].length - 1) {
                    g.setColor(new Color(43, 26, 2));
                    g.fillRect((int) ponto.getX(), (int) ponto.getY(), size, size);
                }

                if (!grid.getCells()[x][y].isEmpty()) {
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
        g.setFont(new Font("Courier New", Font.PLAIN, getWidth() / 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Pontos: " + score + " ", this.getWidth() - metrics.stringWidth("Pontos: " + score + " "), this.getHeight() - g.getFont().getSize() / 3);
    }


    @Override
    public void drawSnakeDirection(Graphics g) {
        int dir = this.bg.getSnake().getDirection();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.PLAIN, getWidth() / 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Dir H: " + dir + " ", 0, this.getHeight() - g.getFont().getSize() / 3);

    }

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

    @Override
    public void drawBorder(Graphics g) {
        Grid grid = this.bg.getGrid();
        g.setColor(Color.black);
        int x1 = (int) grid.getCells()[0][0].getTopRight().getX();
        int y1 = (int) grid.getCells()[0][0].getTopRight().getY();
        int x2 = (int) grid.getCells()[0][grid.getCells()[0].length - 1].getDownLeft().getX();
        int y2 = y1;
        g.drawLine(x1, y1, x2, y2);
        x1 = x2;
        y2 = (int) grid.getCells()[grid.getCells().length - 2][grid.getCells()[0].length - 1].getDownLeft().getY();
        g.drawLine(x1, y1, x2, y2);
        y1 = y2;
        x2 = (int) grid.getCells()[0][0].getTopRight().getX();
        g.drawLine(x1, y1, x2, y2);
        x1 = x2;
        y2 = (int) grid.getCells()[0][0].getTopRight().getY();
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void setBG(Background bg) {
        this.bg = bg;
    }

}
