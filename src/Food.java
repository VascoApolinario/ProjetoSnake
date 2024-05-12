
import FigurasGeo.*;

import java.util.Random;

/**
 * Interface que contém metodos relacionados com as ações realizadas pela comida no jogo Snake
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @Version 1.0 09/05/2024
 */
public interface Food {

    /**
     * Metodo que e chamado quando a Food é consumida.
     * @param grid grid do jogo
     */
    void consumir(Grid grid);

    /**
     * Método que dá spawn da comida
     * @param c celula onde a comida irá dar spawn
     */
    void spawn(Cell c);

    /**
     * Método que retorna o centro/centroide da comida
     * @return centro/centroide da comida
     */
    Ponto getLocation();
}

