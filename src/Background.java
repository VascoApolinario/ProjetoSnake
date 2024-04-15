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
        this.snake = new Snake(0,0);
        this.grid = new Grid(800,600,30);
        this.score = new Score(0);
    }
}
