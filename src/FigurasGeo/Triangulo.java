package FigurasGeo;

import java.util.Arrays;
/**
 * Classe responsável para representar um triangulo.
 * @author [Diogo Almeida 79810]
 * @version 1.0 , 26/03/2024
 * @inv O array de pontos que define o triangulo tem que conter 3 pontos.
 * @inv Não pode existir 3 pontos consecutivos colineares.
 * @inv Nenhum par de arestas pode cruzar-se entre si.
 */
public class Triangulo extends Poligono {

    /**
     * Construtor da classe triangulo
     * @param pontos do triangulo
     */
    public Triangulo(Ponto[] pontos) {
        super(pontos);
        verificarInvariantes(pontos);
    }

    /**
     * Construtor da classe FigurasGeo.Triangulo
     * @param s string com as coordenadas
     *
     */
    public Triangulo(String s) {
        super("3 " + s);
        verificarInvariantes(super.getPontos());
    }

    /**
     * Método que verifica se as invariantes da classe são respeitadas
     * @param pontos
     */
    private void verificarInvariantes(Ponto[] pontos)
    {
        if(pontos.length != 3)
        {
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
    }

    /**
     * Metodo que cria a representação em String de um triangulo
     * @return String que representa um triangulo
     */
    @Override
    public String toString(){
        return "Triangulo: " + Arrays.toString(super.getPontos());
    }
    /**
     * Metodo que chama o construtor da sua classe e cria uma nova instância
     * @param pontos do triangulo
     * @return FigurasGeo.Triangulo
     */
    @Override
    protected Triangulo createInstance(Ponto[] pontos) {
        return new Triangulo(pontos);
    }
}
