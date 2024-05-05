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
    private IGraficos tipoGraficos;
    private boolean gameOver;

    public Background(int Width, int Height, String tipo) {
        this.grid = new Grid(Width,Height,40);
        this.snake = new Snake(40,0, this.grid.pickSpawnPoint());
        this.player = new Player("PARA MUDAR DPS", 0);
        //this.tipoGraficos = new Grafica(Width,Height,this);
        this.tipoGraficos = new Textual(Width,Height,this);
        this.comida = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.comida.add(new CircleFood(this.grid.pickSpawnPoint(), 15));
        this.comida.add(new SquareFood(30,this.grid.pickSpawnPoint()));

        this.obstaculos.add(new Obstacle("Poligono 3 400 300 450 350 330 150", false, 0));
        this.gameOver = false;
    }

    public void updateAll() {
        snake.move(this.grid);
        snake.update();
        this.grid.update(obstaculos);
        for (Food f : comida) {
            snake.eat(f,this.grid);
        }
        if(snake.increaseScore)
        {
            player.setScore(player.getScore()+1);
            snake.increaseScore = false;
        }
        if(!snake.alive)
        {
            gameOver = true;
            if(player.getScore() > player.getBestScore())
            {
                player.setBestScore(player.getScore());
            }
        }
        System.out.println(player.getScore());
        this.tipoGraficos.repaint();

        /*
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        //repaint();

        /*
        for (Obstacle o : obstaculos) {
            if (o.isDinamico()) {
                o.update();
            }
        }
        snake.update();
        for (Food c : comida) {
            c.update();
        }
        */
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
}
