package FigurasGeo;

import java.util.Arrays;
/**
 * Classe responsável para representar um retangulo.
 * @author [Diogo Almeida 79810]
 * @version 1.0 , 26/03/2024
 * @inv O array de pontos que define o retangulo tem que conter 4 pontos.
 * @inv Não pode existir 3 pontos consecutivos colineares.
 * @inv Nenhum par de arestas pode cruzar-se entre si.
 * @inv todos os angulos internos têm de ter 90º
 */
public class Retangulo extends Poligono {
    /**
     * Construtor da classe FigurasGeo.Retangulo
     * @param pontos do retangulo
     */
    public Retangulo(Ponto[] pontos) {
        super(pontos);
        verificarInvariantes(pontos);
    }

    /**
     * Construtor da classe FigurasGeo.Retangulo
     * @param s string com coordenadas
     */
    public Retangulo(String s)
    {
        super("4 " + s);
        verificarInvariantes(super.getPontos());
    }

    /**
     * Método que verifica se as invariantes da classe são respeitadas
     * @param pontos
     */
    private void verificarInvariantes(Ponto[] pontos)
    {
        if(pontos.length != 4)
        {
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
        double angle1;
        double angle2;
        double angleBetweenLines;
        angle1 = Math.atan2(pontos[pontos.length-1].getY() - pontos[0].getY(),pontos[pontos.length-1].getX() - pontos[0].getX());
        for(int i = 1; i < pontos.length; i++)
        {
            angle2 = Math.atan2(pontos[i-1].getY() - pontos[i].getY(),pontos[i-1].getX() - pontos[i].getX());
            angleBetweenLines = Math.toDegrees(Math.abs(angle1 - angle2))%180;
            if(Math.abs(angleBetweenLines - 90) > 1e-9 )
            {
                System.out.println("Retangulo:vi");
                System.exit(0);
            }
            angle1 = angle2;
        }
        angle2 = Math.atan2(pontos[pontos.length-1].getY() - pontos[0].getY(),pontos[pontos.length-1].getX() - pontos[0].getX());
        angleBetweenLines = Math.toDegrees(Math.abs(angle1-angle2))%180;
        if(Math.abs(angleBetweenLines - 90) > 1e-9 )
        {
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
    }

    /**
     * Metodo que cria a representação em String de um retangulo
     * @return String que representa um retangulo
     */
    @Override
    public String toString(){
        return "FigurasGeo.Retangulo: " + Arrays.toString(super.getPontos());
    }

    /**
     * Metodo que chama o construtor da sua classe e cria uma nova instância
     * @param pontos do retangulo
     * @return FigurasGeo.Retangulo
     */
    @Override
    protected Retangulo createInstance(Ponto[] pontos) {
        return new Retangulo(pontos);
    }





}