import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Direction:
// (0) -> para direita
// (90) -> para cima
// (180) -> para esquerda
// (270) -> para baixo
class SnakeTest {
    @Test
    void moveTest() {
        Snake snake = new Snake(20, 0, new Ponto(200,200));
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        ArrayList<Ponto> positions = new ArrayList<>();
        positions.add(snake.getHead().getCentroide());
        for (Quadrado q : snake.getTail()) {
            positions.add(q.getCentroide());
        }

        snake.move();

        ArrayList<Ponto> Newpositions = new ArrayList<>();
        Newpositions.add(snake.getHead().getCentroide());
        for (Quadrado q : snake.getTail()) {
            Newpositions.add(q.getCentroide());
        }
        ArrayList<Boolean> check = new ArrayList<>();
        int i = 0;
        for (Ponto p : positions) {
            check.add(p.getX() < Newpositions.get(i).getX() && (p.getY() == Newpositions.get(i).getY()));
            i++;
        }

        for (Boolean b : check) {
            assertTrue(b);
        }
    }

    @Test
    void rotateTest() {
        Snake snake = new Snake(2, 0, new Ponto(2,5));  //a cobra n  pode virar se receber uma direção que faz 180ª com a direção atual (so pode 90º)
        snake.rotate(180);
        assertEquals(0,snake.getDirection());

        snake.rotate(90);
        assertEquals(90,snake.getDirection());

        snake.rotate(270);
        assertEquals(90,snake.getDirection());

        snake.rotate(180);
        assertEquals(180,snake.getDirection());

        snake.rotate(0);
        assertEquals(180,snake.getDirection());

        snake.rotate(270);
        assertEquals(270,snake.getDirection());

        snake.rotate(90);
        assertEquals(270,snake.getDirection());

        snake.rotate(0);
        assertEquals(0,snake.getDirection());

        snake.rotate(270);
        assertEquals(270,snake.getDirection());

        snake.rotate(180);
        assertEquals(180,snake.getDirection());

        snake.rotate(90);
        assertEquals(90,snake.getDirection());

        snake.rotate(0);
        assertEquals(0,snake.getDirection());
    }

    @Test
    void growTest() {    //Neste teste criamos uma cobra e dps vamos dando grow() à cobra e verificamos se o size da tail aumenta. As coordenadas do quadrado adicionado à tail será igual às coordenadas da ultima tail ( ou head se n houver tail ainda). Na pratica a tail adicionada só será "visivel" apos a cobra dar move.
        Snake snake = new Snake(2, 0, new Ponto(2,5));
        for (int i = 0; i < 6;i++)
        {
            snake.grow();
            assertTrue(snake.getTail().size() == i+1);
            snake.move();
            int headSize = (int) snake.getHead().sideLength();
            int headCentroidX = (int) snake.getHead().getCentroide().getX();
            for(int j = 0; j < snake.getTail().size(); j++)
            {
                int minX = headCentroidX - headSize/2 - headSize*(j+1);
                int maxX = headCentroidX - headSize/2 - headSize*(j);
                String expectedString = String.format("Quadrado: [(%d,4), (%d,4), (%d,6), (%d,6)]",minX,maxX,maxX,minX);
                assertEquals(expectedString,snake.getTail().get(j).toString());
            }
        }
    }

    @Test
    void eatTest()
    {

    }

    @Test
    void collisionWithTailTest(){ //para testar isto temos que ter uma cobra com tail > 5 (i guess) e faze-la andar num caminho que se suicide

    }

    @Test
    void collisionWithObstacleTest(){

    }


}

