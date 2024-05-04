package FigurasGeo;

/**
 * Classe responsável para representar um retângulo que envolve um poligono. É usado no pre-processamento para ver se dois poligonos se intersetam.
 * @author [Diogo Almeida 79810]
 * @version 1.0 , 04/03/2024
 */
public class PreProcessamento {
    private Ponto downLeft, topRight;


    /**
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     */
    public PreProcessamento(int minX, int maxX, int minY, int maxY)
    {
        this.downLeft = new Ponto(minX,minY);
        this.topRight = new Ponto(maxX,maxY);
    }


    public PreProcessamento(Poligono poligono)
    {
        Ponto[] pontosPoligono = poligono.getPontos();
        Ponto topRight = pontosPoligono[0];
        Ponto bottomLeft = pontosPoligono[0];
        for (int i = 1; i < pontosPoligono.length; i++) {
            if (pontosPoligono[i].getX() > topRight.getX()) {
                topRight = new Ponto(pontosPoligono[i].getX(), topRight.getY());
            }
            if (pontosPoligono[i].getY() > topRight.getY()) {
                topRight = new Ponto(topRight.getX(), pontosPoligono[i].getY());
            }
            if (pontosPoligono[i].getX() < bottomLeft.getX()) {
                bottomLeft = new Ponto(pontosPoligono[i].getX(), bottomLeft.getY());
            }
            if (pontosPoligono[i].getY() < bottomLeft.getY()) {
                bottomLeft = new Ponto(bottomLeft.getX(), pontosPoligono[i].getY());
            }
        }
        this.downLeft = bottomLeft;
        this.topRight = topRight;
    }


    /**
     * Metodo usado para verificar se dois retângulos se cruzam
     * @param that
     * @return true se houver cruzamento, false se não.
     */
    public boolean rectIntercept(PreProcessamento that)
    {
        if(this.downLeft.getX() < that.topRight.getX() && that.downLeft.getX() < this.topRight.getX())
            return (this.downLeft.getY() < that.topRight.getY() && that.downLeft.getY() < this.topRight.getY());
        else
            return false;
    }

    public Ponto getDownLeft() {
        return downLeft;
    }

    public Ponto getTopRight() {
        return topRight;
    }
}
