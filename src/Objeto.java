import FigurasGeo.Poligono;
import FigurasGeo.Ponto;

/**
 * Classe abstrata para tratar dos diferentes objetos do jogo
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0 22/04/2024
 */

public abstract class Objeto {

    /**
     * Metodo que atualiza o objeto de jogo correspondente (equivale a diferentes mudanças consoante o objeto
     */
    abstract void update();

    /**
     * Metodo que move o objeto na grid g
     * @param g a grid onde mover o objeto
     */
    abstract void move(Grid g);

    /**
     * Metodo que roda o objeto por uma quantidade determinada de graus
     * @param degrees a quantidade de graus a rodar o objeto
     */
    abstract void rotate(int degrees);

    /**
     * Metodo cria um poligono do objeto com o formato dado
     * @param formato String que corresponde ao formato do objeto
     */
    abstract Poligono format(String formato);

    /**
     * Metodo que retorna o tipo de objeto em formato string
     */
    abstract String tipo();
}