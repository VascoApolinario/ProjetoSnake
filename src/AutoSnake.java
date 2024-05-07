import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.util.ArrayList;

public class AutoSnake {
    private Path snakepath;
    private boolean searching;
    private int count;

    public AutoSnake() {
        this.searching = true;
        this.count = 1;
    }

    public void Start(Snake snake, Background background){

            if (searching) {
                this.snakepath = Search(background);
                this.searching = false;

            } else {
                if (snakepath != null) {
                    snake.setStatus(Status.ALIVE);
                    followPath(snake, this.snakepath);
                } else {
                    End(snake);
                }

            }


    }

    public void End(Snake snake){
        System.out.println("END!");
    }

    public Path Search(Background bg){
        Path path = null;
        for (Food food : bg.getComida()) {
            double x = 0;
            double y = 0;
            double[] options = new double[2];
            Ponto[] pontos = new Ponto[3];
            pontos[0] = bg.getSnake().getHead().getCentroide();
            if(food instanceof SquareFood){
                x = ((SquareFood) food).getQuadrado().getCentroide().getX();
                y = ((SquareFood) food).getQuadrado().getCentroide().getY();
                pontos[2] = ((SquareFood) food).getQuadrado().getCentroide();
            }
            else if (food instanceof CircleFood) {
                x  = ((CircleFood) food).getCirculo().getCenter().getX();
                y  = ((CircleFood) food).getCirculo().getCenter().getY();
                pontos[2] = ((CircleFood) food).getCirculo().getCenter();
            }
            options[0] = x;
            options[1] = y;

            for(int i = 0; i < options.length; i++)
            {
                pontos[1] = new Ponto(options[0],bg.getSnake().getHead().getCentroide().getY());
                if(pontos[1].equals(pontos[2]) || pontos[1].equals(pontos[0])){
                    Ponto[] pts = new Ponto[2];
                    pts[0] = pontos[0];
                    pts[1] = pontos[2];
                    path = new Path(pts);
                }
                else
                    path = new Path(pontos);
                if(validPath(path,bg))
                    break;
            }

        }
        return path;
    }



    public boolean validPath(Path p,Background bg){
        for (Obstacle obstacle : bg.getObstaculos()) {
            if(p.interseta(obstacle.getPoligono()) == 1)
                return false;
        }
        for (Quadrado t : bg.getSnake().getTail())
        {
            if(p.interseta(t) == 1)
                return false;
        }
        return true;
    }


    public void followPath(Snake snake,Path path){

            if(snake.getHead().getCentroide().equals(path.getPontos()[count]))
            {
                count++;
            }
            if(count >= path.getPontos().length) {
                this.searching = true;
                this.count = 1;
            }
            if(snake.getDirection() == 0 || snake.getDirection() == 180) // se a cobra estiver numa direção horizontal
            {
                if (path.getPontos()[count].getY() < snake.getHead().getCentroide().getY()) // se o y do 2º ponto está em cima da snake
                {
                    snake.rotate(90);
                } else if (path.getPontos()[count].getY() > snake.getHead().getCentroide().getY()) // se o y do 2º ponto está abaixo da snake
                {
                    snake.rotate(270);
                }
            }
            else
            {
                if(path.getPontos()[count].getX() < snake.getHead().getCentroide().getX()) // se o x do 2º ponto está à esquerda da snake
                {
                    snake.rotate(180);
                }
                else if(path.getPontos()[count].getX() > snake.getHead().getCentroide().getX()) // se o x do 2º ponto está à direita da snake
                {
                    snake.rotate(0);
                }
            }
    }
}






