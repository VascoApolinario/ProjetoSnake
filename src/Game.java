
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements ActionListener {
    private Background background;
    private Timer timer;
    private boolean running;
    private Leaderboard leaderboard;

    public Game() {
        int Width = 800;
        int Height = 600;
        background = new Background(Width,Height,"Grafico");
        timer = new Timer(100, this);
        running = true;
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        leaderboard = new Leaderboard("leaderboard.txt");
        leaderboard.printLeaderboard();
        StartGame();
    }

    public void StartGame() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            background.updateAll();
            // Deteção de inputs
            // Draw dos objetos no background
        }
    }


}
