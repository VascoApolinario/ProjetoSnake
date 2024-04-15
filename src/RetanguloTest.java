import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetanguloTest {
    @Test
    public void testRotate(){
        Ponto[] pontosA = {new Ponto(1,1),new Ponto(3,1),new Ponto(3,5),new Ponto(1,5)};
        Ponto[] pontosB = {new Ponto(1,2),new Ponto(3,2),new Ponto(3,4),new Ponto(1,4)};
        assertEquals("Retangulo: [(4,2), (4,4), (0,4), (0,2)]",new Retangulo(pontosA).rotate(90).toString());
        assertEquals("Retangulo: [(3,3), (3,1), (7,1), (7,3)]",new Retangulo(pontosA).rotate(-90,3,1).toString());
        assertEquals("Retangulo: [(2,2), (3,3), (2,4), (1,3)]",new Retangulo(pontosB).rotate(45).toString());

    }

    @Test
    public void testMove1(){
        Ponto[] pontosA = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,3),new Ponto(1,3)};
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(3,1),new Ponto(3,5),new Ponto(1,5)};
        Ponto[] pontosC = {new Ponto(6,1),new Ponto(4,2),new Ponto(5,4),new Ponto(7,3)};
        assertEquals("Retangulo: [(13,8), (17,8), (17,10), (13,10)]",new Retangulo(pontosA).move(12,7).toString());
        assertEquals("Retangulo: [(0,0), (4,0), (4,2), (0,2)]",new Retangulo(pontosA).move(-1,-1).toString());
        assertEquals("Retangulo: [(0,31), (2,31), (2,35), (0,35)]",new Retangulo(pontosB).move(-1,30).toString());
        assertEquals("Retangulo: [(101,1), (103,1), (103,5), (101,5)]",new Retangulo(pontosB).move(100,0).toString());
        assertEquals("Retangulo: [(2,0), (0,1), (1,3), (3,2)]",new Retangulo(pontosC).move(-4,-1).toString());


    }


    @Test
    public void testMove2(){
        Ponto[] pontosA = {new Ponto(1,1),new Ponto(5,1),new Ponto(5,3),new Ponto(1,3)};
        Ponto[] pontosB = {new Ponto(1,1),new Ponto(3,1),new Ponto(3,5),new Ponto(1,5)};
        Ponto[] pontosC = {new Ponto(6,1),new Ponto(4,2),new Ponto(5,4),new Ponto(7,3)};
        assertEquals("Retangulo: [(1,2), (5,2), (5,4), (1,4)]",new Retangulo(pontosA).moveCentroid(3,3).toString());
        assertEquals("Retangulo: [(3,2), (7,2), (7,4), (3,4)]",new Retangulo(pontosA).moveCentroid(5,3).toString());
        assertEquals("Retangulo: [(6,2), (8,2), (8,6), (6,6)]",new Retangulo(pontosB).moveCentroid(7,4).toString());
        assertEquals("Retangulo: [(0,3), (2,3), (2,7), (0,7)]",new Retangulo(pontosB).moveCentroid(1,5).toString());
        assertEquals("Retangulo: [(2,0), (0,1), (1,3), (3,2)]",new Retangulo(pontosC).moveCentroid(1,1).toString());
    }



}