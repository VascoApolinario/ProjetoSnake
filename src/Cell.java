import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class Cell extends Quadrado {

    private boolean empty;
    private Objeto content;     //se o conteudo dentro da celula for Objeto printa O
    public Cell(Ponto p1, Ponto p2) {
        super(p1,p2);
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Objeto getContent() {
        return content;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
