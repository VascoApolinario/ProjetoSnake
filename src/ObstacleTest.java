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
    void testSpawn() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", new Ponto(0,0), true, 90);
        assert(obs1.getPoligono().getCentroide().equals(new Ponto(0,0)));
    }

    @Test
    void testRotate() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", true, 90);
        obs1.rotate(obs1.getDegree());
        assert(obs1.getPoligono().equals(new Poligono("Poligono 0 0 0 4 4 4 4 0").rotate(90)));
    }

    @Test
    void testTipo() {
        Obstacle obs1 = new Obstacle("Poligono 0 0 0 4 4 4 4 0", true, 90);
        assertEquals("Obstaculo", obs1.tipo());
    }

}