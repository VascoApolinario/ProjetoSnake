
public interface AutoSnake {
    void Start(Snake snake,Background background);
    default void avoidObstacle(Snake snake,Grid grid){
        int row = grid.returnRowFromPoint(snake.getHead().getCentroide());
        int col = grid.returnColFromPoint(snake.getHead().getCentroide());
        if(snake.getDirection() == 0)
        {
            if(grid.getCells()[row][col+1].isDangerous())
            {
                if(grid.getCells()[row-1][col].isDangerous())
                {
                    snake.rotate(270);
                }
                else
                    snake.rotate(90);
            }
        }
        else if(snake.getDirection() == 180)
        {
            if(grid.getCells()[row][col-1].isDangerous())
            {

                if(grid.getCells()[row-1][col].isDangerous())
                {
                    snake.rotate(270);
                }
                else
                    snake.rotate(90);
            }

        } else if (snake.getDirection() == 90) {
            if(grid.getCells()[row-1][col].isDangerous())
            {
                if(grid.getCells()[row][col-1].isDangerous())
                {
                    snake.rotate(0);
                }
                else
                    snake.rotate(180);
            }

        }
        else
        {
            if(grid.getCells()[row+1][col].isDangerous())
            {
                if(grid.getCells()[row][col-1].isDangerous())
                {
                    snake.rotate(0);
                }
                else
                    snake.rotate(180);
            }

        }

    }
}
