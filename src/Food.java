
import FigurasGeo.*;

import java.util.Random;

public interface Food {

    void consumir(Grid grid);
    void update();
    void spawn(Cell c);
}

