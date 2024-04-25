import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class SquareFood extends Objeto implements Food{

    private Quadrado quadrado;

    @Override
    public void update() {
        
    }

    @Override
    void spawn(Ponto p) {

    }

    @Override
    void move() {

    }

    @Override
    void rotate(int degrees) {

    }

    @Override
    void format(String formato) {

    }

    @Override
    String tipo() {
        return "";
    }

    public Quadrado getQuadrado() {
        return quadrado;
    }
}
