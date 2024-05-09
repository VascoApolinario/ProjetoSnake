import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe responsável por receber e administrar o input do usuário durante o jogo. implementa a interface KeyListener
 *  * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 *  * @version 1.0 09/05/2024
 */
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
            if(game.getBackground().getSnake().getStatus().equals(Status.START))
                game.getBackground().getSnake().setStatus(Status.ALIVE);
            game.getBackground().getSnake().rotate(90);

        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if(game.getBackground().getSnake().getStatus().equals(Status.START))
                game.getBackground().getSnake().setStatus(Status.ALIVE);
            game.getBackground().getSnake().rotate(270);
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            if(game.getBackground().getSnake().getStatus().equals(Status.START))
                game.getBackground().getSnake().setStatus(Status.ALIVE);
            game.getBackground().getSnake().rotate(180);
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            if(game.getBackground().getSnake().getStatus().equals(Status.START))
                game.getBackground().getSnake().setStatus(Status.ALIVE);
            game.getBackground().getSnake().rotate(0);
        }else if (keyCode == KeyEvent.VK_SPACE){
            if(game.getBackground().getGameOver())
                game.reset();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
