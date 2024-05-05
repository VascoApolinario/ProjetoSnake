import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public interface IGraficos {

    void drawPanel(int width, int height);
    void createFrame();
    void drawGameElements();
    void drawSnake(Graphics g);
    void drawObstacles(Graphics g);
    void drawFood(Graphics g);
    void drawGrid(Graphics g);
    void repaint();

}


class Grafica extends JPanel implements IGraficos {
    JFrame gameframe;
    private Background bg;



    public Grafica(int width, int height,Background bg) {
        this.bg = bg;
        drawPanel(width,height);
        createFrame();
    }


    public void drawPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(255,255,255));
        this.setFocusable(true);
        this.addKeyListener(new InputHandler(bg.getSnake()));
    }

    @Override
    public void createFrame() {
        this.gameframe = new JFrame();
        this.gameframe.add(this);
        this.gameframe.setTitle("SNAKE");
        this.gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameframe.setResizable(false);
        this.gameframe.pack();
        this.gameframe.setVisible(true);
        this.gameframe.setLocationRelativeTo(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawFood(g);
        drawSnake(g);
        drawObstacles(g);

    }

    @Override
    public void drawGameElements() {

    }

    @Override
    public void drawSnake(Graphics g) {
        Snake snake = bg.getSnake();
        int headSize = (int)snake.getHead().getSide();
        int x = (int)snake.getHead().getDownLeft().getX();
        int y = (int)snake.getHead().getDownLeft().getY();
        g.setColor(new Color(24, 197, 15, 255));
        g.drawRect(x,y,headSize,headSize);
        g.setColor(new Color(20, 57, 2, 255));
        g.fillRect(x,y,headSize,headSize);
        if(!snake.getTail().isEmpty()) {
            for (Quadrado t : snake.getTail()) {
                g.setColor(new Color(24, 197, 15, 255));
                g.fillRect((int) t.getDownLeft().getX(), (int) t.getDownLeft().getY(), (int) t.getSide(), (int) t.getSide());
            }
        }

    }

    @Override
    public void drawObstacles(Graphics g) {
        ArrayList<Obstacle> obstacles = bg.getObstaculos();
        for (Obstacle o : obstacles) {
            Poligono po = o.getPoligono();
            g.setColor(new Color(1, 23, 255, 255));
            g.fillPolygon(po.getxv(),po.getyv(),po.getPontos().length);
            g.setColor(new Color(0, 0, 0, 255));
            g.drawPolygon(po.getxv(),po.getyv(),po.getPontos().length);
        }
    }

    @Override
    public void drawFood(Graphics g) {
        ArrayList<Food> food = bg.getComida();
        for(Food f : food)
        {
            g.setColor(new Color(227, 8, 8));
            if(f instanceof CircleFood)
            {
                int radius = (int) ((CircleFood) f).getCirculo().getRadius();
                int x = (int) (((CircleFood) f).getCirculo().getCenter().getX()-radius);
                int y = (int) (((CircleFood) f).getCirculo().getCenter().getY()-radius);
                g.fillOval(x,y,2*radius,2*radius);
            }
            else if (f instanceof SquareFood) {
                int side = (int) ((SquareFood) f).getQuadrado().getSide();
                int x = (int) ((SquareFood) f).getQuadrado().getDownLeft().getX();
                int y = (int) ((SquareFood) f).getQuadrado().getDownLeft().getY();
                g.fillRect(x,y,side,side);

            }
        }
    }


    @Override
    public void drawGrid(Graphics g) {
        Grid grid = bg.getGrid();
        for (int x = 0; x < grid.getCells().length; x++) {
            for (int y = 0; y < grid.getCells()[x].length; y++) {
                Ponto ponto = grid.getCells()[x][y].getPontos()[0];
                int size = (int) grid.getCells()[x][y].getSide();
                g.setColor(new Color(0,0,0));
                g.drawRect((int)ponto.getX(),(int)ponto.getY(),size,size);
                if(!grid.getCells()[x][y].isEmpty()) {
                    g.setColor(new Color(172, 23, 177));
                    g.fillRect((int) ponto.getX(), (int) ponto.getY(), size, size);
                }
            }
        }
    }



}



class Textual implements IGraficos{

    @Override
    public void drawPanel(int width, int height) {

    }

    @Override
    public void createFrame() {

    }

    @Override
    public void drawGameElements() {

    }

    @Override
    public void drawSnake(Graphics g) {

    }

    @Override
    public void drawObstacles(Graphics g) {

    }

    @Override
    public void drawFood(Graphics g) {

    }


    @Override
    public void drawGrid(Graphics g) {

    }

    @Override
    public void repaint() {

    }


}