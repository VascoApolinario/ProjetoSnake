import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class Cell extends Quadrado {

    private boolean empty;

    private Objeto content;     //se o conteudo dentro da celula for Objeto printa O

    public Cell(Ponto p1, Ponto p2) {
        super(p1,p2);
        empty = true;
    }

    public void updateCell(boolean empty,Objeto cont) {
        this.setEmpty(empty);
        this.setContent(cont);
    }

    public boolean isEmpty() {
        return empty;
    }

    public Objeto getContent() {
        return content;
    }

    public void setContent(Objeto content) {
        this.content = content;
        this.empty = false;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
