import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {


    @Test
    void testFormat() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", true, 90);
        assert(obs1.getPoligono().equals(new Poligono("Poligono 0 0 0 4 4 4 4 0")));
    }

    @Test
    void testUpdate() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", true, 90);
        obs1.update();
        assert(obs1.getPoligono().equals(new Poligono("Poligono 0 0 0 4 4 4 4 0").rotate(90)));
    }


    @Test
    void testisDinamico() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", true, 90);
        Obstacle obs2 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", false, 90);
        assert(obs1.isDinamico());
        assertFalse(obs2.isDinamico());
    }

    @Test
    void testRotate() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", true, 90);
        obs1.rotate(obs1.getDegree());
        assert(obs1.getPoligono().equals(new Poligono("Poligono 0 0 0 4 4 4 4 0").rotate(90)));
    }

    @Test
    void testTipo() {
        Obstacle obs1 = new Obstacle("Poligono 4 0 0 0 4 4 4 4 0", true, 90);
        assertEquals("Obstaculo", obs1.tipo());
    }

    @Test
    void testgetPoligono() {
        Obstacle obs1 = new Obstacle("Poligono 4 0 0 0 4 4 4 4 0", true, 90);
        Poligono p = obs1.getPoligono();
        assert(p.equals(obs1.getPoligono()));
    }

    @Test
    void testgetDegree() {
        Obstacle obs1 = new Obstacle("Poligono 4 0 0 0 4 4 4 4 0", true, 90);
        assertEquals(90, obs1.getDegree());
    }

}