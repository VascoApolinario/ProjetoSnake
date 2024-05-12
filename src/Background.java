import FigurasGeo.Ponto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por representar o Background e guardar/atualizar todos os elementos de jogo.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */

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

    /**
     * Construtor da classe Background.
     * @param Width largura da grid
     * @param Height altura da grid
     * @param playername nome do jogador
     * @param activateAutoSnake valor booleano que representa se a snake se deve mover em modo automatico ou não
     */
    public Background(int Width, int Height, String playername, Boolean activateAutoSnake,int autoSnakeStrat) {
        this.grid = new Grid(Width,Height,40);
        this.snake = new Snake(40, this.grid.returnCellFromPoint(new Ponto(40,300)));
        this.player = new Player(playername);
        this.comida = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.obstaculos.add(new Obstacle("Poligono 3 400 300 450 350 330 150", true, 45));
        this.obstaculos.add(new Obstacle("Poligono 3 80 80 80 60 120 60", true, 45));
        this.grid.update(obstaculos);
        this.comida.add(new CircleFood(this.grid.pickSpawnPoint(), 15,(int)this.snake.getHead().getSide()));
        this.comida.add(new SquareFood(30,this.grid.pickSpawnPoint(),(int)this.snake.getHead().getSide()));
        this.gameOver = false;
        this.updateLeaderBoard = false;
        this.autoSnake = new PathFindingStrategy();
        this.activateAutoSnake = activateAutoSnake;
        if(autoSnakeStrat == 0)
            this.autoSnake = new PathFindingStrategy();
        else
            this.autoSnake = new RandomStrategy();
    }

    /**
     * Construtor da classe Background para ficheiros.
     * @param filename nome do ficheiro nivel a carregar
     * @param playername nome do jogador
     * @param activateAutoSnake valor booleano que representa se a snake se deve mover em modo automatico ou não
     */
    public Background(String filename, String playername, boolean activateAutoSnake, int autoSnakeStrat) {
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
                    //int x = Integer.parseInt(coords[0].trim());
                    //int y = Integer.parseInt(coords[1].trim());
                    this.snake = new Snake(cellsize, this.grid.pickSpawnPoint());
                } else if (line.startsWith("CircleFood")) {
                    int radius = Integer.parseInt(line.replace("CircleFood,", "").trim());
                    this.comida.add(new CircleFood(this.grid.pickSpawnPoint(), radius,cellsize));
                } else if (line.startsWith("SquareFood")) {
                    int size = Integer.parseInt(line.replace("SquareFood,", "").trim());
                    this.comida.add(new SquareFood(size, this.grid.pickSpawnPoint(),cellsize));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        this.activateAutoSnake = activateAutoSnake;
        if(autoSnakeStrat == 0)
            this.autoSnake = new PathFindingStrategy();
        else
            this.autoSnake = new RandomStrategy();
    }

    /**
     * Metodo que atualiza todos os objetos do jogo.
     * @post Objetos de jogo atualizados
     */
    public void updateAll() {
        if(!gameOver) {
            if(activateAutoSnake)
            {
                autoSnake.update(this.snake,this);
            }
            if(snake.getStatus().equals(Status.ALIVE))
                snake.move(this.grid);
            snake.update();
            for (Obstacle o : obstaculos) {
                o.update();
            }
            this.grid.update(obstaculos);
            for (Food f : comida) {
                if (this.grid.cellAvaiable())  {
                    snake.eat(f, this.grid);
                }
            }
            if (snake.increaseScore) {
                player.setScore(player.getScore() + 1);
                snake.increaseScore = false;
            }
            if (snake.getStatus().equals(Status.DEAD)) {
                gameOver = true;
                snake.setStatus(Status.ALIVE);
            }
            if(!grid.cellAvaiable()) {
                player.setScore(Integer.MAX_VALUE);
                gameOver = true;
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

    /**
     * Setter do atributo "updateLeaderboard"
     * @param updateLeaderBoard booleano atualizado
     */
    public void setUpdateLeaderBoard(boolean updateLeaderBoard) {
        this.updateLeaderBoard = updateLeaderBoard;
    }

    /**
     * Getter do atributo "updateLeaderboard"
     * @return updateLeaderBoard
     */
    public boolean getUpdateLeaderBoard() {
        return updateLeaderBoard;
    }

    /**
     * Getter do atributo "snake"
     * @return Snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Getter do atributo "grid"
     * @return grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Getter do atributo "comida"
     * @return comida
     */
    public ArrayList<Food> getComida() {
        return comida;
    }

    /**
     * Getter do atributo "obstaculos"
     * @return obstaculos
     */
    public ArrayList<Obstacle> getObstaculos() {
        return obstaculos;
    }

    /**
     * Getter do atributo "player"
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter do atributo "gameOver"
     * @return gameOver
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * Getter do atributo "Width"
     * @return Width
     */
    public int getWidth() {
        return this.Width;
    }

    /**
     * Getter do atributo "Height"
     * @return Height
     */
    public int getHeight() {
        return this.Height;
    }
}
