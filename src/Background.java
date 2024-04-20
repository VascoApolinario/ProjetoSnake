import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Background {
    private ArrayList<Obstacle> obstaculos;
    private ArrayList<Food> comida;
    private Snake snake;
    private Grid grid;
    private Score score;
    private IGraficos tipoGraficos;

    public Background(int Width, int Height, String tipo) {
        this.grid = new Grid(Width,Height,30);
        this.snake = new Snake(0,0, new Ponto(this.grid.getWidth()/2,this.grid.getHeight()/2));
        this.score = new Score(0);
    }

    public void updateAll(){
        for (Obstacle o : obstaculos) {
            if (o.isDinamico()) {
                o.update();
            }
        }
        snake.update();
        for (Food c : comida) {
            c.update();
        }
    }
}
