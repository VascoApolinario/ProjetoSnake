package FigurasGeo;

/**
 * Classe responsável em representar uma reta.
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.1 , 22/02/2024
 * @inv Os dois pontos que são usados para calcular a equação da reta têm de ter coordenadas diferentes.
 */
public class Reta {

    private double declive,b;
    private final boolean vertical;


    /**
     * Construtor de reta
     * @param p1 ponto da reta
     * @param p2 ponto da reta
     * @pre os dois pontos não têm as mesmas coordenadas
     */
    public Reta(Ponto p1, Ponto p2)
    {
        if(Math.abs(p1.getX() - p2.getX()) < Math.pow(10,-9)  && Math.abs(p1.getY() - p2.getY()) < Math.pow(10,-9))
        {
            System.out.println("Reta:vi");
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

    /**
     * Getter do declive da reta
     * @return declive da reta
     */
    public double getDeclive()
    {
        return this.declive;
    }

    /**
     * Getter da ordenada na origem da reta
     * @return ordenada na origem
     */
    public double getB()
    {
        return this.b;
    }

    /**
     * Getter da variavel vertical  (indica se a reta é vertical ou nao)
     * @return variavel vertical
     */
    public boolean getVertical()
    {
        return this.vertical;
    }

    /**
     * metodo para verificar se duas retas têm o mesmo declive (ou se ambas são verticais)
     * @param that reta a comparar
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
