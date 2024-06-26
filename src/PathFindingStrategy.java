import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Classe que implementa o modo automático que procura um Path até à comida da Snake
 * @author [Diogo Almeida 79810, André Guerreiro 79809, Vasco Apolinário 79944]
 * @version 1.0 09/05/2024
 */
public class PathFindingStrategy implements AutoSnake {
    private Path snakepath;
    private boolean searching;
    private boolean avoiding;
    private int count;
    private long timer;

    /**
     * Construtor da classe PathFindingStrategy
     */
    public PathFindingStrategy() {
        this.searching = true;
        this.avoiding = false;
        this.timer = System.currentTimeMillis();
        this.count = 1;
    }

    /**
     * Metodo que é chamado a cada instante de jogo e implementa a lógica da automatização da snake
     * @param snake cobra do jogo
     * @param background background onde está a ocorrer a ação do jogo
     */
    public void update(Snake snake, Background background){
        avoidObstacle(snake,background.getGrid());
        if(System.currentTimeMillis() - timer > 10000){
            snakepath =null;
            searching = true;
        }
        if (searching) {
            this.snakepath = Search(background,snake);
            this.searching = false;
            this.timer = System.currentTimeMillis();

        } else {

            if (snakepath != null && !avoiding) {
                snake.setStatus(Status.ALIVE);
                followPath(snake, this.snakepath, background);
            } else {
                this.searching = true;
            }

        }


    }


    /**
     * Método que devolve, se possível, um caminho da cobra até a uma comida aleatória seguro
     * @param bg background onde está a ocorrer a ação do jogo
     * @param snake cobra do jogo
     * @return Path que da cabeça da cobra até à comida
     */
    public Path Search(Background bg, Snake snake){
        Path path = null;
        Random random = new Random();
        Food food = bg.getComida().get(random.nextInt(bg.getComida().size()));
        //Food food = getCloserFood(bg,snake);
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


    /**
     * Metodo que verifica se um caminho não interseta com obstáculos ou cauda da cobra
     * @param p Path a ser verificado
     * @param bg background onde está a ocorrer a ação do jogo
     * @return true se não intersetar, false se intersetar
     */
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

    /**
     * Metodo que faz com que a snake siga um Path
     * @param snake cobra do jogo
     * @param path Path a ser seguido
     * @param background background onde está a ocorrer a ação do jogo
     */
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
            if (path.getPontos()[count].getY() < snake.getHead().getCentroide().getY() && !background.getGrid().getCells()[row-1][col].isDangerous()) // se o y do 2º ponto está em cima da snake
            {
                snake.rotate(90);
            } else if (path.getPontos()[count].getY() > snake.getHead().getCentroide().getY() && !background.getGrid().getCells()[row+1][col].isDangerous()) // se o y do 2º ponto está abaixo da snake
            {
                snake.rotate(270);
            }
        }
        else
        {
            if(path.getPontos()[count].getX() < snake.getHead().getCentroide().getX() && !background.getGrid().getCells()[row][col-1].isDangerous()) // se o x do 2º ponto está à esquerda da snake
            {
                snake.rotate(180);
            }
            else if(path.getPontos()[count].getX() > snake.getHead().getCentroide().getX() && !background.getGrid().getCells()[row][col+1].isDangerous()) // se o x do 2º ponto está à direita da snake
            {
                snake.rotate(0);
            }
        }
    }

    /**
     * Metodo quer faz com que a snake desvie-se de obstáculos, da borda e da sua própria cauda.
     * @param snake cobra do jogo
     * @param grid grid onde está a ocorrer a ação do jogo
     */
    @Override
    public void avoidObstacle(Snake snake,Grid grid){
        int row = grid.returnRowFromPoint(snake.getHead().getCentroide());
        int col = grid.returnColFromPoint(snake.getHead().getCentroide());
        if(snake.getDirection() == 0)
        {
            if(grid.getCells()[row][col+1].isDangerous())
            {
                this.avoiding = true;
                if(grid.getCells()[row-1][col].isDangerous())
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
            if(grid.getCells()[row][col-1].isDangerous())
            {
                this.avoiding = true;
                if(grid.getCells()[row-1][col].isDangerous())
                {
                    snake.rotate(270);
                }
                else
                    snake.rotate(90);
            }
            else
                this.avoiding = false;

        } else if (snake.getDirection() == 90) {
            if(grid.getCells()[row-1][col].isDangerous())
            {
                this.avoiding = true;
                if(grid.getCells()[row][col-1].isDangerous())
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
            if(grid.getCells()[row+1][col].isDangerous())
            {
                this.avoiding = true;
                if(grid.getCells()[row][col-1].isDangerous())
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
