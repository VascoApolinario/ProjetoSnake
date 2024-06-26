package FigurasGeo;

/**
 * Classe responsável para representar um segmento de reta.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.3 ,04/03/2024
 * @inv Os dois pontos que definem o segmento têm de ter coordenadas diferentes.
*/
public class Segmento
{
    private final Ponto p1,p2;
    private final Reta r;

    /** Construtor de segmento a partir de 2 pontos
     * @param p1 ponto 1
     * @param p2 ponto 2
     */
    public Segmento(Ponto p1, Ponto p2)
    {
        if(Math.abs(p1.getX() - p2.getX()) < Math.pow(10,-9)  && Math.abs(p1.getY() - p2.getY()) < Math.pow(10,-9))
        {
            System.out.println("Segmento:vi");
            System.exit(0);
        }
        this.p1 = new Ponto(p1.getX(),p1.getY());
        this.p2 = new Ponto(p2.getX(),p2.getY());
        this.r = new Reta(this.p1,this.p2);
    }

    /**
     * Metodo que retorna comprimento do segmento
     * @return lenght
     */
    public double lenght()
    {
        return this.p1.dist(this.p2);
    }

    /**
     * Devolve o primeiro ponto do segmento
     * @return ponto1
     */

    public Ponto getP1() {
        return p1;
    }

    /**
     * Devolve o segundo ponto do segmento
     * @return ponto2
     */

    public Ponto getP2() {
        return p2;
    }

    /**
     * Devolve a reta à qual este segmento pertence
     * @return reta
     */

    public Reta getR() {
        return r;
    }

    /**
     * Metodo usado para verificar se ocorre cruzamento com o segmento that.
     * @param that outro segmento a verificar
     * @return true se houver cruzamento, false se não.
     */
    public boolean cruzamento(Segmento that)
    {
        if(this.r.sameSlope(that.r)) //se os segmentos forem paralelos ou coincidentes não se intersetam
            return false;

        double x;
        double y;
        double maxX;
        double minX;
        if(this.r.getVertical() && !that.r.getVertical()) //caso o this.r seja um segmento de reta vertical
        {
            x = this.p1.getX();
            y = that.r.getDeclive() * x + that.r.getB();

            if (x < 0 || y < 0)
                return false;

            maxX = Math.max(that.p1.getX(), that.p2.getX());
            minX = Math.min(that.p1.getX(), that.p2.getX());
            if (x > (minX + 1e-9) && x < (maxX - 1e-9)) {
                double minY = Math.min(this.p1.getY(),this.p2.getY());
                double maxY = Math.max(this.p1.getY(),this.p2.getY());
                return y > (minY + 1e-9) && y < (maxY - 1e-9);
            }
            else
                return false;
        }
        else if(that.r.getVertical() && !this.r.getVertical()) //caso o that.r seja um segmento de reta vertical
        {
            x = that.p1.getX();
            y = this.r.getDeclive() * x + this.r.getB();

            if (x < 0 || y < 0)
                return false;

            maxX = Math.max(this.p1.getX(), this.p2.getX());
            minX = Math.min(this.p1.getX(), this.p2.getX());

            if (x > (minX + 1e-9) && x < (maxX - 1e-9)) {
                double minY = Math.min(that.p1.getY(),that.p2.getY());
                double maxY = Math.max(that.p1.getY(),that.p2.getY());
                return y > (minY + 1e-9) && y < (maxY - 1e-9);
            }
            else
                return false;
        }
        else //nenhum dos segmentos de reta seja vertical
        {
            //ponto onde intersetam as retas
            x = (that.r.getB() - this.r.getB()) / (this.r.getDeclive() - that.r.getDeclive());
            y = this.r.getDeclive() * x + this.r.getB();
            //se o ponto nao pertencer ao primeiro quadrante, entao nao ocorre cruzamento entre segmentos.
            if (x < 0 || y < 0)
                return false;

            maxX = Math.max(this.p1.getX(), this.p2.getX());
            minX = Math.min(this.p1.getX(), this.p2.getX());
            if (x >= (minX + 1e-9) && x < (maxX -1e-9)) {
                maxX = Math.max(that.p1.getX(), that.p2.getX());
                minX = Math.min(that.p1.getX(), that.p2.getX());
                return x > (minX + 1e-9) && x < (maxX - 1e-9);
            }
            else
                return false;
        }
    }

    /**
     * Verifica se um ponto pertence a este segmento
     * @param x Cordenadas X do ponto a verificar
     * @param y Cordenadas Y do ponto a verificar
     * @return  true se pertencer, false caso contrário
     */
    public boolean pertenceSeg(double x, double y) {
        double Minx = Math.min(p1.getX(), p2.getX());
        double Maxx = Math.max(p1.getX(), p2.getX());
        double Miny = Math.min(p1.getY(), p2.getY());
        double Maxy = Math.max(p1.getY(), p2.getY());


        boolean dentroX = x >= Minx && x <= Maxx;
        boolean dentroY = y >= Miny && y <= Maxy;
        return dentroX && dentroY;

    }
    /**
     * Verifica se um ponto pertence a este segmento
     * @param p Ponto a verificar
     * @return  true se pertencer, false caso contrário
     */

    public boolean pertenceSeg(Ponto p) {
        double x = p.getX();
        double y = p.getY();

        double Minx = Math.min(p1.getX(), p2.getX());
        double Maxx = Math.max(p1.getX(), p2.getX());
        double Miny = Math.min(p1.getY(), p2.getY());
        double Maxy = Math.max(p1.getY(), p2.getY());


        boolean dentroX = x >= Minx && x <= Maxx;
        boolean dentroY = y >= Miny && y <= Maxy;
        return dentroX && dentroY;

    }




}
