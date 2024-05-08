import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Background {
    private ArrayList<Obstacle> obstaculos;
    private ArrayList<Food> comida;
    private Snake snake;
    private Grid grid;
    private Player player;
    private boolean gameOver;
    private boolean updateLeaderBoard;
    private AutoSnake autoSnake;
    private boolean activateAutoSnake;
    private int Width;
    private int Height;

    public Background(int Width, int Height, String playername, Boolean activateAutoSnake) {
        this.grid = new Grid(Width,Height,40);
        this.snake = new Snake(40,0, this.grid.returnCellFromPoint(new Ponto(40,300)));
        this.player = new Player(playername);
        this.comida = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.obstaculos.add(new Obstacle("Poligono 3 400 300 450 350 330 150", true, 45));
        this.obstaculos.add(new Obstacle("Poligono 3 80 80 80 60 120 60", true, 45));
        this.grid.update(obstaculos);
        this.comida.add(new CircleFood(this.grid.pickSpawnPoint(), 15));
        this.comida.add(new SquareFood(30,this.grid.pickSpawnPoint()));
        this.gameOver = false;
        this.updateLeaderBoard = false;
        this.autoSnake = new AutoSnake();
        this.activateAutoSnake = activateAutoSnake;
    }

    public Background(String filename, String playername, boolean activateAutoSnake) {
        this.obstaculos = new ArrayList<>();
        this.comida = new ArrayList<>();
        this.gameOver = false;
        this.updateLeaderBoard = false;
        this.player = new Player(playername);


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = br.readLine().split(",");
             this.Width = Integer.parseInt(dimensions[0].trim());
             this.Height = Integer.parseInt(dimensions[1].trim());
            int cellsize = Integer.parseInt(dimensions[2].trim());
            this.grid = new Grid(Width, Height, cellsize);

            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                line = line.trim();
                if (line.startsWith("Poligono")) {
                    String[] parts = line.split(", ");
                    boolean isFixed = Boolean.parseBoolean(parts[1].trim());
                    int rotation = Integer.parseInt(parts[2].trim());
                    this.obstaculos.add(new Obstacle(parts[0], isFixed, rotation));
                    this.grid.update(this.obstaculos);
                } else if (line.startsWith("Snake")) {
                    String[] coords = line.replace("Snake ", "").split(",");
                    int x = Integer.parseInt(coords[0].trim());
                    int y = Integer.parseInt(coords[1].trim());
                    this.snake = new Snake(cellsize, 0, this.grid.returnCellFromPoint(new Ponto(x, y)));
                } else if (line.startsWith("CircleFood")) {
                    int radius = Integer.parseInt(line.replace("CircleFood,", "").trim());
                    this.comida.add(new CircleFood(this.grid.pickSpawnPoint(), radius));
                } else if (line.startsWith("SquareFood")) {
                    int size = Integer.parseInt(line.replace("SquareFood,", "").trim());
                    this.comida.add(new SquareFood(size, this.grid.pickSpawnPoint()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        this.activateAutoSnake = activateAutoSnake;
        this.autoSnake = new AutoSnake();
    }


    public void updateAll() {
        if(!gameOver) {
            if(!grid.cellAvaiable()) {
                player.setScore(Integer.MAX_VALUE);
                gameOver = true;
            }
            if(activateAutoSnake)
            {
                autoSnake.Start(this.snake,this);
            }
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
                snake.setStatus(Status.ALIVE);
            }
        }
        else
        {
            if (player.getScore() > player.getBestScore()) {
                player.setBestScore(player.getScore());
                updateLeaderBoard = true;
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

    public int getWidth() {
        return this.Width;
    }
    public int getHeight() {
        return this.Height;
    }
}
