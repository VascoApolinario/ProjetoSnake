public class Game {
    boolean game;
    Background background;
    public Game() {
        int Width = 800;
        int Height = 600;
        background = new Background(Width,Height,"Grafico");
    }

    public void runGame() {
        while(game) {
            background.updateAll();
            // Deteção de inputs
            // Draw dos objetos no background
        }
    }

}
