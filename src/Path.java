import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import FigurasGeo.Segmento;

import java.util.ArrayList;
/**
 * Classe responsável em representar uma trajetoria.
 * @author [Diogo Almeida 79810]
 * @version 2.1 , 22/02/2024
 * @inv O array de pontos que define a trajetoria tem que conter mais do que 1 ponto.
 */
public class Path {
    private final Ponto[] pontos;
    private final ArrayList<Segmento> linhas;

    /**
     *
     * @param pontos
     */
    public Path(Ponto[] pontos)
    {
        if(pontos.length < 2)
        {
            System.out.println("Trajetoria:vi");
            System.exit(0);
        }
        Ponto[] points = new Ponto[pontos.length];
        for(int i = 0; i < pontos.length;i++)
        {
            points[i] = new Ponto(pontos[i].getX(),pontos[i].getY());
        }
        this.pontos = points;
        ArrayList<Segmento> linhas = new ArrayList<>();
        for(int i = 1; i < pontos.length;i++)
        {
            linhas.add(new Segmento(pontos[i-1],pontos[i]));
        }
        this.linhas = linhas;
    }

    /**
     * Metodo que calcula e retorna o comprimento total da trajetoria
     * @return comprimento total da trajetoria
     */
    public double length()
    {
        double sum = 0;
        for(int i = 1; i < this.pontos.length;i++)
        {
            sum += pontos[i-1].dist(pontos[i]);
        }
        return sum;
    }

    /**
     * metodo que verifica se alguma linha da trajetoria interseta alguma aresta do poligono
     * @param that
     * @return 1 se houver intersecção, 0 se não
     */
    public int interseta(Poligono that)
    {
        for(Segmento l : this.linhas)
        {
            for(Segmento a : that.getArestas())
            {
                if(l.cruzamento(a)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public Ponto[] getPontos() {
        return pontos;
    }

}
