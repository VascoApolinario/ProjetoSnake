import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    @Test
    void update() throws IOException {
        Leaderboard l = new Leaderboard("f1.csv");
        l.add(new Player("Player1", 10));
        l.add(new Player("Player2", 1));
        l.add(new Player("Player3", 9));
        l.add(new Player("Player4", 150));

        assert(l.getLeaderboard().get(0).getNome().equals("Player4"));
        assert(l.getLeaderboard().get(1).getNome().equals("Player1"));
        assert(l.getLeaderboard().get(2).getNome().equals("Player3"));
        assert(l.getLeaderboard().get(3).getNome().equals("Player2"));

    }

    @Test
    void findPlayer() throws IOException {
        Leaderboard l = new Leaderboard("f1.csv");
        l.add(new Player("Player1", 10));
        assert(l.findPlayer("Player1").getNome().equals("Player1"));
    }

    @Test
    void add() throws IOException {
        Leaderboard l = new Leaderboard("f1.csv");
        l.add(new Player("Player1", 10));
        assert(l.findPlayer("Player1").getNome().equals("Player1"));
    }
}