
/**
 * Classe responsável por representar um jogador
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1, 22/04/2024
 */
public class Player implements Comparable<Player> {
    private int score;
    private final String nome;
    private int bestScore;

    /**
     * Construtor de Player
     * @param name nome do jogador
     * @param bestScore melhor pontuação do jogador
     */
    public Player(String name, int bestScore) {
        this.score = 0;
        this.nome = name;
        this.bestScore = bestScore;
    }

    /**
     * Construtor de Player, quando não há BestScore (é um novo jogador) o bestScore é 0
     * @param name nome do jogador
     */
    public Player(String name) {
        this(name,0);
    }


    /** Getter do score
     * @return int score
     */
    public int getScore() {return score;}

    /** Setter do nome
     * @param set int score
     */
    public void setScore(int set) {this.score = set;}



    /** Getter do nome
     * @return String nome
     */
    public String getNome() {return nome;}

    /**
     * Getter da melhor pontuação daquele jogador
     * @return int bestScore
     */
    public int getBestScore()
    {
        return this.bestScore;
    }

    /**
     * Atualiza a melhor pontuação do jogador atual (seria impossivel manipular o score de outros jogadores a partir do jogo)
     * @param newScore nova melhor pontuação
     * @pre newScore >= this.bestScore
     * @post bestScore == newScore
     */

    public void setBestScore(int newScore)
    {
        this.bestScore = newScore;
    }

    /**
     * Método para transformar um jogador em String
     * @return String com o nome e a melhor pontuação do jogador
     */

    @Override
    public String toString()
    {
        return this.nome + " : " + this.bestScore;
    }

    /**
     * Compara dois jogadores, retorna o inteiro resultante da comparação a partir dos seus bestScores
     * @param o the object to be compared.
     * @return o valor correspondente a comparação entre os dois jogadores do comparator
     */

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.bestScore, this.bestScore);
    }
}
