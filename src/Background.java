import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;

public class Background {
    private ArrayList<Obstacle> obstaculos;
    private ArrayList<Food> comida;
    private Snake snake;
    private Grid grid;
    private Player player;
    private boolean gameOver;
    private boolean updateLeaderBoard;

    public Background(int Width, int Height, String playername) {
        this.grid = new Grid(Width,Height,40);
        this.snake = new Snake(40,0, this.grid.returnCellFromPoint(new Ponto(40,300)));
        this.player = new Player(playername);
        this.comida = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.comida.add(new CircleFood(this.grid.pickSpawnPoint(), 15));
        this.comida.add(new SquareFood(30,this.grid.pickSpawnPoint()));

        this.obstaculos.add(new Obstacle("Poligono 3 400 300 450 350 330 150", true, 45));
        this.obstaculos.add(new Obstacle("Poligono 3 80 80 80 60 120 60", true, 45));
        this.gameOver = false;
        this.updateLeaderBoard = false;
    }

    public void updateAll() {
        if(!gameOver) {
            if(snake.getStatus().equals(Status.ALIVE))
                snake.move(this.grid);
            snake.update();
            for (Obstacle o : obstaculos) {
                o.update();
            }
            this.grid.update(obstaculos);
            for (Food f : comida) {
                snake.eat(f, this.grid);
            }
            if (snake.increaseScore) {
                player.setScore(player.getScore() + 1);
                snake.increaseScore = false;
            }
            if (snake.getStatus().equals(Status.DEAD)) {
                gameOver = true;
                if (player.getScore() > player.getBestScore()) {
                    player.setBestScore(player.getScore());
                    updateLeaderBoard = true;
                }
                snake.setStatus(Status.ALIVE);
            }
        }
    }

    public void setUpdateLeaderBoard(boolean updateLeaderBoard) {
        this.updateLeaderBoard = updateLeaderBoard;
    }

    public boolean getUpdateLeaderBoard() {
        return updateLeaderBoard;
    }

    public Snake getSnake() {
        return snake;
    }

    public Grid getGrid() {
        return grid;
    }

    public ArrayList<Food> getComida() {
        return comida;
    }

    public ArrayList<Obstacle> getObstaculos() { return obstaculos;}

    public Player getPlayer() {
        return player;
    }

    public boolean getGameOver() {
        return gameOver;
    }
}
