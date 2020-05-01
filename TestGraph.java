package cs146s20.larson.project03;

import junit.framework.TestCase;

public class TestGraph extends TestCase {
    public void testGraph() {
        Maze maze = new Maze(4);

        // print the graph
        System.out.println("Maze:\n" + maze.printMaze());
        System.out.println("Graph:\n" + maze);
    }
}
