package cs146s20.larson.project03;

import junit.framework.TestCase;

public class TestGraph extends TestCase {
    public void testGraph() {
        Maze maze = new Maze(2);

        // print the graph
        System.out.println("Maze.Maze:\n" + maze.printMaze());
    }
}
