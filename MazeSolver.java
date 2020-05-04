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

        while (!cellStack.isEmpty() ){
            currentCell = cellStack.pop();
            if(currentCell== end)break;
            path.add(new Pair(currentCell.getX(),currentCell.getY() ));
            for (Cell neighbor: maze.getEdges(currentCell)){
                if(!neighbor.visited()){
                    cellStack.push(neighbor);
                    neighbor.markAsVisited();
                }
                if(neighbor== end)break;
            }
        }
        path.add(new Pair(end.getY(),end.getX() ));
    }
    public void printDFS(){
        Cell[][] grid = maze.getGrid();
        int size = grid.length;
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
        int ndx = 0;
        for (Pair p: path){
            int x = (int) p.getKey();
            int y = (int) p.getValue();
            grid[y][x].setFoundNdex(ndx++);
            //System.out.println("(" + x + "," + y + ")");
            //System.out.println(grid[x][y].getFoundNdex());
        }

        String result = "";

        offSetY = 0;
        for(int row = 0; row < charMaze.length; ++row) {
            offSetX = 0;
            for(int col = 0; col < charMaze.length; ++col) {
                if(row % 2 == 0 && col % 2 == 0) {
                    charMaze[row][col] = '+';
                }
                else if(row % 2 != 0 && col % 2 != 0) {
                    if(grid[offSetY][offSetX].getFoundNdex()>=0){
                        charMaze[row][col] = (char) (grid[offSetY][offSetX].getFoundNdex() + 48) ;

                    }else {
                        charMaze[row][col] = ' ';
                    }
                    offSetX++;
                }

                result += charMaze[row][col];
            }
            if(row % 2 != 0) { offSetY++;}
            result += "\n";
        }
        System.out.println(result);
    }
}
