import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Leaderboard {
    private ArrayList<Player> leaderboard;
    private String filename;


    public Leaderboard(String filename) {
        this.filename = "leaderboard.txt";
        this.leaderboard = new ArrayList<>();
        File file = new File(this.filename);
        if(file.exists()) {
            //System.out.println("File exists");
            try {
                loadFromFile();
            }
            catch(Exception e) {
                System.out.println("File does not exist");
            }
        }
        else{
        //System.out.println("File does not exist");
            try {
                file.createNewFile();
            }
            catch(Exception e) {
                System.out.println("File does not exist");
            }
        }

        //System.out.println(leaderboard.size());
    }


    public void loadLeaderboard() throws FileNotFoundException {
        File myFile = new File(this.filename);
        Scanner myReader = new Scanner(myFile);
        String numberOfPlayers = myReader.nextLine();
        int numberOfPlayersNum = Integer.parseInt(numberOfPlayers);
        for(int i = 0; i < numberOfPlayersNum; i++) {

        }
    }

    public void update(Player player) {
        add(player);
        Collections.sort(leaderboard);
        saveToFile();
    }

    public void update() {
        saveToFile();
    }

    public void sort(){
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
                String[] data = line.split(" ");
                if (data.length >= 2) {
                    //System.out.println("SAVING PLAYER NAME AND BEST SCORE");
                    //System.out.println(data[1]);
                    Player player = new Player(data[0], Integer.parseInt(data[1]));
                    leaderboard.add(player);
                }
                if(data.length == 1) {
                    //System.out.println("SAVING ONLY PLAYER NAME");
                    Player player = new Player(data[0]);
                    leaderboard.add(player);
                }
            }
    }

    public void saveToFile() {
        try (FileWriter fw = new FileWriter(filename)) {
            sort();
            for (Player player : leaderboard) {
                fw.write(player.getNome() + " " + player.getBestScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void add(Player player) {
        boolean exists = false;
        int i;
        for(i = 0; i < leaderboard.size(); i++) {
            if(player.getNome().equals(leaderboard.get(i).getNome())) {
                //System.out.println("Player already exists 1 ");
                exists = true;
                break;
            }
        }
        if(exists)
        {
            //System.out.println("Player already exists 2 ");
            if(player.getBestScore() > leaderboard.get(i).getBestScore()) {
                leaderboard.get(i).setBestScore(player.getBestScore());
            }
        }
        else{
            leaderboard.add(new Player(player.getNome(), player.getBestScore()));
        }
        saveToFile();
    }

    public void printLeaderboard() {
        this.sort();
        for(Player player : leaderboard)
        {
            System.out.println(player.toString());
        }
    }

    public String printLeaderboard(int n)
    {
        this.sort();
        String result = "";
        for(int i = 0; i < n; i++)
        {
            if(i<leaderboard.size()) {
                result = result + "RANK #"+ (i+1) + " BEST SCORE : "+ leaderboard.get(i).getBestScore() + " NAME : " +leaderboard.get(i).getNome() + "\n";
            }
        }
        return result;
    }

    /*
    @Override
    public String toString()
    {
        String result = "";
        for(Player player : leaderboard) {
            result = player.getNome() + " " + player.getBestScore() + "\n";
        }
        return result;
    }
    */

    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }
}
