import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class InitialPanel extends JPanel implements ActionListener {

    private JFrame janela;
    private JButton submeter;
    private JTextField playernameField;
    private String playerName;
    public boolean startGame;
    private JTextField textField1;
    private JPanel panel1;
    private JLabel insertUserName;
    private JLabel nameRestriction;
    private JLabel leaderboardText;
    private JTextArea printLeaderboard;
    private JScrollPane printLeaderboardScroll;

    public InitialPanel(Leaderboard leaderboardCopy){
        startGame = false;
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 700);
        janela.setLayout(new FlowLayout());
        janela.setLocationRelativeTo(null);
        janela.setTitle("OBJECT ORIENTED PROGRAMMED SNAKE");
        playerName = "";

        insertUserName = new JLabel("INSERT PLAYER NAME: ");
        insertUserName.setFont(new Font("Courier New", Font.BOLD, 16));
        nameRestriction = new JLabel("NOTE: Your name should not contain space characters!!");

        nameRestriction.setFont(new Font("Courier New", Font.BOLD, 12));


        playernameField = new JTextField();
        playernameField.setPreferredSize(new Dimension(225, 35));
        playernameField.setFont(new Font("Courier New", Font.PLAIN, 18));

        submeter = new JButton("START GAME");
        submeter.addActionListener(this);
        submeter.setFont(new Font("Courier New", Font.PLAIN, 18));

        leaderboardText = new JLabel("LEADERBOARD");
        leaderboardText.setFont(new Font("Courier New", Font.BOLD, 40));


        janela.add(insertUserName);
        janela.add(playernameField);
        janela.add(submeter);
        janela.add(nameRestriction);
        janela.add(leaderboardText);

        printLeaderboard = new JTextArea();
        printLeaderboard.setPreferredSize(new Dimension(500,250));
        printLeaderboard.setFont(new Font("Courier New", Font.BOLD, 16));
        printLeaderboard.setEditable(false);
        printLeaderboard.setText(leaderboardCopy.printLeaderboard(100));

        printLeaderboardScroll = new JScrollPane(printLeaderboard);
        printLeaderboardScroll.setPreferredSize(new Dimension(550,400));
        printLeaderboardScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        janela.add(printLeaderboardScroll);
        //janela.add(printLeaderboardScroll);
        //janela.pack();
        janela.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submeter)
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
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getStartGameStatus() {
        return startGame;
    }

}