package Maze;

public class Maze {
    private Graph<Integer> maze;
    private int size;

    public Maze(int size) {
        maze = new Graph<Integer>();
        this.size = size;
        int spacing = (int)Math.floor(size/2);
        int idk = size - 1;

        for(int i = 0; i < size*size; ++i) { //rows
            for(int j = i + 1; j < size*size; j += spacing + 1) { //columns
                if(j <= i + size) {
                    if ((i == idk) && (j % size == 0)) {
                        idk += size;
                        continue;
                    } else {
                        maze.addEdge(i, j, true);
                    }
                }
            }
        }
    }

    public Maze() {
        this(0);
    }

    public String printMaze() {
        String result = maze.toString();



        return result;
    }
}
