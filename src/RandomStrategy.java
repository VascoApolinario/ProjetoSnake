import java.util.Random;
/**
 * A classe RandomStrategy implementa a interface AutoSnake.
 * Esta estratégia faz com que a cobra se mova de forma aleatória no jogo.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.0 09/05/2024
 */
public class RandomStrategy implements AutoSnake {

    /**
     * Atualiza o estado da cobra no jogo.
     * A cobra tenta evitar obstáculos e, em seguida, decide aleatoriamente se deve ou não girar.
     * @param snake a cobra a ser atualizada
     * @param background o fundo do jogo
     */
    public void update(Snake snake, Background background)
    {
        snake.setStatus(Status.ALIVE);
        Random random = new Random();
        avoidObstacle(snake,background.getGrid());
        if(random.nextBoolean())
        {
            rotate(snake,background);
        }

    }
    /**
     * Faz a cobra girar numa direção aleatória.
     * A cobra só irá girar se a célula na direção desejada não for perigosa.
     * @param snake a cobra a ser girada
     * @param background o fundo do jogo
     */
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
