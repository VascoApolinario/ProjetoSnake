import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Background bg = new Background(800,800,"Grafica");
        while (true)
        {
            bg.updateAll();
        }
    }

}