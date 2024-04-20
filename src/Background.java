import FigurasGeo.Poligono;
import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Background extends JPanel implements ActionListener {
    private ArrayList<Obstacle> obstaculos;
    private Snake snake;
    private Grid grid;
    private Score score;
    private Poligono poligono;
    Timer timer;

    public Background() {
        //this.grid = new Grid(800,600,30);
        //this.snake = new Snake(0,0, new Ponto(this.grid.getWidth()/2,this.grid.getHeight()/2));
        //this.score = new Score(0);
        Ponto[] pontosA = {new Ponto(5,5),new Ponto(35,5),new Ponto(35,35),new Ponto(5,35)};
        this.poligono = new Poligono(pontosA);
        StartGame();
    }

    public void StartGame() {
        timer = new Timer(75,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        poligono.draw(g); // Draw the polygon

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.poligono = poligono.move(30,0);
        repaint();
    }
}
