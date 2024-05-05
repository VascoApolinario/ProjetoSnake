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
    public InitialPanel(){
        startGame = false;
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new FlowLayout());
        playerName = "";


        playernameField = new JTextField();
        playernameField.setPreferredSize(new Dimension(250, 40));
        playernameField.setFont(new Font("Courier New", Font.PLAIN, 20));

        submeter = new JButton("Submeter");
        submeter.addActionListener(this);
        submeter.setFont(new Font("Courier New", Font.PLAIN, 20));


        janela.add(submeter);
        janela.add(playernameField);
        janela.pack();
        janela.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submeter)
        {
            playerName = playernameField.getText();
            startGame = true;
            janela.dispose();
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getStartGameStatus() {
        return startGame;
    }

}