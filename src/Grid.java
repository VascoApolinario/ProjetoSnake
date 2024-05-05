import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private Cell[][] cells;

    private final int width;
    private final int height;
    private final int squaresize;

    public Grid(int width, int height, int squaresize) {
        this.width = width;
        this.height = height;
        this.squaresize = squaresize;

        int rows = height / squaresize;
        int cols = width / squaresize;

        cells = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Ponto bottomLeft = new Ponto(j * squaresize, i * squaresize);
                Ponto topRight = new Ponto((j + 1) * squaresize, (i+1) * squaresize);

                cells[i][j] = new Cell(bottomLeft, topRight);
            }
        }
    }

    public Grid(int width, int height, int squaresize, int[] rows, int[] cols)
    {
        this(width, height, squaresize);


        this.getCells()[1][2].updateCell(false,Content.OBSTACLE);

    }


    public Cell pickSpawnPoint(){
        Random rand = new Random();
        boolean selected = false;
        Cell[][] cells = this.getCells();
        Cell c = null;
        if(cellAvaiable())
        {
            while(!selected){
                int row = rand.nextInt(this.getHeight()/this.getSquaresize());
                int column = rand.nextInt(this.getWidth()/this.getSquaresize());
                if (cells[row][column].isEmpty()) {
                    c = cells[row][column];
                    selected = true;
                }
            }
        }
        else
            System.out.println("You have not selected any cells!");
        return c;
    }

    public void update(ArrayList<Obstacle> obstacles) {
        for (Obstacle o : obstacles) {
            for (Cell[] cell : cells) {
                for (int j = 0; j < cell.length; j++) {
                    if (cell[j].polygonsIntercept(o.getPoligono())) {
                        cell[j].updateCell(false, Content.OBSTACLE);
                    }
                    else if ((!cell[j].polygonsIntercept(o.getPoligono())) && !cell[j].isEmpty() && !(cell[j].getContent() == Content.FOOD))
                    {
                        cell[j].updateCell(true, Content.EMPTY);
                    }
                }
            }
        }
    }

    public boolean cellAvaiable(){
        boolean check = false;
        Cell[][] cells = this.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isEmpty()) {
                    check = true;
                }
            }
        }
        return check;
    }

    public Cell returnCellFromPoint(Ponto p){
        int row =(int) p.getY()/this.squaresize;
        int col =(int) p.getX()/this.squaresize;
        return this.getCells()[row][col];
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public Cell[][] getCells() {return cells;}
    public int getSquaresize() {return squaresize;}


}