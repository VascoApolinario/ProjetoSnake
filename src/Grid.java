import FigurasGeo.Ponto;

public class Grid {
    private Cell[][] squares;
    private final int width;
    private final int height;
    private final int squaresize;

    public Grid(int width, int height, int squaresize) {
        this.width = width;
        this.height = height;
        this.squaresize = squaresize;

        int rows = height / squaresize;
        int cols = width / squaresize;

        squares = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Ponto bottomLeft = new Ponto(j * squaresize, (i + 1) * squaresize);
                Ponto topRight = new Ponto((j + 1) * squaresize, i * squaresize);

                //squares[i][j] = new Cell(bottomLeft, topRight);
            }
        }
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
}