import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.util.ArrayList;

public class AutoSnake {
    private Background bg;
    private Path snakepath;
    private boolean searching;

    public AutoSnake(Background background) {
        this.bg = background;
        this.searching = true;


    }

    public void Start(){
        bg.getSnake().setStatus(Status.ALIVE);
    }

    public void End(){

    }

    public Path Search(){
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
                path = new Path(pontos);
                if(validPath(path))
                    break;
            }

        }
        return path;
    }



    public boolean validPath(Path p){
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

    //falta quando a snake chega ao ultimo ponto
    public void followPath(Snake snake,Path path){
       if(snake.getDirection() == 0 || snake.getDirection() == 180) // se a cobra estiver numa direção horizontal
       {
           if(path.getPontos()[1].getY() < snake.getHead().getCentroide().getY()) // se o y do 2º ponto está em cima da snake
           {
               snake.rotate(90);
           }
           else if(path.getPontos()[1].getY() > snake.getHead().getCentroide().getY()) // se o y do 2º ponto está abaixo da snake
           {
               snake.rotate(270);
           }

           if(path.getPontos()[1].equals(snake.getHead().getCentroide())) //a snake ja chegou no segundo ponto
           {
               if(path.getPontos()[2].getY() < snake.getHead().getCentroide().getY()) // se o y do ultimo ponto está em cima da snake
               {
                   snake.rotate(90);
               }
               else if(path.getPontos()[2].getY() > snake.getHead().getCentroide().getY()) // se o y do ultimo ponto está abaixo da snake
               {
                   snake.rotate(270);
               }
           }

       }
       else // se a cobra estiver numa direção vertical
       {
           if(path.getPontos()[1].getX() < snake.getHead().getCentroide().getX()) // se o x do 2º ponto está à esquerda da snake
           {
               snake.rotate(180);
           }
           else if(path.getPontos()[1].getX() > snake.getHead().getCentroide().getX()) // se o x do 2º ponto está à direita da snake
           {
               snake.rotate(0);
           }

           if(path.getPontos()[1].equals(snake.getHead().getCentroide())) //a snake ja chegou no segundo ponto
           {
               if(path.getPontos()[2].getX() < snake.getHead().getCentroide().getX()) // se o x do ultimo ponto está à esquerda da snake
               {
                   snake.rotate(180);
               }
               else if(path.getPontos()[2].getX() > snake.getHead().getCentroide().getX()) // se o x do ultimo ponto está à direita da snake
               {
                   snake.rotate(0);
               }
           }

       }
    }




}

