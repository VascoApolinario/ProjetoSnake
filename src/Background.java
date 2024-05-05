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

<<<<<<< Updated upstream
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
=======
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
>>>>>>> Stashed changes

        this.obstaculos.add(new Obstacle("Poligono 3 400 300 450 350 330 150", false, 0));

    }

<<<<<<< Updated upstream
    @Override
    public void actionPerformed(ActionEvent e) {
        this.poligono = poligono.move(30,0);
        repaint();
=======
    public void updateAll() {
        snake.move(this.grid);
        snake.update();
        this.grid.update(obstaculos);
        for (Food f : comida) {
            snake.eat(f,this.grid);
        }
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
>>>>>>> Stashed changes
    }

    public ArrayList<Obstacle> getObstaculos() { return obstaculos;}

}
