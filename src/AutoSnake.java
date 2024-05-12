/**
 *  Interface do modo automatico da Snake.
 *  @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 *  @version 1.0
 */
public interface AutoSnake {

    /**
     * Atualiza/Inicia (se não inicida) a cobra no jogo e chama os métodos que a controlam.
     * @param snake a cobra a ser iniciada
     * @param background o fundo do jogo
     */
    void update(Snake snake,Background background);

    /**
     * Este método é usado para evitar obstáculos no jogo.
     * A cobra verifica a célula à frente na direção em que anda.
     * Se a célula à frente for perigosa, a cobra tentará virar à direita ou à esquerda, dependendo da direção atual.
     * @param snake a cobra que está tentando evitar obstáculos
     * @param grid a grade do jogo
     */
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
