import FigurasGeo.Circulo;
import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import java.util.Random;

/**
 * Classe que representa Comida em formato Circular
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @Version 1.0 09/05/2024
 * @inv o diâmetro não pode ser superior ao lado da cabeça da snake.
 */

public class CircleFood extends Objeto implements Food{
    private Circulo circulo;

    /**
     * Construtor da classe CircleFood
     * @param spawn celula cujo o centroide será o centro da comida
     * @param raio raio da comida
     * @pre receber uma Cell e raio válidos
     * @post cria nova instância da classe CircleFood
     */
    public CircleFood(Cell spawn, double raio, int SnakeHeadSize) {
        if(raio*2 > SnakeHeadSize){
            throw new IllegalArgumentException("Circle Food: vi");
        }
        this.circulo = new Circulo(new Ponto(spawn.getCentroide().getX(),spawn.getCentroide().getY()),raio);
        spawn.updateCell(false,Content.FOOD);

    }

    /**
     * Método chamado quando a CircleFood é consumida.
     * @param grid grid do jogo
     */
    @Override
    public void consumir(Grid grid) {
        Cell spawnPoint = grid.pickSpawnPoint();
        this.spawn(spawnPoint);
    }

    @Override
    public void update() {
        
    }

    /**
     * Metodo que altera as coordenadas do CircleFood
     * @param c celula
     */
    @Override
    public void spawn(Cell c) {
        c.updateCell(false,Content.FOOD);
        Random rand = new Random();
        this.circulo = new Circulo(c.getCentroide(),rand.nextInt((int)c.getSide()/4,(int)c.getSide()/2));  //podia-se fazer o raio aleatorio entre um valor minimo visivel e o tamanho da cabeça da snake

    }

    /**
     * Método que retorna o centro da CircleFood
     * @return centro do circulo
     */
    @Override
    public Ponto getLocation() {
        return this.circulo.getCenter();
    }

    @Override
    void move(Grid g) {

    }

    @Override
    void rotate(int degrees) {

    }

    @Override
    Poligono format(String formato) {

        return null;
    }

    @Override
    String tipo() {
        return "";
    }

    /**
     * Getter da classe CircleFood que retorna o Circulo da CircleFood
     * @return Circulo
     */
    public Circulo getCirculo() {
        return circulo;
    }
}
