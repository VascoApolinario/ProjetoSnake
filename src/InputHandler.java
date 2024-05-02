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
        if (keyCode == KeyEvent.VK_UP) {
            snake.rotate(90);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            snake.rotate(270);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            snake.rotate(180);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            snake.rotate(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}