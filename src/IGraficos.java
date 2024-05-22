import FigurasGeo.Ponto;

import java.awt.*;

/**
 * Interface que é responsável pela GUI do jogo. Incluí métodos para desenhar os vários elementos que compõem o jogo.
 * @author [André Guerreiro 79809, Diogo Almeida 79810, Vasco Apolinário 79944]
 * @version 1.0
 */
public interface IGraficos {
    /**
     * Desenha o painel do jogo.
     * @param width a largura do painel
     * @param height a altura do painel
     * @param inputHandler o manipulador de entrada do jogo
     */
    void drawPanel(int width, int height, InputHandler inputHandler);
    /**
     * Desenha a cobra no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawSnake(Graphics g);
    /**
     * Desenha os obstáculos no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawObstacles(Graphics g);
    /**
     * Desenha a comida no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawFood(Graphics g);
    /**
     * Desenha a comida numa célula específica do jogo.
     * @param g o objeto Graphics usado para desenhar
     * @param c a célula onde a comida está localizada
     * @param p o ponto onde a comida está localizada
     */
    void drawFood(Graphics g,Cell c,Ponto p);
    /**
     * Desenha a grade do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawGrid(Graphics g);
    /**
     * Desenha a pontuação do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawScore(Graphics g);
    /**
     * Desenha a direção da cobra no jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawSnakeDirection(Graphics g);
    /**
     * Desenha a tela de fim de jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawGameOver(Graphics g);
    /**
     * Desenha a borda do jogo.
     * @param g o objeto Graphics usado para desenhar
     */
    void drawBorder(Graphics g);

    /**
     * Repinta o painel.
     */
    void repaint();
    /**
     * Atualiza o fundo do jogo.
     * @param bg o novo fundo do jogo
     */
    void setBG(Background bg);

}





