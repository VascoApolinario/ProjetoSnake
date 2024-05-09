import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe responsável por representar uma grelha.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @inv width > 0, height > 0, squaresize > 0, width % squaresize = 0, height % squaresize = 0
 * @version 1.0
 */

public class Grid {
    private Cell[][] cells;

    private final int width;
    private final int height;
    private final int squaresize;

    /**
     * Construtor da classe Grid.
     * @param width largura da grid
     * @param height altura da grid
     * @param squaresize tamanho do lado de cada célula da grid
     */
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
                if(i == 0 || i == rows - 1 || j == 0 || j == cols - 1  || i == rows - 2) {
                    cells[i][j].updateCell(false,Content.BORDER);
                }
            }
        }
    }

    /**
     * Metodo que retorna uma célula vazia aleatória.
     * @return celula vazia.
     */
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
                if (cells[row][column].getContent().equals(Content.EMPTY)) {
                    c = cells[row][column];
                    selected = true;
                }
            }
        }
        else
            System.out.println("You have not selected any cells!");
        return c;
    }

    /** Metodo de update a grid com base nos obstaculos
     * @param obstacles ArrayList de obstaculos
     * @post Celulas que intersetam os obstaculos ou uma rotação do obstaculo são marcadas como ocupadas
     */
    public void update(ArrayList<Obstacle> obstacles) {
        for (Obstacle o : obstacles) {
            this.update(o);
        }
    }
    /** Metodo de update a grid para um obstaculo individual
     * @param o Obstaculo
     * @post Celulas que intersetam o obstaculo ou uma rotação do obstaculo são marcadas como ocupadas
     */
    public void update(Obstacle o) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (o.getPoligono().polygonsIntercept(cells[i][j])) {
                    cells[i][j].updateCell(false, Content.OBSTACLE);
                }
                if (o.isDinamico()) {
                    for (int k = 1; k <= 360 / o.getDegree(); k++) {
                        if ((cells[i][j].polygonsIntercept(o.getPoligono().rotate(o.getDegree() * k))) && (!cells[i][j].polygonsIntercept(o.getPoligono()))) {
                            cells[i][j].updateCell(false, Content.DINAMICO);
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que verifica se existem células vazias na grid.
     * @return true se existir celulas vazias, false se não
     */
    public boolean cellAvaiable(){
        boolean check = false;
        Cell[][] cells = this.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getContent().equals(Content.EMPTY)) {
                    check = true;
                }
            }
        }
        return check;
    }

    /**
     * Metodo que retorna a célula em que o ponto se encontra
     * @param p ponto
     * @return celula baseado nas coordenadas do ponto
     */
    public Cell returnCellFromPoint(Ponto p){
        int row =returnRowFromPoint(p);
        int col =returnColFromPoint(p);
        return this.getCells()[row][col];
    }

    /**
     * Metodo que retorna a Linha da grelha onde se encontra o ponto.
     * @param p ponto
     * @return Linha baseado nas coordenadas do ponto
     */
    public int returnRowFromPoint(Ponto p){
        return (int) p.getY()/this.squaresize;
    }

    /**
     * Metodo que retorna a Coluna da grelha onde se encontra o ponto.
     * @param p ponto
     * @return Coluna baseado nas coordenadas do ponto
     */
    public int returnColFromPoint(Ponto p){
        return (int) p.getX()/this.squaresize;
    }

    /**
     * Getter da altura da grid
     * @return atributo int Height (Altura)
     */
    public int getHeight() {return height;}

    /**
     * Getter da largura da grid
     * @return atributo int Width (Largura)
     */
    public int getWidth() {return width;}

    /**
     * Getter das celulas da grid
     * @return atributo Cells[][] cells (matriz de celulas)
     */
    public Cell[][] getCells() {return cells;}

    /**
     * Getter das dimensões das celulas da grid
     * @return atributo int squaresize (tamanho das celulas)
     */
    public int getSquaresize() {return squaresize;}
}