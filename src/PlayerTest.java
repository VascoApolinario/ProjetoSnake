import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void getName() {
        Player p1 = new Player("Jogador1", 150);
        assertEquals(p1.getNome(),"Jogador1");
    }

    @Test
    void setBestScore() {
        Player p1 = new Player("Jogador1", 150);
        p1.setBestScore(151);
        assertEquals(p1.getBestScore(),151);
    }
    @Test
    void getBestScore() {
        Player p1 = new Player("Jogador1", 150);
        assertEquals(p1.getBestScore(),150);
    }

    @Test
    void getScore() {
        Player p1 = new Player("Jogador1", 150);
        assertEquals(p1.getScore(),0);
    }

    @Test
    void setScore() {
        Player p1 = new Player("Jogador1", 150);
        p1.setScore(20);
        assertEquals(p1.getScore(),20);
    }
    @Test
    void testComparable()
    {
        Player p1 = new Player("Jogador1", 170);
        Player p2 = new Player("Jogador2", 150);
        assert(p1.compareTo(p2) < 0);
        assert(p2.compareTo(p1) > 0);
        assert(p1.compareTo(p1) == 0);
    }
    @Test
    void testToString(){
        Player p1 = new Player("Jogador1", 150);
        assertEquals(p1.toString(),"Jogador1 : 150");
    }
}