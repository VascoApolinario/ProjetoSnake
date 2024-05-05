package FigurasGeo;

import java.util.Objects;

/**
 * Classe responsável em representar um ponto localizado no primeiro quadrante.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 3 , 25/03/2024
 * @inv As coordenadas x e y têm de ser positivas (pertencer ao 1º quadrante).
 */
public class Ponto {

    private double x,y;

    /**
     * Construtor da classe FigurasGeo.Ponto
     * @param  x coordenada x do ponto
     * @param  y coordenada y do ponto
     */
    public Ponto(double x, double y)
    {
        if(x >= 0 && y >= 0)
        {
            this.x = x;
            this.y = y;
        }
        else {
            invalidPoint();
        }
    }

    public void invalidPoint()
    {
        System.out.println("FigurasGeo.Ponto:vi");
        System.exit(0);
    }


    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public void setX(int x) {
        if(x < 0)
        {
            invalidPoint();
        }
        else {
            this.x = x;
        }
    }

    public void setY(int y) {
        if(y < 0)
        {
            invalidPoint();
        }
        else {
            this.y =y;
        }
    }

    /**
     * metodo usado para calcular a distancia entre dois pontos
     * @param  that
     * @return double com a distancia entre o ponto this e o ponto that
     */
    public double dist(Ponto that)
    {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Método que cria uma representação em String de um ponto
     * @return string que representa um ponto
     */
    @Override
    public String toString()
    {
        return String.format("(%d,%d)", (int)x, (int)y);
    }

    public String toString2()
    {
        return String.format("%d %d", (int)x, (int)y);
    }



    /**
     * Método que verifica se dois pontos são iguais
     * @param o ponto a qual iremos comparar
     * @return true se forem iguais, false se não
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;
        return Double.compare(x, ponto.x) == 0 && Double.compare(y, ponto.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Ponto translate(int dx, int dy)
    {
        return new Ponto(this.x + dx, this.y + dy);
    }

    public Ponto rotate(int angleDegrees, double pivotX, double pivotY) {
        // Translate the point to the origin
        double newX = this.getX() - pivotX;
        double newY = this.getY() - pivotY;

        // Rotate the translated point
        double angleRadians = Math.toRadians(angleDegrees);
        double rotatedX = newX * Math.cos(angleRadians) - newY * Math.sin(angleRadians);
        double rotatedY = newX * Math.sin(angleRadians) + newY * Math.cos(angleRadians);

        // Translate the rotated point back to its original position
        double finalX = rotatedX + pivotX;
        double finalY = rotatedY + pivotY;

        return new Ponto((int) Math.round(finalX), (int) Math.round(finalY));
    }

    /**
     * Método responsável por verificar se um ponto está dentro ou fora de um poligono
     * @param poligono
     * @return
     */

    public boolean isInside(Poligono poligono) {
        PreProcessamento rect = new PreProcessamento(poligono);
        Ponto[] pontosPoligono = poligono.getPontos();
        Segmento[] arestasPoligono = poligono.getArestas();
        Ponto bottomLeft = rect.getDownLeft();
        Ponto topRight = rect.getTopRight();

        //verificar se o ponto está no retangulo envolvente
        if(this.x < bottomLeft.x || this.x > topRight.x)
            return false;
        if(this.y < bottomLeft.y || this.y > topRight.y)
            return false;

        topRight = new Ponto(topRight.getX() + 1, topRight.getY());
        // tenho o ponto topRight
        Segmento segmentoVerificacao = new Segmento(this, topRight);
        //iterar sobre as arestas todas e ver quando interseta o segmento com a aresta, fazer um counter, se for par o ponto esta fora do poligono, se for impar o ponto esta dentro do poligono
        int intersectionCounter = 0;
        for (int i = 0; i < arestasPoligono.length; i++) {
            if (arestasPoligono[i].cruzamento2(segmentoVerificacao))
                intersectionCounter++;
            if (arestasPoligono[i].getP1().equals(this) || arestasPoligono[i].getP2().equals(this))
                intersectionCounter--;
        }
        boolean result;
        if(intersectionCounter % 2 == 0)
            result = false;
        else
            result = true;
        return  result;
    }

    public boolean isInside(Circulo c)
    {
        return this.dist(c.getCenter()) <= c.getRadius();
    }

    public boolean isInside(Quadrado q)
    {
        return this.x >= q.getDownLeft().getX() && this.x <= q.getTopRight().getX()  && this.y >= q.getDownLeft().getY() && this.y <= q.getTopRight().getY();
    }
}