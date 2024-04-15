package FigurasGeo;

/**
 * Classe responsável em representar uma reta.
 * @author [Diogo Almeida 79810]
 * @version 1.1 , 22/02/2024
 * @inv Os dois pontos que são usados para calcular a equação da reta têm de ter coordenadas diferentes.
 */
public class Reta {

    private double declive,b;
    private final boolean vertical;


    /**
     * @param p1
     * @param p2
     */
    public Reta(Ponto p1, Ponto p2)
    {
        if(Math.abs(p1.getX() - p2.getX()) < Math.pow(10,-9)  && Math.abs(p1.getY() - p2.getY()) < Math.pow(10,-9))
        {
            System.out.println("FigurasGeo.Reta:vi");
            System.exit(0);
        }
        if(p1.getX() == p2.getX())
        {
            this.vertical = true;
            this.b = p1.getX();
        }
        else {
            this.vertical = false;
            this.declive = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
            this.b = p1.getY() - declive * p1.getX();
        }
    }

    public double getDeclive()
    {
        return this.declive;
    }

    public double getB()
    {
        return this.b;
    }

    public boolean getVertical()
    {
        return this.vertical;
    }

    /**
     * metodo para verificar se duas retas têm o mesmo declive (ou se ambas são verticais)
     * @param that
     * @return true se tiverem o mesmo declive, false se não
     */
    public boolean sameSlope(Reta that)
    {
        if(this.vertical && that.vertical)
        {
            return true;
        }
        else if(this.vertical || that.vertical)
        {
            return false;
        }
        else
            return this.declive == that.declive;
    }
}
