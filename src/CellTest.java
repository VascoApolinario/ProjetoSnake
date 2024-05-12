import FigurasGeo.Poligono;
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
    void testSetAndGetContent()
    {

        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        assertTrue(cell.getContent().equals(Content.EMPTY));
        cell.setContent(Content.OBSTACLE);
        assertTrue(cell.getContent().equals(Content.OBSTACLE));
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