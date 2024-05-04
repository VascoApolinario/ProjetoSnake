import FigurasGeo.*;


public class Cell extends Quadrado {

    private boolean empty;

    private Content content;     //se o conteudo dentro da celula for Objeto printa O


    public Cell(Ponto p1, Ponto p2) {
        super(p1,p2);
        empty = true;
        content = Content.EMPTY;
    }

    public void updateCell(boolean empty,Content cont) {
        this.setEmpty(empty);
        this.setContent(cont);
    }

    public boolean isEmpty() {
        return empty;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isIntersected(Poligono poligono)
    {
        Ponto[] pontosQuadradoCelula = super.getPontos();
        for(Ponto pontoCelula : pontosQuadradoCelula)
        {
            if(pontoCelula.isInside(poligono))
                return true;
        }
        return false;
    }

}
