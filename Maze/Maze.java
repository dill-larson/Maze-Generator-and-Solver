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
        int index = 0;

        // create cells in space
        for (int i = 0; i<size; i++){
            for (int j = 0; j< size; j++){
                cellSpace[i][j]= new Cell();
                cellSpace[i][j].setIndex(index++);
                maze.addVertex(cellSpace[i][j]);
            }
        }
        // create cell stack
        Stack<Cell> cellStack = new Stack<Cell>();
        int totalCells = size*size;
        int currentRow = 0;
        int currentColumn = 0;
        Cell currentCell = cellSpace[currentRow][currentColumn];
        currentCell.setNorthWall(false);
        //cellSpace[size-1][size-1].setSouthWall(false);
        int visitedCells = 1;

        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        Cell endCell = cellSpace[size-1][size-1];
        while (visitedCells< totalCells ){
            //find neighbors with intact walls

            neighbors.clear();
            if (currentColumn != size-1 ){
                if (cellSpace[currentRow][currentColumn+1].wallsIntact()){
                    cellSpace[currentRow][currentColumn+1].edgeWall = "east";
                    neighbors.add(cellSpace[currentRow][currentColumn+1]);
                }
            }
            if (currentColumn != 0 ){
                if (cellSpace[currentRow][currentColumn-1].wallsIntact()){
                    cellSpace[currentRow][currentColumn-1].edgeWall = "west";
                    neighbors.add(cellSpace[currentRow][currentColumn-1]);
                }
            }
            if (currentRow != size-1 ){
                if (cellSpace[currentRow+1][currentColumn].wallsIntact()){
                    cellSpace[currentRow+1][currentColumn].edgeWall = "south";
                    neighbors.add(cellSpace[currentRow+1][currentColumn]);
                }

            }
            if (currentRow != 0 ){
                if (cellSpace[currentRow-1][currentColumn].wallsIntact()){
                    cellSpace[currentRow-1][currentColumn].edgeWall = "north";
                    neighbors.add(cellSpace[currentRow-1][currentColumn]);
                }

            }
            // if one or more found, choose one at random
            Cell neighbor;
            if(!currentCell.equals(endCell) && neighbors.size()>=1  ){
                neighbor = chooseRandomRoom(neighbors);
                // knock down wall between neighbor and currentCell
                if (neighbor.edgeWall.equals("east")){
                    neighbor.setWestWall(false);
                    currentCell.setEastWall(false);

                    maze.addEdge(currentCell,neighbor,true);
                    currentColumn++;
                }else if (neighbor.edgeWall.equals("west")){
                    neighbor.setEastWall(false);
                    currentCell.setWestWall(false);

                    maze.addEdge(currentCell,neighbor,true);
                    currentColumn--;
                }else if (neighbor.edgeWall.equals("north")){
                    neighbor.setSouthWall(false);
                    currentCell.setNorthWall(false);

                    maze.addEdge(currentCell,neighbor,true);
                    currentRow--;
                }
                else {
                    neighbor.setNorthWall(false);
                    currentCell.setSouthWall(false);

                    maze.addEdge(currentCell,neighbor,true);
                    currentRow++;
                }
                cellStack.push(currentCell);
                currentCell = neighbor;
                visitedCells++;

            }else {
                if (!cellStack.isEmpty())
                    currentCell = cellStack.pop();
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
        for (int i = 0; i< size; i++){
            for (int j=0; j< size; j++){
                if (cellSpace[i][j].getSouthWall()){
                    System.out.print("_");
                }
                System.out.print(cellSpace[i][j].index);
                if (cellSpace[i][j].getEastWall()){
                    System.out.print("|");
                }


            }
            System.out.println("\n");
        }
        return result;
    }
}
