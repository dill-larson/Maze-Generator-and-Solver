package cs146s20.larson.project03;

public class Maze {
    private Graph<Integer> maze;
    private int size;

    public Maze(int size) {
        maze = new Graph<>();
        this.size = size;
        int spacing = size % 2;
        for(int i = 0; i < size*size; ++i) { //rows
            for(int j = i; j < i + size; ++j) { //columns
                if((i == size - 1) && ((j / (size - 1)) != 1)) {
                    maze.addEdge(i, j + 1, true);
                }
                j += spacing;
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
