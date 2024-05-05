
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    private Background background;
    private Timer timer;
    private boolean running;
    private Leaderboard leaderboard;

    public Game() {
        int Width = 800;
        int Height = 600;
        background = new Background(Width,Height);
        timer = new Timer(100, this);
        running = true;
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
            this.restart();
        }
    }

    public void restart() {
        if ((background.getSnake().alive.equals(SnakeStatus.DEAD)) && background.isGameOver()) {
            System.out.println("Game Over!");
            if(background.getPlayer().getScore() > background.getPlayer().getBestScore())
            {
                background.getPlayer().setBestScore(background.getPlayer().getScore());
            }
            background.getPlayer().setScore(0);
            background.reset();
        }
    }


}
