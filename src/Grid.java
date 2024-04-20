import FigurasGeo.CellSquare;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class Grid {
    private CellSquare[][] squares;
    private final int width;
    private final int height;
    private final int squaresize;

    public Grid(int width, int height, int squaresize) {
        this.width = width;
        this.height = height;
        this.squaresize = squaresize;

        int rows = height / squaresize;
        int cols = width / squaresize;

        squares = new CellSquare[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Ponto bottomLeft = new Ponto(j * squaresize, (i + 1) * squaresize);
                Ponto topRight = new Ponto((j + 1) * squaresize, i * squaresize);

                //squares[i][j] = new CellSquare(bottomLeft, topRight);
            }
        }
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
}