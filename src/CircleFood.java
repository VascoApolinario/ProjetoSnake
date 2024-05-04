import FigurasGeo.Circulo;
import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class CircleFood extends Objeto implements Food{
    private Circulo circulo;

    public CircleFood(double x, double y, double raio) {
        this.circulo = new Circulo(new Ponto(x,y),raio);

    }

    @Override
    public void consumir(Grid grid) {
        int cellSize = (int)grid.getCells()[0][0].getSide();
        grid.returnCellFromPoint(this.getCirculo().getCenter()).updateCell(true,Content.EMPTY);
        Cell spawnPoint = grid.pickSpawnPoint();
        this.spawn(spawnPoint);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void spawn(Cell c) {
        c.updateCell(false,Content.FOOD);
        this.circulo = new Circulo(c.getCentroide(),this.circulo.getRadius());  //podia-se fazer o raio aleatorio entre um valor minimo visivel e o tamanho da cabeça da snake

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

    public Circulo getCirculo() {
        return circulo;
    }
}
