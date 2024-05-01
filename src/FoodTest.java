import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    @Test
    void Spawntest() { //precisamos criar grid para testar
        Grid grid = new Grid(3,3,1);
        Obstacle obstacle1 = new Obstacle("Quadrado 0 0 1 0 1 1 0 1",false,0);
        Obstacle obstacle2 = new Obstacle("Quadrado 0 1 1 1 1 2 0 2",false,0);
        Obstacle obstacle3 = new Obstacle("Quadrado 0 2 1 2 1 3 0 3",false,0);
        Obstacle obstacle4 = new Obstacle("Quadrado 1 0 2 0 2 1 1 1",false,0);
        Obstacle obstacle5 = new Obstacle("Quadrado 2 0 3 0 3 1 2 1",false,0);
        Obstacle obstacle6 = new Obstacle("Quadrado 1 2 2 2 2 3 1 3",false,0);
        Obstacle obstacle7 = new Obstacle("Quadrado 2 2 3 2 3 3 2 3",false,0);
        Player player = new Player("jogador");
        CircleFood food = new CircleFood(1.5,1.5,0.5);
        food.consumir(grid);
        assertEquals("(2.5,1.5)",food.getCirculo().getCenter());
        Obstacle obstacle8 = new Obstacle("Quadrado 1 1 2 1 2 2 1 2",false,0);
        food.consumir(grid);
        assertEquals(Integer.MAX_VALUE,player.getScore());

    }
}