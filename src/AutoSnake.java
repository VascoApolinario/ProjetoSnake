import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.util.ArrayList;

public class AutoSnake {
    private Path snakepath;
    private boolean searching;
    private boolean avoiding;
    private int count;

    public AutoSnake() {
        this.searching = true;
        this.avoiding = false;
        this.count = 1;
    }

    public void Start(Snake snake, Background background){
        avoidObstacle(snake,background.getGrid());
        if (searching) {
            this.snakepath = Search(background,snake);
            this.searching = false;

        } else {

            if (snakepath != null && !avoiding) {
                snake.setStatus(Status.ALIVE);
                followPath(snake, this.snakepath, background);
            } else {
                this.searching = true;
            }

        }


    }

    public Food getCloserFood(Background background, Snake snake){
        Food food = background.getComida().getFirst();
        Double dist = background.getComida().getFirst().getLocation().dist(snake.getHead().getCentroide());
        for(int i = 0; i < background.getComida().size();i++)
        {
            if(background.getComida().get(i).getLocation().dist(snake.getHead().getCentroide()) < dist)
                food = background.getComida().get(i);
        }
        return food;
    }


    public Path Search(Background bg, Snake snake){
        Path path = null;
        Food food = getCloserFood(bg,snake);
        double x = 0;
        double y = 0;
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(bg.getSnake().getHead().getCentroide());
        x = food.getLocation().getX();
        y = food.getLocation().getY();
        Ponto intermedio;
        Ponto ultimo = new Ponto(x, y);
        intermedio = new Ponto(x,bg.getSnake().getHead().getCentroide().getY());
        if(!intermedio.equals(pontos.getFirst()) && !intermedio.equals(ultimo))
        {
            pontos.add(intermedio);
        }
        pontos.add(ultimo);
        path = new Path( pontos.toArray(new Ponto[0]));
        if(validPath(path,bg))
            return path;

        pontos.clear();
        pontos.add(bg.getSnake().getHead().getCentroide());
        intermedio = new Ponto(bg.getSnake().getHead().getCentroide().getX(),y);
        if(!intermedio.equals(pontos.getFirst()) && !intermedio.equals(ultimo))
        {
            pontos.add(intermedio);
        }
        pontos.add(ultimo);
        path = new Path( pontos.toArray(new Ponto[0]));
        if(validPath(path,bg))
            return path;
        else
            return null;
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


    public void followPath(Snake snake,Path path,Background background){
        int row = background.getGrid().returnRowFromPoint(snake.getHead().getCentroide());
        int col = background.getGrid().returnColFromPoint(snake.getHead().getCentroide());

        if(count < path.getPontos().length && snake.getHead().getCentroide().equals(path.getPontos()[count]))
        {
            count++;
        }
        if(count >= path.getPontos().length) {

            this.searching = true;
            this.count = 1;
        }
        if(snake.getDirection() == 0 || snake.getDirection() == 180) // se a cobra estiver numa direção horizontal
        {
            if (path.getPontos()[count].getY() < snake.getHead().getCentroide().getY() && !background.getGrid().getCells()[row-1][col].getContent().equals(Content.OBSTACLE) && !background.getGrid().getCells()[row-1][col].getContent().equals(Content.TAIL)) // se o y do 2º ponto está em cima da snake
            {
                snake.rotate(90);
            } else if (path.getPontos()[count].getY() > snake.getHead().getCentroide().getY() && !background.getGrid().getCells()[row+1][col].getContent().equals(Content.OBSTACLE) && !background.getGrid().getCells()[row+1][col].getContent().equals(Content.TAIL)) // se o y do 2º ponto está abaixo da snake
            {
                snake.rotate(270);
            }
        }
        else
        {
            if(path.getPontos()[count].getX() < snake.getHead().getCentroide().getX() && !background.getGrid().getCells()[row][col-1].getContent().equals(Content.OBSTACLE) && !background.getGrid().getCells()[row][col-1].getContent().equals(Content.TAIL)) // se o x do 2º ponto está à esquerda da snake
            {
                snake.rotate(180);
            }
            else if(path.getPontos()[count].getX() > snake.getHead().getCentroide().getX() && !background.getGrid().getCells()[row][col+1].getContent().equals(Content.OBSTACLE) && !background.getGrid().getCells()[row][col+1].getContent().equals(Content.TAIL)) // se o x do 2º ponto está à direita da snake
            {
                snake.rotate(0);
            }
        }
    }

    public void avoidObstacle(Snake snake,Grid grid){
        int row = grid.returnRowFromPoint(snake.getHead().getCentroide());
        int col = grid.returnColFromPoint(snake.getHead().getCentroide());
        if(snake.getDirection() == 0)
        {
            if(grid.getCells()[row][col+1].getContent().equals(Content.OBSTACLE)||grid.getCells()[row][col+1].getContent().equals(Content.BORDER)||grid.getCells()[row][col+1].getContent().equals(Content.TAIL))
            {
                this.avoiding = true;
                if(grid.getCells()[row-1][col].getContent().equals(Content.OBSTACLE) ||grid.getCells()[row-1][col].getContent().equals(Content.BORDER) ||grid.getCells()[row-1][col].getContent().equals(Content.TAIL) )
                {
                    snake.rotate(270);
                }
                else
                    snake.rotate(90);
            }
            else
                this.avoiding = false;
        }
        else if(snake.getDirection() == 180)
        {
            if(grid.getCells()[row][col-1].getContent().equals(Content.OBSTACLE)||grid.getCells()[row][col-1].getContent().equals(Content.BORDER) ||grid.getCells()[row][col-1].getContent().equals(Content.TAIL))
            {
                this.avoiding = true;
                if(grid.getCells()[row-1][col].getContent().equals(Content.OBSTACLE) || grid.getCells()[row-1][col].getContent().equals(Content.BORDER) || grid.getCells()[row-1][col].getContent().equals(Content.TAIL))
                {
                    snake.rotate(270);
                }
                else
                    snake.rotate(90);
            }
            else
                this.avoiding = false;

        } else if (snake.getDirection() == 90) {
            if(grid.getCells()[row-1][col].getContent().equals(Content.OBSTACLE) || grid.getCells()[row-1][col].getContent().equals(Content.BORDER) || grid.getCells()[row-1][col].getContent().equals(Content.TAIL))
            {
                this.avoiding = true;
                if(grid.getCells()[row][col-1].getContent().equals(Content.OBSTACLE) || grid.getCells()[row][col-1].getContent().equals(Content.BORDER) || grid.getCells()[row][col-1].getContent().equals(Content.TAIL))
                {
                    snake.rotate(0);
                }
                else
                    snake.rotate(180);
            }
            else
                this.avoiding = false;

        }
        else
        {
            if(grid.getCells()[row+1][col].getContent().equals(Content.OBSTACLE) || grid.getCells()[row+1][col].getContent().equals(Content.BORDER) || grid.getCells()[row+1][col].getContent().equals(Content.TAIL))
            {
                this.avoiding = true;
                if(grid.getCells()[row][col-1].getContent().equals(Content.OBSTACLE) || grid.getCells()[row][col-1].getContent().equals(Content.BORDER) || grid.getCells()[row][col-1].getContent().equals(Content.TAIL))
                {
                    snake.rotate(0);
                }
                else
                    snake.rotate(180);
            }
            else
                this.avoiding = false;

        }

    }
}
