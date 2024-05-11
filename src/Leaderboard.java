import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe da Leaderboard para guardar informações sobre os melhores jogadores
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0 22/04/2024
 */

public class Leaderboard implements Iterable<Player>{
    private ArrayList<Player> leaderboard;
    private String filename;

    /**
     * Constructor method for LeaderboardClass
     * @param filename
     */
    public Leaderboard(String filename) {
        this.filename = filename;
        this.leaderboard = new ArrayList<>();
        File file = new File(this.filename);
        if(file.exists()) {
            try {
                loadFromFile();
            }
            catch(Exception e) {
                System.out.println("File does not exist");
            }
        }
        else{
            try {
                file.createNewFile();
            }
            catch(Exception e) {
                System.out.println("Cannot be created");
            }
        }
    }

    /**
     * Retorna iterator para leaderboard, itera sobre os players.
     * @return Iterator<Player>
     */
    public Iterator<Player> iterator() {
        return new LeaderboardIterator(this);
    }

    public class LeaderboardIterator implements Iterator<Player> {
        private int currentIndex = 0;
        private final Leaderboard leaderboard;

        /**
         * Construtor do iterador para classe Leaderboard
         * @param leaderboard
         */
        public LeaderboardIterator(Leaderboard leaderboard) {
            this.leaderboard = leaderboard;
        }

        /**
         * Verifica se existe um próximo elemento na coleação
         * @return
         */
        @Override
        public boolean hasNext() {
            return currentIndex < leaderboard.getLeaderboard().size();
        }

        /**
         * Retorna o próximo elemento da coleção
         * @return
         */
        @Override
        public Player next() {
            return leaderboard.getLeaderboard().get(currentIndex++);
        }
    }


    /**
     * Método para atualizar ficheiro leaderboard (recebe player, adiciona, faz sort e guarda no ficheiro)
     * @param player
     */
    public void update(Player player) {
        add(player);
        Collections.sort(leaderboard);
        saveToFile();
    }

    /**
     * Método para atualizar ficheiro leaderboard
     */
    public void update() {
        saveToFile();
    }

    /**
     * Método para organizar leaderboard por player pontuação descendente (maior para o menor)
     */
    public void sort(){
        Collections.sort(leaderboard);
    }

    /**
     * Dada uma string nome, compara com os nomes dos players da leaderboard e retorna o player com nome igual à string.
     * @pre para funcionamento correto, "name" deve ter um nome igual ao nome de um Player existente
     * @param name
     * @pos name.Equals(Player.getNome())
     */
    public Player findPlayer(String name) {
        for (Player player : leaderboard) {
            if (player.getNome().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * A partir do nome do ficheiro de texto desta instancia, carrega para função o texto e formata-o para preencher a Leaderboard com Player's
     * @pre filePath da "filename" existe e é um ficheiro válido com dados de uma Leaderboard.
     * @throws IOException
     */
    private void loadFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length >= 2) {
                    Player player = new Player(data[0], Integer.parseInt(data[1]));
                    leaderboard.add(player);
                }
                if(data.length == 1) {
                    Player player = new Player(data[0]);
                    leaderboard.add(player);
                }
            }
    }

    /**
     * A partir do nome do ficheiro de texto desta instancia, carrega para o ficheiro o estado atual da Leaderboard em texto, com a sintaxe apropriada para poder ser lida mais tarde
     * @pre filePath da "filename" existe.
     */
    public void saveToFile() {
        try (FileWriter fw = new FileWriter(filename)) {
            sort();
            for (Player player : this) {
                fw.write(player.getNome() + " " + player.getBestScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Adiciona player à Leaderboard, caso já exista permanece o com melhor pontuação
     * @param player
     */
    public void add(Player player) {
        boolean exists = false;
        int i;
        for(i = 0; i < leaderboard.size(); i++) {
            if(player.getNome().equals(leaderboard.get(i).getNome())) {
                exists = true;
                break;
            }
        }
        if(exists)
        {
            if(player.getBestScore() > leaderboard.get(i).getBestScore()) {
                leaderboard.get(i).setBestScore(player.getBestScore());
            }
        }
        else{
            leaderboard.add(new Player(player.getNome(), player.getBestScore()));
        }
        saveToFile();
    }

    /**
     * Devolve uma string com os melhores "n" Players da LeaderBoard
     * @param n
     * @return
     */

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

    /**
     * Getter do array de Players
     * @return
     */
    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }
}
