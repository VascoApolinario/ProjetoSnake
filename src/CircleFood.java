import FigurasGeo.Circulo;
import FigurasGeo.Ponto;

public class CircleFood extends Objeto implements Food{
    private Circulo circulo;

    public CircleFood(double x, double y, double raio) {
        this.circulo = new Circulo(new Ponto(x,y),raio);
    }

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

    public Circulo getCirculo() {
        return circulo;
    }
}
