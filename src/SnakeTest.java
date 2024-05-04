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
    /*
    @Test
    void moveTest() {  //JÁ TÁ A FUNCIONAR
        Snake snake = new Snake(20, 0, new Ponto(200,200));
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        ArrayList<Ponto> positions = new ArrayList<>();
        positions.add(snake.getHead().getCentroide());
        for (Quadrado q : snake.getTail().reversed()) {
            positions.add(q.getCentroide());
        }
        snake.move();

        ArrayList<Ponto> Newpositions = new ArrayList<>();
        Newpositions.add(snake.getHead().getCentroide());
        for (Quadrado q : snake.getTail().reversed()) {
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

    @Test     //JÁ TÁ A FUNCIONAR!!!
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

    @Test //JÁ TÁ A FUNCIONAR
    void growTest() {    //Neste teste criamos uma cobra e dps vamos dando grow() à cobra e verificamos se o size da tail aumenta. As coordenadas do quadrado adicionado à tail será igual às coordenadas da ultima tail ( ou head se n houver tail ainda). Na pratica a tail adicionada só será "visivel" apos a cobra dar move.
        Snake snake = new Snake(2, 0, new Ponto(2,5));
        for (int i = 0; i < 6;i++)
        {
            snake.grow();
            snake.move();
            assertEquals(i+1,snake.getTail().size());
            int headSize = (int) snake.getHead().getSide();
            int headCentroidX = (int) snake.getHead().getCentroide().getX();
            for(int j = 0; j < snake.getTail().size(); j++)
            {
                int minX = 1 + headSize*(j);
                int maxX = 1 + headSize*(j+1);
                String expectedString = String.format("Quadrado: [(%d,4), (%d,6), (%d,6), (%d,4)]",minX,minX,maxX,maxX);
                assertEquals(expectedString,snake.getTail().get(j).toString());
            }
        }
    }

    @Test
    void eatTest()
    {

    }

    @Test
    void collisionWithTailTest(){ //para testar isto temos que ter uma cobra com tail > 5 (i guess) e faze-la andar num caminho que se suicide  TÁ A FUNCIONAR
        Snake snake = new Snake(2, 0, new Ponto(4,2));
        snake.grow();
        snake.move();
        assertFalse(snake.collisionWithTail());
        snake.grow();
        snake.move();
        assertFalse(snake.collisionWithTail());
        snake.grow();
        snake.move();
        assertFalse(snake.collisionWithTail());
        snake.rotate(270);
        snake.grow();
        snake.move();
        assertFalse(snake.collisionWithTail());
        snake.rotate(180);
        snake.grow();
        snake.move();
        assertFalse(snake.collisionWithTail());
        snake.rotate(90);
        snake.grow();
        snake.move();
        assertTrue(snake.collisionWithTail());
    }

    @Test
    void collisionWithObstacleTest(){
        Snake snake = new Snake(2, 0, new Ponto(4,2));
        Obstacle obstacle = new Obstacle("Triangulo 6 1 10 1 8 4",false,0);
        assertFalse(snake.collisionWithObstacle(obstacle));
        snake.move();
        assertTrue(snake.collisionWithObstacle(obstacle));
    }

*/
}

