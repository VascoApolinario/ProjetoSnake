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
     * Construtor da classe Ponto
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
        System.out.println("Ponto:vi");
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
}