package FigurasGeo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PoligonoTest {

    @Test
    public void testPolygonIntercept0() {
        Ponto[] pontosA = {new Ponto(5,5),new Ponto(8,5),new Ponto(8,7),new Ponto(5,7)};
        Ponto[] pontosB = {new Ponto(7,1),new Ponto(9,1),new Ponto(9,3),new Ponto(7,3)};
        Ponto[] pontosC = {new Ponto(7,4),new Ponto(9,4),new Ponto(9,6),new Ponto(7,6)};
        Ponto[] pontosD = {new Ponto(1,1),new Ponto(3,1),new Ponto(5,2),new Ponto(2,3)};
        Ponto[] pontosE = {new Ponto(1,1),new Ponto(2,2),new Ponto(3,0)};
        assertFalse(new Poligono(pontosA).polygonsIntercept(new Poligono(pontosB)));
        assertTrue(new Poligono(pontosA).polygonsIntercept(new Poligono(pontosC)));
        assertTrue(new Poligono(pontosD).polygonsIntercept(new Poligono(pontosE)));
    }

    @Test
    public void testPolygonToString(){
        Ponto[] pontosA = {new Ponto(5,5),new Ponto(8,6),new Ponto(8,7),new Ponto(5,7)};
        Ponto[] pontosE = {new Ponto(1,1),new Ponto(2,2),new Ponto(3,0)};
        assertEquals("FigurasGeo.Poligono de 4 vertices: [(5,5), (8,6), (8,7), (5,7)]",new Poligono(pontosA).toString());
        assertEquals("FigurasGeo.Poligono de 3 vertices: [(1,1), (2,2), (3,0)]",new Poligono(pontosE).toString());
    }

    @Test
    public void testRotate(){
        Ponto[] pontosA = {new Ponto(2,0),new Ponto(1,3),new Ponto(2,4),new Ponto(3,3)};
        Ponto[] pontosB = {new Ponto(3,1),new Ponto(5,1),new Ponto(5,3),new Ponto(7,3),new Ponto(5,5),new Ponto(3,5),new Ponto(1,3),new Ponto(3,3)};
        assertEquals("FigurasGeo.Poligono de 4 vertices: [(2,5), (3,2), (2,1), (1,2)]",new Poligono(pontosA).rotate(180).toString());
        assertEquals("FigurasGeo.Poligono de 4 vertices: [(2,8), (3,5), (2,4), (1,5)]",new Poligono(pontosA).rotate(-180,2,4).toString());
        assertEquals("FigurasGeo.Poligono de 8 vertices: [(2,4), (2,2), (4,2), (4,0), (6,2), (6,4), (4,6), (4,4)]",new Poligono(pontosB).rotate(-90).toString());
        assertEquals("FigurasGeo.Poligono de 8 vertices: [(5,9), (3,9), (3,7), (1,7), (3,5), (5,5), (7,7), (5,7)]",new Poligono(pontosB).rotate(180,4,5).toString());

    }

    @Test
    public void testMove1(){
        Ponto[] pontosA = {new Ponto(1,2),new Ponto(5,6),new Ponto(8,7),new Ponto(12,14)};
        Ponto[] pontosB = {new Ponto(2,8),new Ponto(1,9),new Ponto(2,10),new Ponto(3,10),new Ponto(4,9),new Ponto(3,8)};
        Ponto[] pontosC = {new Ponto(0,0),new Ponto(3,0),new Ponto(2,1),new Ponto(2,2),new Ponto(3,2),new Ponto(2,3),new Ponto(2,4),new Ponto(1,4),new Ponto(1,3),new Ponto(0,2),new Ponto(1,2),new Ponto(1,1)};
        assertEquals("FigurasGeo.Poligono de 4 vertices: [(0,5), (4,9), (7,10), (11,17)]",new Poligono(pontosA).move(-1,3).toString());
        assertEquals("FigurasGeo.Poligono de 6 vertices: [(6,1), (5,2), (6,3), (7,3), (8,2), (7,1)]",new Poligono(pontosB).move(4,-7).toString());
        assertEquals("FigurasGeo.Poligono de 12 vertices: [(3,2), (6,2), (5,3), (5,4), (6,4), (5,5), (5,6), (4,6), (4,5), (3,4), (4,4), (4,3)]",new Poligono(pontosC).move(3,2).toString());
    }

    @Test
    public void testMove2(){
        Ponto[] pontosA = {new Ponto(0,5),new Ponto(4,9),new Ponto(7,10),new Ponto(11,17)};
        Ponto[] pontosB = {new Ponto(2,8),new Ponto(1,9),new Ponto(2,10),new Ponto(3,10),new Ponto(4,9),new Ponto(3,8)};
        Ponto[] pontosC = {new Ponto(0,0),new Ponto(3,0),new Ponto(2,1),new Ponto(2,2),new Ponto(3,2),new Ponto(2,3),new Ponto(2,4),new Ponto(1,4),new Ponto(1,3),new Ponto(0,2),new Ponto(1,2),new Ponto(1,1)};
        assertEquals("FigurasGeo.Poligono de 4 vertices: [(0,5), (4,9), (7,10), (11,17)]",new Poligono(pontosA).moveCentroid(5,10).toString());
        assertEquals("FigurasGeo.Poligono de 6 vertices: [(6,1), (5,2), (6,3), (7,3), (8,2), (7,1)]",new Poligono(pontosB).moveCentroid(6,2).toString());
        assertEquals("FigurasGeo.Poligono de 12 vertices: [(3,2), (6,2), (5,3), (5,4), (6,4), (5,5), (5,6), (4,6), (4,5), (3,4), (4,4), (4,3)]",new Poligono(pontosC).moveCentroid(4,4).toString());
    }
}