import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private Game game;

    public InputHandler(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            if(!game.isRunning())
                game.setRunning(true);
            game.getBackground().getSnake().rotate(90);

        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if(!game.isRunning())
                game.setRunning(true);
            game.getBackground().getSnake().rotate(270);
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            if(!game.isRunning())
                game.setRunning(true);
            game.getBackground().getSnake().rotate(180);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            if(!game.isRunning())
                game.setRunning(true);
            game.getBackground().getSnake().rotate(0);
        }else if (keyCode == KeyEvent.VK_SPACE){
            game.reset();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
