public class Score {
    private int score;


    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void add(int add) {
        this.score += add;
    }
}
