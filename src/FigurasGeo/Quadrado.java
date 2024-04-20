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
    /**
     * Construtor da classe FigurasGeo.Quadrado
     * @param pontos do quadrado
     */
    public Quadrado(Ponto[] pontos) {
        super(pontos);
        verificarInvariantes(pontos);
    }

    /**
     * Construtor da classe FigurasGeo.Quadrado
     * @param p1 ponto de coordenadas minimox minimoy
     * @param p2 ponto de coordenadas maximox maximoy
     */
    public Quadrado(Ponto p1, Ponto p2) {
        this(createQuadrado(p1,p2));
        verificarInvariantes(super.getPontos());
    }

    public Quadrado(int size) {
        this(createQuadrado(new Ponto(0,0), new Ponto(size,size)));
    }

    private static Ponto[] createQuadrado(Ponto p1, Ponto p2) {
        Ponto[] vertices = new Ponto[4];
        vertices[0] = new Ponto(p1.getX(), p1.getY());
        vertices[1] = new Ponto(p1.getX(), p2.getY());
        vertices[2] = new Ponto(p2.getX(), p2.getY());
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

    /**
     * Metodo que devolve o tamanho do lado do quadrado
     * @return sideLenght
     */
    public double sideLength()
    {
        return getArestas()[0].lenght();
    }

    /**
     * Metodo que cria a representação em String de um quadrado
     * @return String que representa um quadrado
     */
    @Override
    public String toString(){
        return "Quadrado: " + Arrays.toString(super.getPontos());
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

    public boolean isInside(Quadrado q)
    {

        return false;
    }

    /*
    public boolean isInside(Circulo c)
    {
        return false;
    }*/
}