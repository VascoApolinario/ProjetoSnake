import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public interface IGraficos {

    void drawPanel(int width, int height);
    void createFrame();
    void drawGameElements();
    void drawSnake(Graphics g);
    void drawObstacles();
    void drawFood();
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
        drawSnake(g);
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
        g.setColor(new Color(232, 56, 77, 255));
        g.fillRect(x,y,headSize,headSize);
        if(!snake.getTail().isEmpty()) {
            for (Quadrado t : snake.getTail()) {
                g.setColor(new Color(24, 197, 15, 255));
                g.fillRect((int) t.getDownLeft().getX(), (int) t.getDownLeft().getY(), (int) t.getSide(), (int) t.getSide());
            }
        }

    }

    @Override
    public void drawObstacles() {

    }

    @Override
    public void drawFood() {

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
    public void drawObstacles() {

    }

    @Override
    public void drawFood() {

    }


    @Override
    public void drawGrid(Graphics g) {

    }

    @Override
    public void repaint() {

    }


}