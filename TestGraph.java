import junit.framework.TestCase;

public class TestGraph extends TestCase {
    public void testGraph() {
        Maze maze = new Maze(6);
        MazeSolver ms = new MazeSolver();


        // print the graph
        System.out.println("Maze:\n" + maze.printMaze());
        //System.out.println("Graph:\n" + maze);
        ms.DFS(maze);
        ms.printDFS();
    }
}
