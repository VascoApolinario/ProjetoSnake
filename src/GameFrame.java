import javax.swing.*;
import java.awt.*;

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
