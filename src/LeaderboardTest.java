import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    @Test
    void update() throws IOException {
        Leaderboard l = new Leaderboard("f1.txt");
        l.add(new Player("Player1", 10));
        l.add(new Player("Player2", 1));
        l.add(new Player("Player3", 9));
        l.add(new Player("Player4", 150));
        l.update(new Player("Player5", 10));
        l.update(new Player("Player6", 11));
        l.update(new Player("Player7", 12));
        l.update(new Player("Player8", 13));
        l.update(new Player("Player9", 14));
/*
        assert(l.getLeaderboard().get(0).getNome().equals("Player4"));
        assert(l.getLeaderboard().get(1).getNome().equals("Player1"));
        assert(l.getLeaderboard().get(2).getNome().equals("Player3"));
        assert(l.getLeaderboard().get(3).getNome().equals("Player2"));*/
        System.out.println(l.getLeaderboard());

    }

    @Test
    void findPlayer() throws IOException {
        Leaderboard l = new Leaderboard("f1.txt");
        l.add(new Player("Player1", 10));
        assert(l.findPlayer("Player1").getNome().equals("Player1"));
    }

    @Test
    void add() throws IOException {
        Leaderboard l = new Leaderboard("f1.txt");
        l.add(new Player("Player1", 10));
        assert(l.findPlayer("Player1").getNome().equals("Player1"));
    }
    @Test
    public void testPrintLeaderboard() {
        Leaderboard leaderboard = new Leaderboard("leaderboardTest.txt");

        // Add some players
        Player player1 = new Player("Alice", 100);
        Player player2 = new Player("Bob", 150);
        Player player3 = new Player("Charlie", 120);
        leaderboard.update(player1);
        leaderboard.update(player2);
        leaderboard.update(player3);

        // Print top 2 players
        System.out.println("Top 2 players:");
        System.out.println(leaderboard.printLeaderboard(2));
    }
    @Test
    public void testFindPlayer() {
        Leaderboard leaderboard = new Leaderboard("leaderboardTest.txt");

        // encontra player que existe
        Player foundPlayer = leaderboard.findPlayer("Bob");
        if (foundPlayer != null) {
            System.out.println("Found player: " + foundPlayer.getNome() + " with best score: " + foundPlayer.getBestScore());
        } else {
            System.out.println("Player not found.");
        }

        // encontra player que não existe
        Player notFoundPlayer = leaderboard.findPlayer("David");
        if (notFoundPlayer != null) {
            System.out.println("Found player: " + notFoundPlayer.getNome() + " with best score: " + notFoundPlayer.getBestScore());
        } else {
            System.out.println("Player not found.");
        }
    }
}