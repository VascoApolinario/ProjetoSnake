import FigurasGeo.Ponto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testIsEmpty() {
        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        assertTrue(cell.isEmpty());
        cell.setEmpty(false);
        assertFalse(cell.isEmpty());
        cell.setEmpty(true);
        assertTrue(cell.isEmpty());
    }
    @Test
    void testSetContent()
    {
        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        assertTrue(cell.isEmpty());
        Obstacle content0 = new Obstacle("Quadrado", false,0);
        Obstacle content1 = new Obstacle("Quadrado", false,1);
        cell.setContent(content0);
        assertFalse(cell.isEmpty());
        assertEquals(content0, cell.getContent());
        assertNotEquals(content1, cell.getContent());
    }
    @Test
    void testGetContent()
    {
        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        Obstacle content0 = new Obstacle("Quadrado", false,0);
        Obstacle content1 = new Obstacle("Quadrado", false,1);
        cell.setContent(content0);
        assertEquals(content0, cell.getContent());
        assertNotEquals(content1, cell.getContent());
    }
    @Test void testSetEmpty(){
        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        assertTrue(cell.isEmpty());
        cell.setEmpty(false);
        assertFalse(cell.isEmpty());
        cell.setEmpty(true);
        assertTrue(cell.isEmpty());
    }

}