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

}