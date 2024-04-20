import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class Cell extends Quadrado {

    private boolean empty;
    private Objeto content;
    public Cell(Ponto p1, Ponto p2) {
        super(p1,p2);
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
