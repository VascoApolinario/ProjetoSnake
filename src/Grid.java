import FigurasGeo.Ponto;

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
                Ponto bottomLeft = new Ponto(j * squaresize, (i + 1) * squaresize);
                Ponto topRight = new Ponto((j + 1) * squaresize, i * squaresize);

                cells[i][j] = new Cell(bottomLeft, topRight);
            }
        }
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public Cell[][] getCells() {return cells;}
    public int getSquaresize() {return squaresize;}

    public Cell find(Ponto p) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                //if ()
            }
        }
        return null;
    }
}