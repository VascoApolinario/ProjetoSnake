import FigurasGeo.Poligono;
import org.junit.jupiter.api.Test;

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

    }

    @Test
    void testSpawn() {

    }

    @Test
    void testRotate() {

    }

    @Test
    void testTipo() {

    }

}