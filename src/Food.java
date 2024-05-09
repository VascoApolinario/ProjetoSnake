
import FigurasGeo.*;

import java.util.Random;

public interface Food {

    /**
     * Metodo que e chamado quando a Food e sonsumida.
     * @param grid grid do jogo
     */
    void consumir(Grid grid);
    void update();
    void spawn(Cell c);
    Ponto getLocation();
}

