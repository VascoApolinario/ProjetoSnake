import java.util.Random;

public class RandomStrategy implements AutoSnake {


    public void Start(Snake snake, Background background)
    {
        snake.setStatus(Status.ALIVE);
        Random random = new Random();
        avoidObstacle(snake,background.getGrid());
        if(random.nextBoolean())
        {
            rotate(snake,background);
        }

    }


    private void rotate(Snake snake, Background background)
    {
        int direction = snake.getRandomDirection();
        int SnakeRow = background.getGrid().returnRowFromPoint(snake.getHead().getCentroide());
        int SnakeCol = background.getGrid().returnColFromPoint(snake.getHead().getCentroide());
        if(direction == 0 && !background.getGrid().getCells()[SnakeRow][SnakeCol+1].isDangerous())
            snake.rotate(direction);
        else if (direction == 90 && !background.getGrid().getCells()[SnakeRow-1][SnakeCol].isDangerous()) {
            snake.rotate(direction);
        }
        else if (direction == 180 && !background.getGrid().getCells()[SnakeRow][SnakeCol-1].isDangerous() ) {
            snake.rotate(direction);
        }
        else if(direction == 270 && !background.getGrid().getCells()[SnakeRow+1][SnakeCol].isDangerous())
        {
            snake.rotate(direction);
        }
    }




}
