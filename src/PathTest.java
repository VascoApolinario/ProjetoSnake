import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void intersetaTest() {
        Ponto[] pontosA = {new Ponto(0,0),new Ponto(1,2),new Ponto(4,2),new Ponto(5,1),new Ponto(5,4)};

        Ponto[] pontosB = {new Ponto(2,1),new Ponto(3,1),new Ponto(3,2),new Ponto(2,2)};
        Ponto[] pontosC = {new Ponto(5,2),new Ponto(5,3),new Ponto(6,3),new Ponto(6,2)};
        Ponto[] pontosD = {new Ponto(2,1),new Ponto(3,1),new Ponto(3,3),new Ponto(2,3)};

        assertEquals(0,new Path(pontosA).interseta(new Poligono(pontosB)));
        assertEquals(0,new Path(pontosA).interseta(new Poligono(pontosC)));
        assertEquals(1,new Path(pontosA).interseta(new Poligono(pontosD)));
    }
}