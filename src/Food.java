
import FigurasGeo.*;

import java.util.Random;

public interface Food {

    void consumir(Grid grid);

    default Cell pickSpawnPoint(Grid grid){
        Random rand = new Random();
        boolean selected = false;
        Cell[][] cells = grid.getCells();
        Cell c = null;
        if(cellAvaiable(grid))
        {
            while(!selected){
                int row = rand.nextInt(grid.getHeight()/grid.getSquaresize());
                int column = rand.nextInt(grid.getWidth()/grid.getSquaresize());
                if (cells[row][column].isEmpty()) {
                    c = cells[row][column];
                    selected = true;
                }
            }
        }
        return c;
    }

    default boolean cellAvaiable(Grid grid){
        boolean check = false;
        Cell[][] cells = grid.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isEmpty()) {
                    check = true;
                }
            }
        }
        return check;
    }

    void update();
    void spawn(Cell c);
}

