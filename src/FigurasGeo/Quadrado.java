package FigurasGeo;

import java.util.Arrays;
/**
 * Classe responsável para representar um quadrado.
 * @author [Diogo Almeida 79810]
 * @version 1.0 , 26/03/2024
 * @inv O array de pontos que define o quadradp tem que conter 4 pontos.
 * @inv Não pode existir 3 pontos consecutivos colineares.
 * @inv Nenhum par de arestas pode cruzar-se entre si.
 * @inv todos os angulos internos têm de ter 90º
 * @inv os lados têm todos de ter o mesmo comprimento
 */
public class Quadrado extends Retangulo {

    private double side;
    private Ponto topRight;
    private Ponto downLeft;

    /**
     * Construtor da classe FigurasGeo.Quadrado
     * @param pontos do quadrado
     */
    public Quadrado(Ponto[] pontos) {
        super(pontos);
        verificarInvariantes(pontos);
        this.side = pontos[0].dist(pontos[1]);
        setDiagonalPoints(pontos);
    }

    /**
     * Construtor da classe FigurasGeo.Quadrado
     * @param p1 ponto de coordenadas minimox minimoy
     * @param p2 ponto de coordenadas maximox maximoy
     */
    public Quadrado(Ponto p1, Ponto p2) {
        this(createQuadrado(p1,p2));
        verificarInvariantes(super.getPontos());
        this.topRight = new Ponto(p2.getX(), p2.getY());
        this.downLeft = new Ponto(p1.getX(), p1.getY());
    }

    public Quadrado(int size) {
        this(createQuadrado(new Ponto(0,0), new Ponto(size,size)));
    }

    private static Ponto[] createQuadrado(Ponto p1, Ponto p2) {
        Ponto[] vertices = new Ponto[4];
        vertices[0] = new Ponto(p1.getX(), p1.getY());;
        vertices[1] = new Ponto(p1.getX(), p2.getY());
        vertices[2] =  new Ponto(p2.getX(), p2.getY());
        vertices[3] = new Ponto(p2.getX(), p1.getY());
        return vertices;
    }

    /**
     * Construtor da classe FigurasGeo.Quadrado
     * @param s string com coordenadas
     */
    public Quadrado(String s)
    {
        super(s);
        verificarInvariantes(super.getPontos());
        this.side = super.getPontos()[0].dist(super.getPontos()[1]);
        setDiagonalPoints(super.getPontos());
    }

    /**
     * Método que verifica se as invariantes da classe são respeitadas
     * @param pontos
     */
    private void verificarInvariantes(Ponto[] pontos)
    {
        double sideLenght = pontos[pontos.length-1].dist(pontos[0]);
        for(int i = 1; i < pontos.length;i++)
        {
            if(Math.abs(sideLenght -pontos[i-1].dist(pontos[i])) > 1e-9)
            {
                System.out.println("FigurasGeo.Quadrado:vi");
                System.exit(0);
            }
        }
    }

    private void setDiagonalPoints(Ponto[] pontos)
    {
        double minX = pontos[0].getX();
        double minY = pontos[0].getY();
        double maxX = pontos[0].getX();
        double maxY = pontos[0].getY();
        for (int i = 1; i < pontos.length;i++)
        {
            maxX = Math.max(maxX, pontos[i].getX());
            minX = Math.min(minX, pontos[i].getX());
            maxY = Math.max(maxY, pontos[i].getY());
            minY = Math.min(minY, pontos[i].getY());
        }
        this.downLeft = new Ponto(minX, minY);
        this.topRight = new Ponto(maxX, maxY);
    }

    /**
     * Metodo que cria a representação em String de um quadrado
     * @return String que representa um quadrado
     */
    @Override
    public String toString(){
        return "FigurasGeo.Quadrado: " + Arrays.toString(super.getPontos());
    }
    /**
     * Metodo que chama o construtor da sua classe e cria uma nova instância
     * @param pontos do quadrado
     * @return FigurasGeo.Quadrado
     */
    @Override
    protected Quadrado createInstance(Ponto[] pontos) {
        return new Quadrado(pontos);
    }

    public double getSide() {
        return side;
    }

    public Ponto getDownLeft() {
        return downLeft;
    }

    public Ponto getTopRight() {
        return topRight;
    }

    public boolean isInside(Quadrado q)
    {
        if(this.polygonsIntercept(q))
            return false;

        return this.downLeft.getX() <= q.downLeft.getX() && this.topRight.getX() >= q.topRight.getX() && this.downLeft.getY() <= q.downLeft.getY() && this.topRight.getY() >= q.topRight.getY();
    }


    public boolean isInside(Circulo c)
    {
        double leftLimit = c.getCenter().getX() - c.getRadius();
        double rightLimit = c.getCenter().getX() + c.getRadius();
        double upLimit = c.getCenter().getY() + c.getRadius();
        double downLimit = c.getCenter().getY() - c.getRadius();

        return this.downLeft.getX() <= leftLimit && this.topRight.getX() >= rightLimit && this.downLeft.getY() <= downLimit && this.topRight.getY() >= upLimit;
    }
}