public interface IGraficos {

    void draw();
    class Grafica implements IGraficos{
        public void draw() {
        }
    }

    class Textual implements IGraficos{
        public void draw() {
        }
    }


}
/*

"H" -> H's

if format.equals("textual")

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
. . . . . . . . ... . . . . . . . . . . . . . . . . . . . . . . . .
T T T T T T T T H H H . . . F F . . . . . . . . . . . . . . . . . .
T T T T T T T T H H H . . . F F . . . . . . . . . . . . . . . . . .
T T T T T T T T H H H . . . . . . . . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . O O O O O . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . O O O O O . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . O O O O O . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . O O O O O . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
 */