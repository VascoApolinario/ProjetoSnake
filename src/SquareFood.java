import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
/**
 * Classe que representa Comida em formato de Quadrado
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.0 09/05/2024
 * @inv o lado do quadrado da comida não pode ser superior ao lado da cabeça da snake.
 */

public class SquareFood extends Objeto implements Food{

    private Quadrado quadrado;

    /**
     * Construtor da class SquareFood
     * @param size tamanho do quadrado
     * @param spawnPoint ponto onde a comida vai aparecer
     * @param SnakeHeadSize tamanho da cabeça da snake
     */
    public SquareFood(int size,Cell spawnPoint, int SnakeHeadSize)
    {
        if(size > SnakeHeadSize){
            throw new IllegalArgumentException("Square Food: vi");
        }
        this.quadrado = new Quadrado(size);
        spawn(spawnPoint);
    }

    /**
     * Método chamado quando a SquareFood é consumida
     * @param grid grid do jogo
     */
    @Override
    public void consumir(Grid grid) {
        int cellSize = (int)grid.getCells()[0][0].getSide();
        Cell spawnPoint = grid.pickSpawnPoint();
        this.spawn(spawnPoint);
    }

    /**
     * Metodo que altera as coordenadas do SquareFood
     * @param c celula
     */
    @Override
    public void spawn(Cell c) {
        c.updateCell(false,Content.FOOD);
        this.quadrado = (Quadrado) this.quadrado.moveCentroid((int)c.getCentroide().getX(),(int)c.getCentroide().getY());
    }

    /**
     * Método que retorna o centroide da SquareFood
     * @return centroide do quadrado
     */
    @Override
    public Ponto getLocation() {
        return this.quadrado.getCentroide();
    }

    /**
     * Método Não implementado da classe abstrata objeto
     */
    @Override
    void update() {

    }

    /**
     * Método Não implementado da classe abstrata objeto
     * @param g a grid onde mover o objeto
     */
    @Override
    void move(Grid g) {

    }

    /**
     * Método Não implementado da classe abstrata objeto
     * @param degrees a quantidade de graus a rodar o objeto
     */
    @Override
    void rotate(int degrees) {

    }

    /**
     * Método Não implementado da classe abstrata objeto
     * @param formato String que corresponde ao formato do objeto
     * @return null
     */
    @Override
    Poligono format(String formato) {

        return null;
    }

    /**
     * Método que retorna o tipo da SquareFood em formato string
     * @return tipo da SquareFood
     */
    @Override
    String tipo() {
        return "SquareFood";
    }

    /**
     * Getter da classe SquareFood que retorna o  seu quadrado
     * @return quadrado
     */
    public Quadrado getQuadrado() {
        return quadrado;
    }
}
