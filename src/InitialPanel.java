import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class InitialPanel extends JPanel implements ActionListener {

    private JFrame janela;
    private JButton startGameButton;
    private JTextField playernameField;
    private String playerName;
    private String selectedLevel;
    public boolean startGame;
    public boolean autoSnake;
    private JLabel insertUserName;
    private JLabel nameRestriction;
    private JLabel leaderboardText;
    private JTextArea printLeaderboard;
    private JScrollPane printLeaderboardScroll;
    private JSpinner leaderboardSpinner;
    private JLabel showTopPlayers;
    private JLabel players;
    private JLabel selectLevel;
    private JComboBox<String> levelComboBox;
    private JCheckBox activateAutoSnake;

    public InitialPanel(Leaderboard leaderboardCopy){
        startGame = false;
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 500);
        janela.setLayout(new FlowLayout());
        janela.setLocationRelativeTo(null);
        janela.setTitle("OBJECT ORIENTED PROGRAMMED SNAKE");
        playerName = "";

        insertUserName = new JLabel("INSERT PLAYER NAME: ");
        insertUserName.setFont(new Font("Courier New", Font.BOLD, 16));
        nameRestriction = new JLabel("NOTE: Your name should not contain space characters!!");
        activateAutoSnake = new JCheckBox("Enable Automatic Mode");
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

        selectLevel = new JLabel("Select Level: ");
        selectLevel.setFont(new Font("Courier New", Font.PLAIN, 30));

        String[] levels = new String[]{"level1", "level2"};

        levelComboBox = new JComboBox<>(levels);
        levelComboBox.addActionListener(this);
        levelComboBox.setFont(new Font("Courier New", Font.PLAIN, 18));
        levelComboBox.setSelectedIndex(0);

        janela.add(selectLevel);
        janela.add(levelComboBox);








        leaderboardText = new JLabel("LEADERBOARD");
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

        showTopPlayers = new JLabel("Show the top ");
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

        players = new JLabel(" players.");
        players.setFont(new Font("Courier New", Font.PLAIN, 20));


        printLeaderboardScroll = new JScrollPane(printLeaderboard);
        printLeaderboardScroll.setPreferredSize(new Dimension(550,200));
        printLeaderboardScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        janela.add(leaderboardText);
        janela.add(showTopPlayers);
        janela.add(leaderboardSpinner);
        janela.add(players);
        janela.add(printLeaderboardScroll);
        //janela.add(printLeaderboardScroll);
        //janela.pack();
        janela.setVisible(true);
    }
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
    }

    public String getSelectedLevel() {
        return selectedLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getStartGameStatus() {
        return startGame;
    }

    public boolean getAutoSnake() {
        return autoSnake;
    }

}