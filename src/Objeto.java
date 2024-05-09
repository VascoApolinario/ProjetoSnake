import FigurasGeo.Poligono;
import FigurasGeo.Ponto;

/**
 * Classe abstrata para tratar dos diferentes objetos do jogo
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0 22/04/2024
 */

public abstract class Objeto {

    public final void criar(String formato, Ponto spawn) {
        //spawn(spawn);
    }


    abstract void update();
    //abstract void spawn(Ponto p);
    abstract void move(Grid g);
    abstract void rotate(int degrees);
    abstract Poligono format(String formato);
    abstract String tipo();
}