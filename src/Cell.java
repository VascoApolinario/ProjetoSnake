import FigurasGeo.*;
/**
 * Classe que representa uma célula de uma grelha
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version  1.0 09/05/2024
 */

public class Cell extends Quadrado {

    private boolean empty;

    private Content content;     //se o conteudo dentro da celula for Objeto printa O


    /**
     * Construtor da classe Cell
     * @param p1 ponto inferior esquerdo
     * @param p2 ponto superior direito
     * @pre receber pontos válidos
     * @post cria uma nova instância de Cell
     */
    public Cell(Ponto p1, Ponto p2) {
        super(p1,p2);
        empty = true;
        content = Content.EMPTY;
    }

    /**
     * Metodo que atualiza os conteúdos da Cell
     * @param empty boolean se a cell ta fazia ou não
     * @param cont Enum sobre o conteúdo na cell
     */
    public void updateCell(boolean empty,Content cont) {
        this.setEmpty(empty);
        this.setContent(cont);
    }

    /**
     * Metodo que verifica se uma Cell ta vazia.
     * @return true se tiver vazia, false se não
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Getter da classe Cell que retorna o content
     * @return content
     */
    public Content getContent() {
        return content;
    }

    /**
     * Setter da classe Cell que altera o content
     * @param content conteúdo que se pretende inserir na Cell
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * Setter da classe Cell que altera o empty
     * @param empty boolean que representa o estado atual da Cell, em relação se está vazia.
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * Metodo que devolve a representação em String da Cell.
     * @return String.
     */
    @Override
    public String toString() {
        return super.getCentroide().toString2();
    }

    /**
     * Metodo que verifica se uma célula contém conteúdo perigoso para a cobra
     * @return true se tiver Obstáculo,Cauda,Borda ou e uma celula dentro da area de rotação de um obstaculo, false se não.
     */
    public boolean isDangerous(){
        return this.getContent() == Content.OBSTACLE || this.getContent() == Content.TAIL || this.getContent() == Content.BORDER || this.getContent() == Content.DINAMICO;
    }

}
