/**
 * Enum que representa o conteudo que uma celula pode conter.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.0 09/05/2024
 */
public enum Content {

    /**
     * HEAD Enum que representa a cabeça da snake que uma celula pode conter.
     */
    HEAD,

    /**
     * TAIL Enum que representa a cauda da snake que uma celula pode conter.
     */
    TAIL,

    /**
     * OBSTACLE Enum que representa o obstaculo que uma celula pode conter.
     */
    OBSTACLE,

    /**
     * FOOD Enum que representa a food que uma celula pode conter.
     */
    FOOD,

    /**
     * EMPTY Enum que representa uma celula vazia.
     */
    EMPTY,

    /**
     * DINAMICO Enum que representa uma futura rotação de um objeto dinamico que uma celula pode conter.
     */
    DINAMICO,

    /**
     * BORDER Enum que representa a fronteira do mapa que uma celula pode conter.
     */
    BORDER,

    /**
     * EATING Enum que representa a cabeça da snake a comer que uma celula pode conter.
     */
    EATING
}
