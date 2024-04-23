package FigurasGeo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CirculoTest {

    @Test
    void collidesWithPolygonTest() {
        Circulo c1 = new Circulo(new Ponto(4,3),1);
        Ponto[] pontosA = {new Ponto(5.5,4.5),new Ponto(8,4.5),new Ponto(8,6),new Ponto(5.5,6)};
        Ponto[] pontosB = {new Ponto(5,2),new Ponto(5,4),new Ponto(8,4),new Ponto(8,2)};
        Ponto[] pontosC = {new Ponto(10,5),new Ponto(12,5),new Ponto(12,7),new Ponto(10,7)};
        assertFalse(c1.collidesWithPolygon(new Poligono(pontosA)));
        assertTrue(c1.collidesWithPolygon(new Poligono(pontosB)));
        assertFalse(c1.collidesWithPolygon(new Poligono(pontosC)));

    }

    @Test
    void envolvingSquareCollidesTest()
    {
        Circulo c1 = new Circulo(new Ponto(4,3),1);
        Ponto[] pontosA = {new Ponto(5.5,4.5),new Ponto(8,4.5),new Ponto(8,6),new Ponto(5.5,6)};
        Ponto[] pontosB = {new Ponto(5,2),new Ponto(5,4),new Ponto(8,4),new Ponto(8,2)};
        Ponto[] pontosC = {new Ponto(10,5),new Ponto(12,5),new Ponto(12,7),new Ponto(10,7)};
        assertTrue(c1.envolvingSquareCollides(new Poligono(pontosA)));
        assertTrue(c1.envolvingSquareCollides(new Poligono(pontosB)));
        assertFalse(c1.envolvingSquareCollides(new Poligono(pontosC)));

    }

    @Test
    void collidesWithSegmentTest() {
        Circulo c1 = new Circulo(new Ponto(4,3),1);
        Segmento s1 = new Segmento(new Ponto(1,4),new Ponto(2,6));
        Segmento s2 = new Segmento(new Ponto(1,3),new Ponto(8,1));
        assertFalse(c1.collidesWithSegment(s1));
        assertTrue(c1.collidesWithSegment(s2));

    }
}