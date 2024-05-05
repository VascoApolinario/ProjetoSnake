
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener {
    private Background background;
    private Timer timer;
    private boolean running;
    private Leaderboard leaderboard;
    private InputHandler inputHandler;
    private GameFrame gameFrame;
    private IGraficos graficos;
    private InitialPanel painelInicial;

    public Game() {
        int Width = 800;
        int Height = 600;
        painelInicial = new InitialPanel();
        while (!painelInicial.startGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        background = new Background(Width, Height, painelInicial.getPlayerName()); //MAIS TARDE TEMOS DE ADICIONAR UM ARGUMENTO QUE DIGA AS POSIÇOES CERTAS DA GRID E DOS OBSTACULOS, ETC.
        inputHandler = new InputHandler(this);
        this.graficos = new Grafica(Width, Height, this.background, inputHandler);
        this.gameFrame = new GameFrame(this.graficos);
        timer = new Timer(100, this);
        running = true;
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        leaderboard = new Leaderboard("leaderboard.txt");
        leaderboard.printLeaderboard();
        StartGame();
        leaderboard.update(background.getPlayer());

        while(true)
        {
            updateLeaderBoard();
        }

    }

    public void updateLeaderBoard()
    {
        if(background.getUpdateLeaderBoard())
        {
            background.setUpdateLeaderBoard(false);
            leaderboard.update(background.getPlayer());
        }
    }

    public void StartGame() {
        timer.start();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {

            background.updateAll();
            // Deteção de inputs
            // Draw dos objetos no background
        }
        this.graficos.repaint();
        //reset();
    }

    public void reset(){

        leaderboard.update(background.getPlayer());
        this.background = new Background(800,600, painelInicial.getPlayerName());
        this.graficos.setBG(this.background);
    }

    public Background getBackground() {
        return background;
    }

    public boolean isRunning() {
        return running;
    }
}
