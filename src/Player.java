
/**
 * Classe responsável para representar um jogador
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1, 22/04/2024
 */
public class Player implements Comparable<Player> {
    private int score;
    private final String nome;
    private int bestScore;

    public Player(String name, int bestScore) {
        this.score = 0;
        this.nome = name;
        this.bestScore = bestScore;
    }

    public Player(String name) {
        this(name,0);
    }


    /** Getter do score
     * @return int score
     */
    public int getScore() {return score;}
    /** Setter do nome
     */
    public void setScore(int set) {this.score = set;}



    /** Getter do nome
     * @return String nome
     */
    public String getNome() {return nome;}

    /**
     * Getter da melhor pontuação daquele jogador
     * @return
     */
    public int getBestScore()
    {
        return this.bestScore;
    }

    /**
     * Atualiza a melhor pontuação do jogador atual (seria impossivel manipular o score de outros jogadores a partir do jogo)
     * @param newScore
     * @pre newScore >= this.bestScore
     * @post bestScore == newScore
     */

    public void setBestScore(int newScore)
    {
        this.bestScore = newScore;
    }




    @Override
    public String toString()
    {
        return this.nome + " : " + this.score;
    }


    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.bestScore, this.bestScore);
    }
}
