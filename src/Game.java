
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    private boolean game;
    private Background background;
    private Timer timer;

    public Game() {
        int Width = 800;
        int Height = 600;
        background = new Background(Width,Height,"Grafico");
        timer = new Timer(1000, this);
        StartGame();
    }

    public void StartGame() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        while(game) {
            background.updateAll();
            // Deteção de inputs
            // Draw dos objetos no background
        }
    }
}
