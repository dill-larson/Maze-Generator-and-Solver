import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    ArrayList<Pair<Integer, Integer>> path = new ArrayList<Pair<Integer, Integer>>();
    Maze maze;
    public void DFS(Maze maze){
        Cell start = maze.getEntryPoint();
        Cell currentCell= start;
        Cell end = maze.getExitPoint();
        Stack<Cell> cellStack = new Stack<Cell>();
        int index = 0;
        this.maze = maze;
        cellStack.push(start);
        start.markAsVisited();

        while (!cellStack.isEmpty()){
            currentCell = cellStack.pop();
            path.add(new Pair(currentCell.getX(),currentCell.getY() ));
            for (Cell neighbor: maze.getEdges(currentCell)){
                if(neighbor== end)break;
                if(!neighbor.visited()){
                    cellStack.push(neighbor);
                    neighbor.markAsVisited();
                }
            }
        }
        path.add(new Pair(end.getY(),end.getX() ));
    }
    public void printDFS(){
        Cell[][] grid = maze.getGrid();
        int size = grid.length;
        char index = 48;
        char[][] charMaze = new char[size*2+1][size*2+1];

        int offSetX = 1;
        int offSetY = 1;
        for(int row = 0; row < size; ++row) {
            offSetX = 1;
            for(int col = 0; col < size; ++col) {
                Cell currentCell = grid[row][col]; //gets cell at (x,y)
                //North Wall
                charMaze[row+offSetY-1][col+offSetX] = (currentCell.getNorthWall()) ? '-' : ' ';
                //South Wall
                charMaze[row+offSetY+1][col+offSetX] = (currentCell.getSouthWall()) ? '-' : ' ';
                //East Wall
                charMaze[row+offSetY][col+offSetX+1] = (currentCell.getEastWall()) ? '|' : ' ';
                //West Wall
                charMaze[row+offSetY][col+offSetX-1] = (currentCell.getWestWall()) ? '|' : ' ';
                offSetX++;
            }
            offSetY++;
        }


        String result = "";
        for(int row = 0; row < charMaze.length; ++row) {
            for(int col = 0; col < charMaze.length; ++col) {
                if(row % 2 == 0 && col % 2 == 0) {
                    charMaze[row][col] = '+';
                }
                else if(row % 2 != 0 && col % 2 != 0) {
                    charMaze[row][col] = ' ';
                }
                result += charMaze[row][col];
            }
            result += "\n";
        }
        for (Pair p: path) {
            int x = (int) p.getKey();
            int y = (int) p.getValue();
            System.out.println("(" + x + "," + y +")");
            charMaze[x][y] = index++;
        }

        System.out.println(result);
    }
}
