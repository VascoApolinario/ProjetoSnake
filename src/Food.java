
import FigurasGeo.*;

public class Food  extends Objeto  {

    private boolean Circulo;
    private Circulo c;
    private Quadrado q;


    public Food(String tipo, Ponto coordenadas)
    {
        this.format(tipo);
        spawn(coordenadas);
        if (Circulo == false) {q = new Quadrado(tipo);}
        else {
            c = new Circulo(tipo);
        }
    }

    @Override
    void format(String formato) {
        if (formato.equals("Quadrado")) { Circulo = false; }
        else {Circulo = true; }
    }

    @Override
    void update() {

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
    String tipo() {
        return "";
    }
}
