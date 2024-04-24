import FigurasGeo.Ponto;

public abstract class Objeto {


    public final void criar(String formato, Ponto spawn) {
        spawn(spawn);
    }


    abstract void update();
    abstract void spawn(Ponto p);
    abstract void move();
    abstract void rotate(int degrees);
    abstract void format(String formato);
    abstract String tipo();
}