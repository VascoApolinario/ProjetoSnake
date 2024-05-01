import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface IGraficos {

    void drawPanel(int width, int height, Grid grid);
    void createFrame();
    void drawGameElements(Graphics g);


    void drawGrid(Grid grid);
}


class Grafica extends JPanel implements IGraficos {
    JFrame gameframe;
    JPanel gamepanel;



    public Grafica(int width, int height,Grid grid) {
        drawPanel(width,height,grid);
        createFrame();
        drawGrid(grid);



    }


    public void drawPanel(int width, int height, Grid grid) {
        this.gamepanel = new JPanel();
        this.gamepanel.setPreferredSize(new Dimension(width,height));
        this.gamepanel.setBackground(new Color(255,255,255));
        this.gamepanel.setFocusable(true);
    }

    @Override
    public void createFrame() {
        this.gameframe = new JFrame();
        this.gameframe.add(this.gamepanel);
        this.gameframe.setTitle("SNAKE");
        this.gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameframe.setResizable(false);
        this.gameframe.pack();
        this.gameframe.setVisible(true);
        this.gameframe.setLocationRelativeTo(null);
    }

    @Override
    public void drawGameElements(Graphics g) {

    }


    @Override
    public void drawGrid(Grid grid) {
        Graphics g = gamepanel.getGraphics();
        for (int x = 0; x < grid.getCells().length; x++) {
            for (int y = 0; y < grid.getCells()[x].length; y++) {
                Ponto ponto = grid.getCells()[x][y].getPontos()[1];
                int size = (int) grid.getCells()[x][y].getSide();
                g.setColor(new Color(0,0,0));
                g.drawRect((int)ponto.getX(),(int)ponto.getY(),size,size);
            }
        }
    }

}



class Textual implements IGraficos{
    public void drawPanel(int width, int height) {
    }

    @Override
    public void drawPanel(int width, int height, Grid grid) {

    }

    @Override
    public void createFrame() {

    }

    @Override
    public void drawGameElements(Graphics g) {

    }

    @Override
    public void drawGrid(Grid grid) {

    }


}