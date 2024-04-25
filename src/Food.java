
import FigurasGeo.*;

public interface Food {

    default void consumir(){
        System.out.println("Consumido!");
    }

    void update();
}
