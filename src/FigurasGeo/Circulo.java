package FigurasGeo;
import javax.swing.text.Segment;

/** Classe responsável em representar um círculo.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.0 09/05/2024
 * @inv raio tem que ser superior a 0.
 *
 */

public class Circulo {
    private Ponto center;
    private double radius;


    /**
     * Construtor da classe Círculo
     * @param center centro do circulo
     * @param radius raio do circulo
     * @pre receber um ponto e raio validos.
     * @post cria uma nova instância de circulo
     */
    public Circulo(Ponto center, double radius) {
        if(radius <= 0)
        {
            throw new IllegalArgumentException("Circulo: vi. Radius must be greater than 0");
        }
        this.center = new Ponto(center.getX(), center.getY());
        this.radius = radius;
    }

    /**
     * Getter que devolve o centro do círculo
     * @return this.center
     */
    public Ponto getCenter() {
        return this.center;
    }

    /**
     * Setter que altera o centro do círculo
     * @pre receber um ponto valido.
     * @post altera o centro para o ponto recebido
     */
    public void setCenter(Ponto center) {
        this.center = center;
    }

    /**
     * Getter que devolve o raio do círculo
     * @return this.radius
     */
    public double getRadius() {
        return this.radius;
    }


    /**
     * Setter que altera o raio do círculo
     * @pre receber um raio valido.
     * @post altera o raio do circulo
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Método que verifica se o quadrado que envolve o círculo colide com um poligono.
     * @param p poligono que iremos verificar as colisões.
     * @return true se colidir, false se não
     */
    public boolean envolvingSquareCollides(Poligono p)
    {
        Ponto[] pontos = new Ponto[4];
        pontos[0] = new Ponto(center.getX() - radius, center.getY() - radius);
        pontos[1] = new Ponto(center.getX() + radius, center.getY() - radius);
        pontos[2] = new Ponto(center.getX() + radius, center.getY() + radius);
        pontos[3] = new Ponto(center.getX() - radius, center.getY() + radius);
        Quadrado quadradoEnvolvente = new Quadrado(pontos);
        return quadradoEnvolvente.polygonsIntercept(p);
    }

    /**
     * Método que verifica se o circulo colide com um poligono.
     * @param p poligono que iremos verificar as colisões.
     * @return true se colidir, false se não
     */
    public boolean collidesWithPolygon(Poligono p)
    {
        if(envolvingSquareCollides(p)){// ve se o quadrado envolvente colide
            for(Segmento s : p.getArestas()) //se colidir ve se em todas as arestas se o ponto mais proximo do centro ta a uma distancia do centro < do raio
            {
                if(this.collidesWithSegment(s))
                    return true;
            }
        }
        return false;
    }

    /**
     * Método que verifica se o circulo colide com um segmento de reta.
     * @param s segmento de reta que iremos verificar a colisão
     * @return true se colidir, false se não
     */
    public boolean collidesWithSegment(Segmento s)  //esta função procura ponto mais perto do segmento e ve se a dist entre centro e esse ponto é menor do que o raio (se for colide)
    {
        Ponto closestPoint;
        double dot = (((this.center.getX() - s.getP1().getX()) * (s.getP2().getX() - s.getP1().getX())) + (this.center.getY() - s.getP1().getY()) * (s.getP2().getY() - s.getP1().getY()))/(s.lenght()*s.lenght());

        if (dot < 0) { //ponto mais perto está para além do P1
            closestPoint = s.getP1();
        } else if (dot > 1) { //ponto mais perto está para além do P2
            closestPoint = s.getP2();
        } else { //ponto mais perto está entre p1 e p2
            closestPoint = new Ponto(s.getP1().getX() + dot * (s.getP2().getX() - s.getP1().getX()), s.getP1().getY() + dot * (s.getP2().getY() - s.getP1().getY()) );
        }
        return this.center.dist(closestPoint) < this.radius;
    }

}
