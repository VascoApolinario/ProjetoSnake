import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class SquareFood extends Objeto implements Food{

    private Quadrado quadrado;

    public SquareFood(int size,Cell spawnPoint)
    {
        this.quadrado = new Quadrado(size);
        spawn(spawnPoint);
    }

    @Override
    public void consumir(Grid grid) {
        int cellSize = (int)grid.getCells()[0][0].getSide();
        Cell spawnPoint = grid.pickSpawnPoint();
        this.spawn(spawnPoint);
    }

    @Override
    public void update() {
        
    }


    @Override
    public void spawn(Cell c) {
        c.updateCell(false,Content.FOOD);
        this.quadrado = (Quadrado) this.quadrado.moveCentroid((int)c.getCentroide().getX(),(int)c.getCentroide().getY());
    }

    @Override
    public Ponto getLocation() {
        return this.quadrado.getCentroide();
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

    public Quadrado getQuadrado() {
        return quadrado;
    }
}
