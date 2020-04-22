package cs146s20.larson.project03;

public class Maze {
    private Graph<Cell> maze;
    private int size;

    public Maze(int size) {
        maze = new Graph<>();
        this.size = size;
        createEmptyMaze();
    }

    public Maze() {
        this(0);
    }

    public String printMaze() {
        String result = maze.toString();

        return result;
    }

    private void createEmptyMaze() {
        int spacing = (size > 3) ? (int)Math.floor(size/2.0) : size % 2;
        int idk = size - 1;

        for(int i = 0; i < size*size; ++i) { //rows
            for(int j = i + 1; j < size*size; j += spacing + 1) { //columns
                if(j <= i + size) {
                    if ((i == idk) && (j % size == 0)) {
                        idk += size;
                        continue;
                    } else {
                        maze.addEdge(new Cell(i), new Cell(j), true);
                    }
                }
            }
        }
    }
}
