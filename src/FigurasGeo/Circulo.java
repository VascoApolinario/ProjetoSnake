package FigurasGeo;

import javax.swing.text.Segment;

public class Circulo {
    private Ponto center;
    private double radius;

    public Circulo(Ponto center, double radius) {
        this.center = new Ponto(center.getX(), center.getY());
        this.radius = radius;
    }

    public Ponto getCenter() {
        return center;
    }

    public void setCenter(Ponto center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

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
