import FigurasGeo.Ponto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testGetHeight() {
    Grid grid = new Grid(100,100,100);
    assertEquals(100,grid.getHeight());
    }
    @Test
    void testGetWidth() {
        Grid grid = new Grid(100,100,100);
        assertEquals(100,grid.getWidth());
    }

    @Test
    void testGetSquareSize() {
        Grid grid = new Grid(100,100,100);
        assertEquals(100,grid.getSquaresize());
    }

    @Test
    void testpickSpawnPoint() {
        Grid grid = new Grid(800, 600, 40);
        Cell cell = grid.pickSpawnPoint();
        assert(cell.isEmpty());
    }

    @Test
    void testreturnColFromPoint() {
        Grid grid = new Grid(800, 600, 40);
        int col = grid.returnColFromPoint(new Ponto(0,40));
        assertEquals(0,col);

    }

    @Test
    void testupdate() {
        Grid grid = new Grid(800, 600, 40);
        Obstacle o = new Obstacle("Poligono 3 400 300 450 350 330 150", true, 45);
        grid.update(o);
        assert(grid.returnCellFromPoint(new Ponto(401,301)).getContent().equals(Content.OBSTACLE));
    }

    @Test
    void testcellAvailable() {
        Grid grid = new Grid(800, 600, 40);
        Cell[][] cells = grid.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i == 0 || i == cells.length - 1 || j == 0 || j == cells[i].length - 1 || i == cells.length - 2) {
                    assert(!cells[i][j].isEmpty());
                } else {
                    assert(cells[i][j].isEmpty());
                }
            }
        }
    }

    @Test
    void testreturnCellFromPoint() {
        Grid grid = new Grid(800, 600, 40);
        Cell cell = grid.returnCellFromPoint(new Ponto(0,0));
        assert(grid.returnColFromPoint(cell.getCentroide()) == 0);
    }

    @Test
    void testgetCells() {
        Grid grid = new Grid(800, 600, 40);
        Cell[][] cells = grid.getCells();
        assertEquals(cells.length, grid.getHeight()/grid.getSquaresize());
    }

}