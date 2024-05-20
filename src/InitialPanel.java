import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classe que contém o painel inicial do jogo onde podem ser configuradas as definições de inicialização.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @Version 1.0 04/05/2024
 */

public class InitialPanel extends JPanel implements ActionListener {

    /**
     * Janela JFrame onde o painel inicial é apresentado
     */
    private JFrame janela;

    /**
     * Botão para iniciar o jogo
     */
    private JButton startGameButton;

    /**
     * Campo de texto onde o jogador insere o seu nome
     */
    private JTextField playernameField;

    /**
     * Nome do jogador
     */
    private String playerName;

    /**
     * Nível selecionado pelo jogador
     */
    private String selectedLevel;

    /**
     * Booleano que indica se o jogo deve ser iniciado
     */
    public boolean startGame;

    /**
     * Booleano que indica se o jogador selecionou o modo automático
     */
    private boolean autoSnake;

    /**
     * Estratégia de movimento automático selecionada pelo jogador
     */
    private int autoSnakeStrat;

    /**
     * Label que informa o jogador sobre as restrições do nome
     */
    private JLabel nameRestriction;

    /**
     * Area de texto onde é apresentada a leaderboard
     */
    private JTextArea printLeaderboard;

    /**
     * Spinner para subir e descer para ver os jogadores apresentados na leaderboard
     */
    private JSpinner leaderboardSpinner;

    /**
     * ComboBox para selecionar a estratégia de movimento automático
     */
    private JComboBox<String> autoSnakeStratComboBox;

    /**
     * ComboBox para selecionar o nível
     */
    private JComboBox<String> levelComboBox;

    /**
     * ComboBox para selecionar o modo de renderização
     */
    private JComboBox<String> renderComboBox;
    /**
     *  ComboBox para selecionar o modo gráfico
     */
    private JComboBox<String> graphicComboBox;

    /**
     * Booleano que indica se o jogador selecionou o modo de renderização com preenchimento
     */
    private boolean renderFill;


    /**
     *  Booleano que inidica se o jogador selecionou o modo grafico ou textual
     */

    private boolean graphicsMode;

    /**
     * Construtor do Painel inicial, recebe uma cópia da leaderboard para poder apresentá-la
     * @param leaderboardCopy cópia da leaderboard
     */
    public InitialPanel(Leaderboard leaderboardCopy){
        startGame = false;
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);
        janela.setLayout(new FlowLayout());
        janela.setLocationRelativeTo(null);
        janela.setTitle("OBJECT ORIENTED PROGRAMMED SNAKE");
        playerName = "";

        JLabel insertUserName = new JLabel("INSERT PLAYER NAME: ");
        insertUserName.setFont(new Font("Courier New", Font.BOLD, 16));
        nameRestriction = new JLabel("NOTE: Your name should not contain space characters!!");
        JCheckBox activateAutoSnake = new JCheckBox("Enable Automatic Mode");
        activateAutoSnake.setFont(new Font("Courier New", Font.PLAIN, 25));
        activateAutoSnake.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    autoSnake = true;
                } else {
                    autoSnake = false;
                }
            }
        });
        activateAutoSnake.setSelected(true);
        autoSnake = true;

        nameRestriction.setFont(new Font("Courier New", Font.BOLD, 12));


        playernameField = new JTextField();
        playernameField.setPreferredSize(new Dimension(225, 35));
        playernameField.setFont(new Font("Courier New", Font.PLAIN, 18));

        startGameButton = new JButton("START GAME");
        startGameButton.addActionListener(this);
        startGameButton.setFont(new Font("Courier New", Font.PLAIN, 18));

        janela.add(insertUserName);
        janela.add(playernameField);
        janela.add(startGameButton);
        janela.add(nameRestriction);
        janela.add(activateAutoSnake);

        JLabel selectASstrat = new JLabel("AutoSnake Strategy: ");
        selectASstrat.setFont(new Font("Courier New", Font.PLAIN, 30));

        String[] autoSnakeStrats = new String[]{"PathFinding", "Random"};
        autoSnakeStratComboBox = new JComboBox<>(autoSnakeStrats);
        autoSnakeStratComboBox.addActionListener(this);
        autoSnakeStratComboBox.setFont(new Font("Courier New", Font.PLAIN, 18));
        autoSnakeStratComboBox.setSelectedIndex(0);

        JLabel selectLevel = new JLabel("Select Level: ");
        selectLevel.setFont(new Font("Courier New", Font.PLAIN, 30));

        String[] levels = new String[]{"level1", "level2", "level3", "level4", "level5", "level6", "level7", "level8", "level9", "level10"};

        levelComboBox = new JComboBox<>(levels);
        levelComboBox.addActionListener(this);
        levelComboBox.setFont(new Font("Courier New", Font.PLAIN, 18));
        levelComboBox.setSelectedIndex(0);

        String[] renderModes = new String[]{"Border", "Fill"};

        renderComboBox = new JComboBox<>(renderModes);
        renderComboBox.addActionListener(this);
        renderComboBox.setFont(new Font("Courier New", Font.PLAIN, 18));
        renderComboBox.setSelectedIndex(0);
        renderFill = false;

        JLabel selectRenderMode = new JLabel("Select Render Mode: ");
        selectRenderMode.setFont(new Font("Courier New", Font.PLAIN, 30));

        JLabel selectGraphicMode = new JLabel("Select Graphic Mode: ");
        selectGraphicMode.setFont(new Font("Courier New", Font.PLAIN, 30));

        String[] graphicModes = new String[]{"Graphic", "Textual"};

        graphicComboBox = new JComboBox<>(graphicModes);
        graphicComboBox.addActionListener(this);
        graphicComboBox.setFont(new Font("Courier New", Font.PLAIN, 18));
        graphicComboBox.setSelectedIndex(0);
        graphicsMode = true;


        janela.add(selectASstrat);
        janela.add(autoSnakeStratComboBox);
        janela.add(selectLevel);
        janela.add(levelComboBox);
        janela.add(selectRenderMode);
        janela.add(renderComboBox);
        janela.add(selectGraphicMode);
        janela.add(graphicComboBox);

        JLabel leaderboardText = new JLabel("LEADERBOARD");
        leaderboardText.setFont(new Font("Courier New", Font.BOLD, 70));


        SpinnerModel model = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1) {
            @Override
            public Object getNextValue() {
                Object value = super.getNextValue();
                return (value instanceof Integer) ? value : getMinimum();
            }

            @Override
            public Object getPreviousValue() {
                Object value = super.getPreviousValue();
                return (value instanceof Integer) ? value : getMinimum();
            }
        };

        JLabel showTopPlayers = new JLabel("Show the top ");
        showTopPlayers.setFont(new Font("Courier New", Font.PLAIN, 20));

        printLeaderboard = new JTextArea();
        printLeaderboard.setFont(new Font("Courier New", Font.BOLD, 16));
        printLeaderboard.setEditable(false);

        leaderboardSpinner = new JSpinner(model);
        leaderboardSpinner.setPreferredSize(new Dimension(50,25));
        leaderboardSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                printLeaderboard.setText(leaderboardCopy.printLeaderboard((Integer) leaderboardSpinner.getValue()));
            }

        });
        leaderboardSpinner.setValue(10);

        JLabel players = new JLabel(" players.");
        players.setFont(new Font("Courier New", Font.PLAIN, 20));


        JScrollPane printLeaderboardScroll = new JScrollPane(printLeaderboard);
        printLeaderboardScroll.setPreferredSize(new Dimension(550,200));
        printLeaderboardScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        janela.add(leaderboardText);
        janela.add(showTopPlayers);
        janela.add(leaderboardSpinner);
        janela.add(players);
        janela.add(printLeaderboardScroll);
        janela.setVisible(true);
    }

    /**
     * Processa o que é para acontecer no painel incial caso ocorra algum evento "e"
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGameButton)
        {
            playerName = playernameField.getText();
            if(this.playerName.contains(" "))
            {
                nameRestriction.setText("ERROR: Make sure your name doesn't contain space characters!");
            }
            else if(this.playerName.isEmpty())
            {
                nameRestriction.setText("ERROR: Please insert player name before starting the game!");
            }
            else {
                startGame = true;
                janela.dispose();
            }
        }
        if(e.getSource() == levelComboBox)
        {
            selectedLevel = (String) levelComboBox.getSelectedItem();
        }
        if(e.getSource() == renderComboBox)
        {
            renderFill = renderComboBox.getSelectedIndex() != 0;
        }
        if(e.getSource() == autoSnakeStratComboBox)
        {
            autoSnakeStrat = autoSnakeStratComboBox.getSelectedIndex();
        }
        if(e.getSource() == graphicComboBox)
        {
            graphicsMode = graphicComboBox.getSelectedIndex() == 0;
        }
    }

    /**
     * Devolve o nível selecionado pelo jogador
     * @pos return this.selectedLevel
     * @return o nível selecionado pelo jogador
     */
    public String getSelectedLevel() {
        return selectedLevel;
    }

    /**
     * Devolve o nome do jogador selecionado pelo jogador
     * @pos return this.playerName
     * @return o nome do jogador selecionado pelo jogador
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Devolve se o jogador selecionou para iniciar o jogo
     * @return se o jogador selecionou para iniciar o jogo
     */
    public boolean getStartGameStatus() {
        return startGame;
    }

    /**
     * Retorna se o jogador selecionou para iniciar no modo automático
     * @pos result = autoSnake
     * @return se o jogador selecionou para iniciar no modo automático
     */
    public boolean getAutoSnake() {
        return autoSnake;
    }

    /**
     * Método que devolve se o jogador selecionou para renderizar o jogo com preenchimento
     * @return se o jogador selecionou para renderizar o jogo com preenchimento
     */
    public boolean getRenderFill()
    {
        return renderFill;
    }

    /**
     * Método que devolve a estratégia de movimento automático selecionada pelo jogador
     * @return a estratégia de movimento automático selecionada pelo jogador
     */
    public int getAutoSnakeStrat()
    {
        return autoSnakeStrat;
    }

    public boolean getGraphicsMode(){
        return graphicsMode;
    }

}