import javax.swing.*;

public class Main {

    public static void main(String[] args) {
            JFrame frame = new JFrame("Polygon Drawing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.getContentPane().add(new Background());
            frame.setVisible(true);
    }



}