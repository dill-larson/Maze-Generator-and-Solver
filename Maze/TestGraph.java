package Maze;

//import junit.framework.TestCase;

import junit.framework.TestCase;

public class TestGraph extends TestCase {
    public void testGraph() {
        Maze maze = new Maze(4);

        // print the graph
        System.out.println("Maze.Maze:\n" + maze.printMaze());
    }
}
