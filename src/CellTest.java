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
    void testSetContent()
    {
        /*
        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        assertTrue(cell.isEmpty());
        Obstacle content0 = new Obstacle("Quadrado", false,0);
        Obstacle content1 = new Obstacle("Quadrado", false,1);
        cell.setContent(content0);
        assertFalse(cell.isEmpty());
        assertEquals(content0, cell.getContent());
        assertNotEquals(content1, cell.getContent());*/
    }
    @Test
    void testGetContent()
    {
        /*
        Ponto p1 = new Ponto(0.0,0.0);
        Ponto p2 = new Ponto(1.0,1.0);
        Cell cell = new Cell(p1,p2);
        Obstacle content0 = new Obstacle("Quadrado", false,0);
        Obstacle content1 = new Obstacle("Quadrado", false,1);
        cell.setContent(content0);
        assertEquals(content0, cell.getContent());
        assertNotEquals(content1, cell.getContent());*/
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

    /*
    @Test void isIntersectedTest()
    {
        Grid grid = new Grid(6,6,1);
        Ponto[] pontosA = {new Ponto(0,0),new Ponto(3,0),new Ponto(0,3)};
        assertTrue(grid.getCells()[0][0].isIntersected(new Poligono(pontosA)));
        assertTrue(grid.getCells()[0][1].isIntersected(new Poligono(pontosA)));
        assertTrue(grid.getCells()[0][2].isIntersected(new Poligono(pontosA)));
        assertTrue(grid.getCells()[1][0].isIntersected(new Poligono(pontosA)));
        assertTrue(grid.getCells()[1][1].isIntersected(new Poligono(pontosA)));
        assertTrue(grid.getCells()[2][0].isIntersected(new Poligono(pontosA)));
        Ponto[] pontosB = {new Ponto(3,4),new Ponto(6,4),new Ponto(6,6),new Ponto(3,6)};
        assertTrue(grid.getCells()[5][3].isIntersected(new Poligono(pontosB)));
        assertTrue(grid.getCells()[4][3].isIntersected(new Poligono(pontosB)));
        assertTrue(grid.getCells()[4][4].isIntersected(new Poligono(pontosB)));
        assertTrue(grid.getCells()[4][5].isIntersected(new Poligono(pontosB)));
        assertTrue(grid.getCells()[5][4].isIntersected(new Poligono(pontosB)));
        assertTrue(grid.getCells()[5][5].isIntersected(new Poligono(pontosB)));
        Ponto[] pontosC = {new Ponto(4,1),new Ponto(5,1),new Ponto(6,2),new Ponto(5,3),new Ponto(4,3),new Ponto(3,2)};
        assertTrue(grid.getCells()[1][3].isIntersected(new Poligono(pontosC)));
        assertTrue(grid.getCells()[1][4].isIntersected(new Poligono(pontosC)));
        assertTrue(grid.getCells()[1][5].isIntersected(new Poligono(pontosC)));
        assertTrue(grid.getCells()[2][3].isIntersected(new Poligono(pontosC)));
        assertTrue(grid.getCells()[2][4].isIntersected(new Poligono(pontosC)));
        assertTrue(grid.getCells()[2][5].isIntersected(new Poligono(pontosC)));
        Ponto[] pontosD = {new Ponto(1,4),new Ponto(2,4),new Ponto(2,5),new Ponto(1,5)};
        assertTrue(grid.getCells()[4][1].isIntersected(new Poligono(pontosD)));



        assertFalse(grid.getCells()[2][1].isIntersected(new Poligono(pontosA)));
        assertFalse(grid.getCells()[2][1].isIntersected(new Poligono(pontosB)));
        assertFalse(grid.getCells()[2][1].isIntersected(new Poligono(pontosC)));

        assertFalse(grid.getCells()[0][4].isIntersected(new Poligono(pontosA)));
        assertFalse(grid.getCells()[0][4].isIntersected(new Poligono(pontosB)));
        assertFalse(grid.getCells()[0][4].isIntersected(new Poligono(pontosC)));

        assertFalse(grid.getCells()[3][2].isIntersected(new Poligono(pontosA)));
        assertFalse(grid.getCells()[3][2].isIntersected(new Poligono(pontosB)));
        assertFalse(grid.getCells()[3][2].isIntersected(new Poligono(pontosC)));

        assertFalse(grid.getCells()[3][3].isIntersected(new Poligono(pontosA)));
        assertFalse(grid.getCells()[3][3].isIntersected(new Poligono(pontosB)));
        assertFalse(grid.getCells()[3][3].isIntersected(new Poligono(pontosC)));


    }*/

}