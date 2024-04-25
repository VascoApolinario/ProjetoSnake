import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    private ArrayList<Player> leaderboard;
    private String filename;


    public Leaderboard(String filename) throws IOException {
        this.filename = filename;
        this.leaderboard = new ArrayList<>();
        loadFromFile();
    }

    public void update() {
        Collections.sort(leaderboard);
    }

    public Player findPlayer(String name) {
        for (Player player : leaderboard) {
            if (player.getNome().equals(name)) {
                return player;
            }
        }
        return null;
    }

    private void loadFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    Player player = new Player(data[0], Integer.parseInt(data[1]));
                    leaderboard.add(player);
                }
            }
    }

    private void saveToFile() {
        try (FileWriter fw = new FileWriter(filename)) {
            update();
            for (Player player : leaderboard) {
                fw.write(player.getNome() + "," + player.getBestScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void add(Player player) {
        leaderboard.add(player);
        saveToFile();
    }

    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }
}
