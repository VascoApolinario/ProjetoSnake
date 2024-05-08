import FigurasGeo.Circulo;
import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.util.Random;

public class CircleFood extends Objeto implements Food{
    private Circulo circulo;

    public CircleFood(Cell spawn, double raio) {
        this.circulo = new Circulo(new Ponto(spawn.getCentroide().getX(),spawn.getCentroide().getY()),raio);
        spawn.updateCell(false,Content.FOOD);

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
        Random rand = new Random();
        this.circulo = new Circulo(c.getCentroide(),rand.nextInt((int)c.getSide()/4,(int)c.getSide()/2));  //podia-se fazer o raio aleatorio entre um valor minimo visivel e o tamanho da cabeça da snake

    }

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

    public Circulo getCirculo() {
        return circulo;
    }
}
