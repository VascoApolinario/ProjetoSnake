package FigurasGeo;

import javax.swing.text.Segment;

public class Circulo {
    private Ponto center;
    private int radius;

    public Circulo(Ponto center, int radius) {
        this.center = new Ponto(center.getX(), center.getY());
        this.radius = radius;
    }

    public Ponto getCenter() {
        return center;
    }

    public void setCenter(Ponto center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean collidesWithPolygon(Poligono p)
    {
        Ponto[] pontos = new Ponto[4];
        pontos[0] = new Ponto(center.getX() - radius, center.getY() - radius);
        pontos[1] = new Ponto(center.getX() + radius, center.getY() - radius);
        pontos[2] = new Ponto(center.getX() + radius, center.getY() + radius);
        pontos[3] = new Ponto(center.getX() - radius, center.getY() + radius);
        Quadrado quadradoEnvolvente = new Quadrado(pontos);
        if(quadradoEnvolvente.polygonsIntercept(p)){
            for(Segmento s : p.getArestas())
            {
                if(this.collidesWithSegment(s))
                    return true;
            }
        }
        return false;
    }

    public boolean collidesWithSegment(Segmento s)
    {
        Ponto closestPoint;
        double dot = (((this.center.getX() - s.getP1().getX()) * (s.getP2().getX() - s.getP1().getX())) + (this.center.getY() - s.getP1().getY()) * (s.getP2().getY() - s.getP1().getY()))/(s.lenght()*s.lenght());

        if (dot < 0) {
            closestPoint = s.getP1();
        } else if (dot > 1) {
            closestPoint = s.getP2();
        } else {
            closestPoint = new Ponto(s.getP1().getX() + dot * (s.getP2().getX() - s.getP1().getX()), s.getP1().getY() + dot * (s.getP2().getY() - s.getP1().getY()) );
        }
        return this.center.dist(closestPoint) < this.radius;
    }

}
