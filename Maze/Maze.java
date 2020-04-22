package Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
    private Graph<Cell> maze;
    private int size;
    private Cell[][] cellSpace;

    public Maze(int size) {
        cellSpace = new Cell[size][size];
        maze = new Graph<Cell>();
        this.size = size;
        Random rg = new Random();

        // create cells in space
        for (int i = 0; i<size; i++){
            for (int j = 0; j< size; j++){
                cellSpace[i][j]= new Cell();
                maze.addVertex(cellSpace[i][j]);
            }
        }
        // create cell stack
        Stack<Cell> cellStack = new Stack<Cell>();
        int totalCells = size*size;
        int currentRow = 0;
        int currentColumn = 0;
        Cell currentCell = cellSpace[currentRow][currentColumn];

        int visitedCells = 1;
        int index = -1;
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        while (visitedCells< totalCells){
            //find neighbors with intact walls
            if (currentColumn != size-1 & cellSpace[currentRow][currentColumn++].wallsIntact()){
                cellSpace[currentRow][currentColumn++].edgeWall = "east";
                neighbors.add(cellSpace[currentRow][currentColumn++]);
            }
            if (currentColumn != 0 && cellSpace[currentRow][currentColumn--].wallsIntact()){
                cellSpace[currentRow][currentColumn--].edgeWall = "west";
                neighbors.add(cellSpace[currentRow][currentColumn--]);
            }
            if (currentRow != size-1 & cellSpace[currentRow++][currentColumn].wallsIntact()){
                cellSpace[currentRow++][currentColumn].edgeWall = "south";
                neighbors.add(cellSpace[currentRow++][currentColumn]);
            }
            if (currentRow != 0 && cellSpace[currentRow--][currentColumn].wallsIntact()){
                cellSpace[currentRow][currentColumn--].edgeWall = "north";
                neighbors.add(cellSpace[currentRow--][currentColumn]);
            }
            // if one or more found, choose one at random
            Cell neighbor;
            if(size>1){
                neighbor = chooseRandomRoom(neighbors);
                // knock down wall between neighbor and currentCell
                if (neighbor.edgeWall.equals("east")){
                    neighbor.setEastWall(false);
                    maze.addEdge(currentCell,neighbor,true);
                }else if (neighbor.edgeWall.equals("west")){
                    neighbor.setWestWall(false);
                    maze.addEdge(currentCell,neighbor,true);
                }else if (neighbor.edgeWall.equals("north")){
                    neighbor.setNorthWall(false);
                    maze.addEdge(currentCell,neighbor,true);
                }
                else {
                    neighbor.setSouthWall(false);
                    maze.addEdge(currentCell,neighbor,true);
                }

            }else {
                neighbor = neighbors.get(0);
            }



        }

        //int spacing = (int)Math.floor(size/2);
        //int idk = size - 1;

//        for(int i = 0; i < size*size; ++i) { //rows
//            for(int j = i + 1; j < size*size; j += spacing + 1) { //columns
//                if(j <= i + size) {
//                    if ((i == idk) && (j % size == 0)) {
//                        idk += size;
//                        continue;
//                    } else {
//                        maze.addEdge(i, j, true);
//                    }
//                }
//            }
//        }
    }

    public Maze() {
        this(0);
    }
    public Cell chooseRandomRoom(ArrayList<Cell> neighbors){
       Random rg = new Random();
       return neighbors.get(rg.nextInt(neighbors.size()));
    }

    public String printMaze() {
        String result = maze.toString();


        return result;
    }
}
