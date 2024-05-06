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
    private JLabel nameRestricion;

    public InitialPanel(){
        startGame = false;
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 700);
        janela.setLayout(new FlowLayout());
        janela.setLocationRelativeTo(null);
        janela.setTitle("OBJECT ORIENTED PROGRAMMED SNAKE");
        playerName = "";

        insertUserName = new JLabel("Insert Player Name: ");
        nameRestricion = new JLabel("NOTE: Your name should not contain space characters!!");


        playernameField = new JTextField();
        playernameField.setPreferredSize(new Dimension(250, 40));
        playernameField.setFont(new Font("Courier New", Font.PLAIN, 20));

        submeter = new JButton("START GAME");
        submeter.addActionListener(this);
        submeter.setFont(new Font("Courier New", Font.PLAIN, 20));

        janela.add(insertUserName);
        janela.add(playernameField);
        janela.add(submeter);
        janela.add(nameRestricion);
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
                nameRestricion.setText("ERROR: Make sure your name doesn't contain space characters!");
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