import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável por representar o frame do jogo.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */
public class GameFrame extends JFrame {

    /**
     * Construtor da classe GameFrame
     * @param graficos interface gráfica do jogo
     */
    public GameFrame(IGraficos graficos) {
        this.add((Component) graficos);
        this.setTitle("SNAKE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
