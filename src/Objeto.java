import FigurasGeo.Ponto;

public abstract class Objeto {

    public final void criar(String formato, Ponto spawn) {
        format(formato);
        spawn(spawn);
    }


    abstract void update();
    abstract void format(String formato);
    abstract void spawn(Ponto p);
    abstract void move();
    abstract void rotate(int degrees);
}