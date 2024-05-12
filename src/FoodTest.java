import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    @Test
    void Spawntest() { //precisamos criar grid para testar
        Grid grid = new Grid(40,40,10);
        Snake snake = new Snake(10,grid.getCells()[1][1],0);
        CircleFood food = new CircleFood(grid.getCells()[1][2],0.5,grid.getSquaresize());
        snake.move(grid);
        snake.eat(food,grid);
        assertEquals("(15,15)",food.getCirculo().getCenter().toString());
    }
}