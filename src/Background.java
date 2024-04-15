import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Background {
    private ArrayList<Obstacle> obstaculos;
    private Snake snake;
    private Grid grid;
    private Score score;

    public Background() {
        this.grid = new Grid(800,600,30);
        this.snake = new Snake(0,0, new Ponto(this.grid.getWidth()/2,this.grid.getHeight()/2));
        this.score = new Score(0);
    }
}
