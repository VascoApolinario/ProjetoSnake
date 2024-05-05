import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private Snake snake;

    public InputHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            snake.rotate(90);
            snake.alive = SnakeStatus.ALIVE;
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            snake.rotate(270);
            snake.alive = SnakeStatus.ALIVE;
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            snake.rotate(180);
            snake.alive = SnakeStatus.ALIVE;
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            snake.rotate(0);
            snake.alive = SnakeStatus.ALIVE;
        }
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
