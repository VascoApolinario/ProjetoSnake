import FigurasGeo.Ponto;

import java.util.Random;
import java.util.ArrayList;

public class Background {
    private ArrayList<Obstacle> obstaculos;
    private ArrayList<Food> comida;
    private Snake snake;
    private Grid grid;
    private Player player;
    private IGraficos tipoGraficos;

    public Background(int Width, int Height, String tipo) {
        this.grid = new Grid(Width,Height,30);
        this.snake = new Snake(0,0, new Ponto(this.grid.getWidth()/2,this.grid.getHeight()/2));
        this.player = new Player("PARA MUDAR DPS", 0);
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

    public Ponto randomLocation() {
        Ponto p = null;
        Random rand = new Random();
        boolean selected = false;
        boolean check = false;
        Cell[][] cells = this.grid.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isEmpty()) {
                    check = true;
                }
            }
        }
        if (check) {
            while(!selected){
                int row = rand.nextInt(this.grid.getHeight()/this.grid.getSquaresize());
                int column = rand.nextInt(this.grid.getWidth()/this.grid.getSquaresize());
                if (cells[row][column].isEmpty()) {
                    p = cells[row][column].getCentroide();
                    selected = true;
                }
            }
        }
        return p;
    }
}
