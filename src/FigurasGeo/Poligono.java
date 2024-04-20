package FigurasGeo;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe responsável para representar um poligono.
 * @author [Diogo Almeida 79810, André Guerreiro 79809]
 * @version 2 , 26/03/2024
 * @inv O array de pontos que define o poligono tem que conter mais do que 2 pontos.
 * @inv Não pode existir 3 pontos consecutivos colineares.
 * @inv Nenhum par de arestas pode cruzar-se entre si.
 */
public class Poligono {

    private Ponto[] pontos;
    private Segmento[] arestas;

    private Ponto centroide;

    /**
     * Construtor da classe FigurasGeo.Poligono
     * @param pontos do poligono
     */
    public Poligono(Ponto[] pontos)
    {
        // criação de um array com todas as arestas que formam o poligono, depois verifica-se se ocorre cruzamento entre arestas.
        Segmento[] arestas = new Segmento[pontos.length];
        arestas[0] = new Segmento(pontos[pontos.length-1],pontos[0]);
        for(int i = 1;i < arestas.length;i++)
        {
            arestas[i] = new Segmento(pontos[i-1],pontos[i]);
        }
        verificarInvariantes(pontos,arestas);
        this.arestas = arestas;
        Ponto[] points = new Ponto[pontos.length];
        double centroideX = 0;
        double centroideY = 0;
        for(int i = 0; i < pontos.length;i++)
        {
            points[i] = new Ponto(pontos[i].getX(),pontos[i].getY());
            centroideX += pontos[i].getX();
            centroideY += pontos[i].getY();
        }
        this.pontos = points;
        centroideX = centroideX/pontos.length;
        centroideY = centroideY/pontos.length;
        this.centroide = new Ponto(centroideX,centroideY);
    }

    public Poligono(String str)
    {
        this(stringToPoints(str));

    }

    private static Ponto[] stringToPoints (String str)
    {
        int n;
        String[] parts = str.split(" ",2);
        n = Integer.parseInt(parts[0]);
        String[] coordenadas = parts[1].split(" ");
        Ponto[] pts = new Ponto[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Ponto(Integer.parseInt(coordenadas[i * 2]), Integer.parseInt(coordenadas[i * 2 + 1]));
        }
        return pts;
    }



    /**
     * Método que verifica se as invariantes da classe são respeitadas
     * @param pontos
     * @param arestas
     */
    private void verificarInvariantes(Ponto[] pontos, Segmento[] arestas)
    {
        if(pontos.length < 3) // verifica se existem pontos suficientes para fazer o poligono
        {
            System.out.println("FigurasGeo.Poligono:vi");
            System.exit(0);
        }
        tresPontosColineares(pontos);
        for(int i = 0; i < arestas.length;i++)
        {
            for(int j = i+1;j<arestas.length;j++)
            {
                if(arestas[i].cruzamento(arestas[j]))
                {
                    System.out.println("FigurasGeo.Poligono:vi");
                    System.exit(0);
                }
            }
        }

    }

    /**
     * Metodo chamado pelo construtor para verificar se existem 3 pontos colineares consecutivos.
     * @param pontos
     */
    private void tresPontosColineares(Ponto[] pontos)
    {
        Reta reta1 = new Reta(pontos[pontos.length-1],pontos[0]);    // Verifica se existem 3 pontos colineares consecutivos atraves das retas 1 e 2. Se ambas forem paralelas logo os 3 pontos consecutivos são colineares.
        Reta reta2;
        for(int i = 1; i < pontos.length; i++)
        {
            reta2 = new Reta(pontos[i - 1], pontos[i]);
            if (reta1.sameSlope(reta2)) {
                System.out.println("FigurasGeo.Poligono:vi");
                System.exit(0);
            } else
                reta1 = reta2;
        }
        if(reta1.sameSlope(new Reta(pontos[pontos.length-1],pontos[0])))
        {
            System.out.println("FigurasGeo.Poligono:vi");
            System.exit(0);
        }
    }

    /**
     * Metodo que calcula e retorna o perimetro do poligono
     * @return inteiro perimetro
     */
    public int perimetro()
    {
        double sum = 0;
        for(int i = 1; i < this.pontos.length;i++)
        {
            sum += pontos[i-1].dist(pontos[i]);
        }
        sum += pontos[pontos.length-1].dist(pontos[0]);
        return (int)sum;
    }

    public Ponto[] getPontos()
    {
        return this.pontos;
    }
    public Segmento[] getArestas()
    {
        return this.arestas;
    }

    /**
     * Método que calcula intercepção entre poligonos
     * @param that poligono com que iremos calcular se existe intercepção
     * @return true se interceptar false se não
     */
    public boolean polygonsIntercept(Poligono that)
    {
        for(Segmento a : this.arestas)
        {
            for(Segmento b : that.getArestas())
            {
                if(a.cruzamento(b)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo que cria a representação em String de um poligono
     * @return String que representa um poligono
     */
    @Override
    public String toString(){
        return "FigurasGeo.Poligono de " +this.pontos.length+" vertices: " + Arrays.toString(this.pontos);
    }

    /**
     * Metodo que chama o construtor da sua classe e cria uma nova instância
     * @param pontos do poligono
     * @return poligono
     */
    protected Poligono createInstance(Ponto[] pontos) {
        return new Poligono(pontos);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poligono poligono)) return false;
        if(this.pontos.length != poligono.pontos.length)
            return false;
        int k;
        boolean b = false;
        for(int i = 0; i < this.pontos.length; i++)
        {
            if(this.pontos[0].equals(poligono.pontos[i]))
            {
                k = i;
                b = true;
                for(int j = 1; j < this.pontos.length;j++)
                {
                    if(!this.pontos[j].equals(poligono.pontos[(j+k)%poligono.pontos.length]))
                        return false;
                }
            }
        }
        return b;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(centroide);
        result = 31 * result + Arrays.hashCode(pontos);
        result = 31 * result + Arrays.hashCode(arestas);
        return result;
    }

    /**
     * Metodo que aplica um movimento de rotação no poligono
     * @param degrees angulo de rotação em graus
     * @param pivotX coordenada X do ponto de rotação
     * @param pivotY coordenada Y do ponto de rotação
     * @return um novo poligono que sofreu a rotação desejada
     * @see "https://en.wikipedia.org/wiki/Rotation_matrix"
     */
    public Poligono rotate(int degrees,double pivotX,double pivotY)
    {
        Ponto[] rotatedPoints = new Ponto[this.pontos.length];
        for(int i = 0; i < this.pontos.length;i++)
        {
            rotatedPoints[i] = this.pontos[i].rotate(degrees,pivotX,pivotY);
        }
        return createInstance(rotatedPoints);
    }

    /**
     * Metodo que aplica um movimento de rotação no poligono (baseado no centroide)
     * @param degrees angulo de rotação em graus
     * @return novo poligono que sofreu a rotação desejada
     */
    public Poligono rotate(int degrees)
    {
        return rotate(degrees,this.centroide.getX(),this.centroide.getY());
    }

    /**
     * Metodo que aplica um movimento de tranlação no poligono baseado num desvio dx e dy
     * @param dx desvio no eixo x
     * @param dy desvio no eixo y
     * @return novo poligono que sofreu o movimento de translação desejado
     */
    public Poligono move(int dx, int dy)
    {
        Ponto[] movedPoints = new Ponto[this.pontos.length];
        for(int i = 0; i <this.pontos.length; i++)
        {
            movedPoints[i] = pontos[i].translate(dx,dy);
        }
        return createInstance(movedPoints);
    }

    /**
     * Metodo que aplica um movimento de tranlação no poligono baseado na atribuição de um novo centroide
     * @param newCentroidX coordenadas x do novo centroide
     * @param newCentroidY coordenadas y do novo centroide
     * @return novo poligono que sofreu o movimento de translação desejado
     */
    public Poligono moveCentroid(int newCentroidX,int newCentroidY)
    {
        int dx = newCentroidX - (int)this.centroide.getX();
        int dy = newCentroidY - (int)this.centroide.getY();
        return move(dx,dy);
    }

    public void draw(Graphics g) {
        // Draw the polygon
        int[] xPoints = new int[pontos.length];
        int[] yPoints = new int[pontos.length];
        for (int i = 0; i < pontos.length; i++) {
            xPoints[i] = (int) pontos[i].getX();
            yPoints[i] = (int) pontos[i].getY();
        }
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, pontos.length);
    }

}
