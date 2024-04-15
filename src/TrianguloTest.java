import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    public void testRotate(){
        Ponto[] pontosA = {new Ponto(2,1),new Ponto(4,1),new Ponto(3,4)};
        Ponto[] pontosB = {new Ponto(6,2),new Ponto(10,2),new Ponto(10,5)};
        assertEquals("Triangulo: [(4,3), (2,3), (3,0)]",new Triangulo(pontosA).rotate(180).toString());
        assertEquals("Triangulo: [(6,3), (6,5), (3,4)]",new Triangulo(pontosA).rotate(90,3,4).toString());
        assertEquals("Triangulo: [(10,0), (10,4), (7,4)]",new Triangulo(pontosB).rotate(90).toString());
        assertEquals("Triangulo: [(6,2), (3,5), (1,3)]",new Triangulo(pontosB).rotate(135,6,2).toString());
    }

    @Test
    public void testMove1(){
        Ponto[] pontosA = {new Ponto(2,1),new Ponto(4,1),new Ponto(3,4)};
        Ponto[] pontosB = {new Ponto(6,2),new Ponto(10,2),new Ponto(10,5)};
        assertEquals("Triangulo: [(0,4), (2,4), (1,7)]",new Triangulo(pontosA).move(-2,3).toString());
        assertEquals("Triangulo: [(6,6), (8,6), (7,9)]",new Triangulo(pontosA).move(4,5).toString());
        assertEquals("Triangulo: [(0,0), (4,0), (4,3)]",new Triangulo(pontosB).move(-6,-2).toString());
        assertEquals("Triangulo: [(8,3), (12,3), (12,6)]",new Triangulo(pontosB).move(2,1).toString());
    }

    @Test
    public void testMove2(){
        Ponto[] pontosA = {new Ponto(1,0),new Ponto(3,0),new Ponto(2,3)};
        Ponto[] pontosB = {new Ponto(6,2),new Ponto(10,2),new Ponto(10,5)};
        assertEquals("Triangulo: [(1,2), (3,2), (2,5)]",new Triangulo(pontosA).moveCentroid(2,3).toString());
        assertEquals("Triangulo: [(0,4), (2,4), (1,7)]",new Triangulo(pontosA).moveCentroid(1,5).toString());
        assertEquals("Triangulo: [(6,6), (8,6), (7,9)]",new Triangulo(pontosA).moveCentroid(7,7).toString());
        assertEquals("Triangulo: [(0,0), (4,0), (4,3)]",new Triangulo(pontosB).moveCentroid(2,1).toString());
        assertEquals("Triangulo: [(8,3), (12,3), (12,6)]",new Triangulo(pontosB).moveCentroid(10,4).toString());

    }

}
