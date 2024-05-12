import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @Test
    void moveTest() {  //Dá erro
        Grid grid = new Grid(200,200,20);
        Snake snake = new Snake(20,grid.getCells()[1][1],0);
        snake.grow();
        snake.move(grid);
        snake.grow();
        snake.move(grid);
        snake.grow();
        snake.move(grid);
        ArrayList<Ponto> positions = new ArrayList<>();
        positions.add(snake.getHead().getCentroide());
        for (Quadrado q : snake.getTail().reversed()) {
            positions.add(q.getCentroide());
        }
        snake.move(grid);

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
        Grid grid = new Grid(200,200,20);
        Snake snake = new Snake(20, grid.getCells()[5][5],0);  //a cobra n  pode virar se receber uma direção que faz 180ª com a direção atual (so pode 90º)
        snake.rotate(180);
        assertEquals(0,snake.getDirection());
        snake.move(grid);
        snake.rotate(90);
        assertEquals(90,snake.getDirection());
        snake.move(grid);
        snake.rotate(270);
        assertEquals(90,snake.getDirection());
        snake.move(grid);
        snake.rotate(180);
        assertEquals(180,snake.getDirection());
        snake.move(grid);
        snake.rotate(0);
        assertEquals(180,snake.getDirection());
        snake.move(grid);
        snake.rotate(270);
        assertEquals(270,snake.getDirection());
        snake.move(grid);
        snake.rotate(90);
        assertEquals(270,snake.getDirection());
        snake.move(grid);
        snake.rotate(0);
        assertEquals(0,snake.getDirection());
        snake.move(grid);
        snake.rotate(270);
        assertEquals(270,snake.getDirection());
        snake.move(grid);
        snake.rotate(180);
        assertEquals(180,snake.getDirection());
        snake.move(grid);
        snake.rotate(90);
        assertEquals(90,snake.getDirection());
        snake.move(grid);
        snake.rotate(0);
        assertEquals(0,snake.getDirection());
    }

    @Test //ta a dar erro
    void growTest() {    //Neste teste criamos uma cobra e dps vamos dando grow() à cobra e verificamos se o size da tail aumenta. As coordenadas do quadrado adicionado à tail será igual às coordenadas da ultima tail ( ou head se n houver tail ainda). Na pratica a tail adicionada só será "visivel" apos a cobra dar move.
        Grid grid = new Grid(200,200,10);
        Snake snake = new Snake(10,grid.getCells()[1][1],0);
        for (int i = 0; i < 6;i++)
        {
            snake.grow();
            snake.move(grid);
            assertEquals(i+1,snake.getTail().size());
            int headSize = (int) snake.getHead().getSide();
            for(int j = 0; j < snake.getTail().size(); j++)
            {
                int minX = headSize + headSize*(j);
                int maxX = headSize + headSize*(j+1);
                String expectedString = String.format("Quadrado: [(%d,10), (%d,20), (%d,20), (%d,10)]",minX,minX,maxX,maxX);
                assertEquals(expectedString,snake.getTail().get(j).toString());
            }
        }
    }


    @Test
    void collisionWithTailTest(){//para testar isto temos que ter uma cobra com tail > 5 (i guess) e faze-la andar num caminho que se suicide  TÁ A FUNCIONAR
        Grid grid = new Grid(200,200,20);
        Snake snake = new Snake(20,grid.getCells()[0][0],0);
        snake.grow();
        snake.move(grid);
        assertFalse(snake.collisionWithTail());
        snake.grow();
        snake.move(grid);
        assertFalse(snake.collisionWithTail());
        snake.grow();
        snake.move(grid);
        assertFalse(snake.collisionWithTail());
        snake.rotate(270);
        snake.grow();
        snake.move(grid);
        assertFalse(snake.collisionWithTail());
        snake.rotate(180);
        snake.grow();
        snake.move(grid);
        assertFalse(snake.collisionWithTail());
        snake.rotate(90);
        snake.grow();
        snake.move(grid);
        assertTrue(snake.collisionWithTail());
    }

    @Test
    void collisionWithObstacleTest(){
        Grid grid = new Grid(200,200,10);
        Snake snake = new Snake(10,grid.getCells()[1][1],0);
        Obstacle obstacle = new Obstacle("Poligono 3 25 15 35 15 35 25",false,0);
        grid.update(obstacle);
        assertFalse(snake.checkCollisions(grid));
        snake.move(grid);
        assertTrue(snake.getStatus().equals(Status.DEAD));
    }


}

