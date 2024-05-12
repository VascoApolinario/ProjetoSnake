
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Classe que compõe o jogo e inicializa os diferentes painéis, encarrega se de juntar a informação visível com a não visivel.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @Version 1.0 21/04/2024
 */

public class Game implements ActionListener {
    private String level;
    private Background background;
    private Timer timer;
    private Leaderboard leaderboard;
    private InputHandler inputHandler;
    private GameFrame gameFrame;
    private IGraficos graficos;
    private InitialPanel painelInicial;

    /**
     * Contrutor da classe Game.
     */
    public Game() {
        leaderboard = new Leaderboard("leaderboard.txt");
        //leaderboard.printLeaderboard();
        painelInicial = new InitialPanel(leaderboard);
        while (!painelInicial.startGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //background = new Background(Width, Height, painelInicial.getPlayerName()); //MAIS TARDE TEMOS DE ADICIONAR UM ARGUMENTO QUE DIGA AS POSIÇOES CERTAS DA GRID E DOS OBSTACULOS, ETC.
        if(checkLevelFile())
            background = new Background(painelInicial.getSelectedLevel() + ".txt", painelInicial.getPlayerName(),painelInicial.getAutoSnake(),painelInicial.getAutoSnakeStrat());
        else
            background = new Background(800,600, painelInicial.getPlayerName(),painelInicial.getAutoSnake(),painelInicial.getAutoSnakeStrat());
        inputHandler = new InputHandler(this);
        this.graficos = new Textual(background.getWidth(), background.getHeight(), this.background, inputHandler, painelInicial.getRenderFill());
        this.gameFrame = new GameFrame(this.graficos);
        timer = new Timer(100, this);
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        StartGame();
        leaderboard.update(background.getPlayer());

    }

    /**
     * Metodo que verifica se o ficheiro de nível existe.
     * @return true se existir, false se não
     */
    public boolean checkLevelFile(){
        String level = this.painelInicial.getSelectedLevel();
        level = level + ".txt";
        File levelFile = new File(level);
        return levelFile.exists();
    }

    /**
     * Metodo que começa o jogo.
     */
    public void StartGame() {
        timer.start();
    }

    /**
     * Este método é chamado sempre que ocorre um evento de ação.
     * Atualiza todos os objetos do jogo, verifica se é necessário atualizar a tabela de classificação e repinta os gráficos.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        background.updateAll();
            // Deteção de inputs
            // Draw dos objetos no background
        if(background.getUpdateLeaderBoard())
        {
            background.setUpdateLeaderBoard(false);
            leaderboard.update(background.getPlayer());
        }
        this.graficos.repaint();
    }
    /**
     * Este método é usado para reiniciar o jogo.
     * Atualiza a tabela de classificação, cria um novo fundo e define o novo fundo nos gráficos.
     */
    public void reset(){

        leaderboard.update(background.getPlayer());
        if(checkLevelFile())
            background = new Background(painelInicial.getSelectedLevel() + ".txt", painelInicial.getPlayerName(),painelInicial.getAutoSnake(),painelInicial.getAutoSnakeStrat());
        else
            background = new Background(800,600, painelInicial.getPlayerName(),painelInicial.getAutoSnake(),painelInicial.getAutoSnakeStrat());
        this.graficos.setBG(this.background);
    }

    /**
     * Getter da classe Game que devolve o background.
     * @return background
     */
    public Background getBackground() {
        return background;
    }

}
