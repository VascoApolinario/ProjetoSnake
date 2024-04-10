package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PoligonoTest {

    @Test
    public void testPolygonIntercept0() {
        Ponto[] pontosA = {new Ponto(5,5),new Ponto(8,5),new Ponto(8,7),new Ponto(5,7)};
        Ponto[] pontosB = {new Ponto(7,1),new Ponto(9,1),new Ponto(9,3),new Ponto(7,3)};
        Ponto[] pontosC = {new Ponto(7,4),new Ponto(9,4),new Ponto(9,6),new Ponto(7,6)};
        Ponto[] pontosD = {new Ponto(1,1),new Ponto(3,1),new Ponto(5,2),new Ponto(2,3)};
        Ponto[] pontosE = {new Ponto(1,1),new Ponto(2,2),new Ponto(3,0)};
        Assertions.assertFalse(new Poligono(pontosA).polygonsIntercept(new Poligono(pontosB)));
        Assertions.assertTrue(new Poligono(pontosA).polygonsIntercept(new Poligono(pontosC)));
        Assertions.assertTrue(new Poligono(pontosD).polygonsIntercept(new Poligono(pontosE)));
    }
}