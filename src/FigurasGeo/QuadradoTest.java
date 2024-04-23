package FigurasGeo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadradoTest {
    @Test
    public void testRotate(){
        Ponto[] pontosA = {new Ponto(1,2),new Ponto(3,2),new Ponto(3,4),new Ponto(1,4)};
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};

        assertEquals("Quadrado: [(2,2), (3,3), (2,4), (1,3)]",new Quadrado(pontosA).rotate(45).toString());
        assertEquals("Quadrado: [(1,2), (3,2), (3,4), (1,4)]",new Quadrado(pontosA).rotate(360).toString());
        assertEquals("Quadrado: [(5,5), (5,1), (9,1), (9,5)]",new Quadrado(pontosB).rotate(-90,5,1).toString());
        assertEquals("Quadrado: [(9,9), (5,9), (5,5), (9,5)]",new Quadrado(pontosB).rotate(180,5,5).toString());

    }

    @Test
    public void testMove1(){
        Ponto[] pontosA = {new Ponto(1,2),new Ponto(3,2),new Ponto(3,4),new Ponto(1,4)};
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};
        assertEquals("Quadrado: [(1,12), (3,12), (3,14), (1,14)]",new Quadrado(pontosA).move(0,10).toString());
        assertEquals("Quadrado: [(0,0), (2,0), (2,2), (0,2)]",new Quadrado(pontosA).move(-1,-2).toString());
        assertEquals("Quadrado: [(5,1), (9,1), (9,5), (5,5)]",new Quadrado(pontosB).move(4,0).toString());
        assertEquals("Quadrado: [(5,5), (9,5), (9,9), (5,9)]",new Quadrado(pontosB).move(4,4).toString());


    }

    @Test
    public void testMove2(){
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,5),new Ponto(1,5)};
        assertEquals("Quadrado: [(5,1), (9,1), (9,5), (5,5)]",new Quadrado(pontosB).moveCentroid(7,3).toString());
        assertEquals("Quadrado: [(5,5), (9,5), (9,9), (5,9)]",new Quadrado(pontosB).moveCentroid(7,7).toString());

    }

    @Test
    public void isInsideTest()
    {
        Ponto[] pontosA = {new Ponto(2,1),new Ponto(6,1),new Ponto(6,5),new Ponto(1,5)};
        Ponto[] pontosB = {new Ponto(3,2),new Ponto(5,2),new Ponto(5,4),new Ponto(3,4)};
        Ponto[] pontosC = {new Ponto(5,2),new Ponto(7,2),new Ponto(7,4),new Ponto(5,4)};
        Ponto[] pontosD = {new Ponto(4,0),new Ponto(7,3),new Ponto(4,6),new Ponto(1,3)};
        Circulo c1 = new Circulo(new Ponto(4,3),1);
        Circulo c2 = new Circulo(new Ponto(4,3),2);
        assertTrue(new Quadrado(pontosA).isInside(new Quadrado(pontosB)));
        assertTrue(new Quadrado(pontosA).isInside(c1));
        assertTrue(new Quadrado(pontosA).isInside(c2));
        assertFalse(new Quadrado(pontosA).isInside(new Quadrado(pontosC)));

        assertTrue(new Quadrado(pontosD).isInside(new Quadrado(pontosB)));
        assertTrue(new Quadrado(pontosD).isInside(c1));
        assertTrue(new Quadrado(pontosD).isInside(c2));
        assertFalse(new Quadrado(pontosD).isInside(new Quadrado(pontosA)));

    }

}