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

}
