import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

public class SquareFood extends Objeto implements Food{

    private Quadrado quadrado;

    @Override
    public void consumir(Grid grid) {
        int cellSize = (int)grid.getCells()[0][0].getSide();
        Cell c = grid.getCells()[(int)(this.quadrado.getCentroide().getY()/cellSize)][(int)(this.quadrado.getCentroide().getX()/cellSize)];
        c.updateCell(true,null);
        Cell spawnPoint = pickSpawnPoint(grid);
        this.spawn(spawnPoint);
    }

    @Override
    public void update() {
        
    }


    @Override
    public void spawn(Cell c) {
        c.updateCell(false,this);
        this.quadrado = (Quadrado) this.quadrado.moveCentroid((int)c.getCentroide().getX(),(int)c.getCentroide().getY());
    }

    @Override
    void move() {

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
