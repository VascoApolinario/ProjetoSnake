package FigurasGeo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadradoTest {
    @Test
    public void testRotate(){
        Ponto[] pontosA = {new Ponto(1,2),new Ponto(3,2),new Ponto(3,4),new Ponto(1,4)};
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};

        assertEquals("FigurasGeo.Quadrado: [(2,2), (3,3), (2,4), (1,3)]",new Quadrado(pontosA).rotate(45).toString());
        assertEquals("FigurasGeo.Quadrado: [(1,2), (3,2), (3,4), (1,4)]",new Quadrado(pontosA).rotate(360).toString());
        assertEquals("FigurasGeo.Quadrado: [(5,5), (5,1), (9,1), (9,5)]",new Quadrado(pontosB).rotate(-90,5,1).toString());
        assertEquals("FigurasGeo.Quadrado: [(9,9), (5,9), (5,5), (9,5)]",new Quadrado(pontosB).rotate(180,5,5).toString());

    }

    @Test
    public void testMove1(){
        Ponto[] pontosA = {new Ponto(1,2),new Ponto(3,2),new Ponto(3,4),new Ponto(1,4)};
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};
        assertEquals("FigurasGeo.Quadrado: [(1,12), (3,12), (3,14), (1,14)]",new Quadrado(pontosA).move(0,10).toString());
        assertEquals("FigurasGeo.Quadrado: [(0,0), (2,0), (2,2), (0,2)]",new Quadrado(pontosA).move(-1,-2).toString());
        assertEquals("FigurasGeo.Quadrado: [(5,1), (9,1), (9,5), (5,5)]",new Quadrado(pontosB).move(4,0).toString());
        assertEquals("FigurasGeo.Quadrado: [(5,5), (9,5), (9,9), (5,9)]",new Quadrado(pontosB).move(4,4).toString());


    }

    @Test
    public void testMove2(){
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};
        assertEquals("FigurasGeo.Quadrado: [(5,1), (9,1), (9,5), (5,5)]",new Quadrado(pontosB).moveCentroid(7,3).toString());
        assertEquals("FigurasGeo.Quadrado: [(5,5), (9,5), (9,9), (5,9)]",new Quadrado(pontosB).moveCentroid(7,7).toString());

    }

    @Test
    public  void testIntercept(){
        Ponto[] pontosA = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};
        Ponto[] pontosB = {new Ponto(4,4),new Ponto(6,2),new Ponto(8,4),new Ponto(6,6)};
        Ponto[] pontosC = {new Ponto(4.8,5.2),new Ponto(7.2,5.2),new Ponto(7.2,2.8),new Ponto(4.8,2.8)};
        assertFalse(new Quadrado(pontosA).polygonsIntercept(new Quadrado(pontosA)));
        assertTrue(new Quadrado(pontosB).polygonsIntercept(new Quadrado(pontosC)));
    }

}