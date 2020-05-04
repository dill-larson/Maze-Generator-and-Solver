
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

public class Maze extends Graph<Cell> {
    private Pair<Integer, Integer> entryPoint;
    private Pair<Integer, Integer> existPoint;
    private int size;
    private Cell[][] grid;
    private Random rng = new Random(3);

    public Maze(int size) {
        this.size = size;
        grid = new Cell[size][size];
        entryPoint = new Pair<>(0,0);
        existPoint = new Pair<>(size-1, size-1);
        createMaze();
    }

    public Maze() {
        this(0);
    }

    private void createMaze() {
        initGrid();

        //Open entry point
        grid[entryPoint.getKey()][entryPoint.getValue()].setNorthWall(false);

        Stack<Cell> cellStack = new Stack<>();
        int totalCells = size * size;
        int currentX = 0;
        int currentY = 0;
        Cell currentCell = grid[currentX][currentY];
        int visitedCells = 1;

        while(visitedCells < totalCells) {
            //find all neighbors of CurrentCell with all walls intact
            ArrayList<Pair<Cell, Direction>> neighbors = findNeighbors(currentCell);

            if(neighbors.size() >= 1) {
                //choose one at random
                int randomIndex = rng.nextInt(neighbors.size());
                Cell neighbor = neighbors.get(randomIndex).getKey();
                Direction direction = neighbors.get(randomIndex).getValue();

                //knock down the wall between neighbor and CurrentCell
                destroyWall(currentCell, neighbor, direction);

                //push CurrentCell location on the CellStack
                cellStack.push(currentCell);

                //make the new cell CurrentCell
                currentCell = neighbor;
                ++visitedCells;
            }
            else {
                //pop the most recent cell entry off the CellStack and make it CurrentCell
                try {
                    currentCell = cellStack.pop();
                }
                catch(EmptyStackException ese) {
                    System.err.println("EmptyStackException: " + ese.getMessage());
                    break;
                }
            }
        }

        //Open exist point
        grid[existPoint.getKey()][existPoint.getValue()].setSouthWall(false);
    }

    /**
     *
     * @return
     */
    public String printMaze() {
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
        return result;
    }

    /**
     * Initializes grid with Cell objects
     */
    private void initGrid() {
        for(int x = 0; x < size; ++x) {
            for(int y = 0; y < size; ++y) {
                grid[y][x] = new Cell(x, y);
            }
        }
    }

    /**
     * +-----------------------+------------------------+-----------------------+
     * |                       | North Neighbor (x,y-1) |                       |
     * +-----------------------+------------------------+-----------------------+
     * | West Neighbor (x-1,y) |          cell          | East Neighbor (x+1,y) |
     * +-----------------------+------------------------+-----------------------+
     * |                       | South Neighbor (x,y+1) |                       |
     * +-----------------------+------------------------+-----------------------+
     * @param cell
     * @return
     */
    private ArrayList<Pair<Cell, Direction>> findNeighbors(Cell cell) {
        ArrayList<Pair<Cell, Direction>> neighbors = new ArrayList<>();

        int cellX = cell.getX();
        int cellY = cell.getY();

        //North Neighbor
        if(withinBounds(cellY-1) && grid[cellY-1][cellX].isIntact()){
            neighbors.add(new Pair(grid[cellY-1][cellX], Direction.NORTH));
        }
        //South Neighbor
        if(withinBounds(cellY+1) && grid[cellY+1][cellX].isIntact()){
            neighbors.add(new Pair(grid[cellY+1][cellX], Direction.SOUTH));
        }
        //East Neighbor
        if(withinBounds(cellX+1) && grid[cellY][cellX+1].isIntact()){
            neighbors.add(new Pair(grid[cellY][cellX+1], Direction.EAST));
        }
        //West Neighbor
        if(withinBounds(cellX-1) && grid[cellY][cellX-1].isIntact()){
            neighbors.add(new Pair(grid[cellY][cellX-1], Direction.WEST));
        }

        return neighbors;
    }

    private boolean withinBounds(int index) {
        return index >= 0 && index < size;
    }

    private void destroyWall(Cell currentCell, Cell neighbor, Direction direction) {
        switch(direction) {
            case NORTH:
                currentCell.setNorthWall(false);
                neighbor.setSouthWall(false);
                this.addEdge(currentCell, neighbor, true);
                break;
            case SOUTH:
                currentCell.setSouthWall(false);
                neighbor.setNorthWall(false);
                this.addEdge(currentCell, neighbor, true);
                break;
            case EAST:
                currentCell.setEastWall(false);
                neighbor.setWestWall(false);
                this.addEdge(currentCell, neighbor, true);
                break;
            case WEST:
                currentCell.setWestWall(false);
                neighbor.setEastWall(false);
                this.addEdge(currentCell, neighbor, true);
                break;
        }
    }
    public Cell getEntryPoint(){
        return grid[0][0];
    }
    public Cell getExitPoint(){
        return grid[size-1][size-1];
    }
    public Cell[][] getGrid(){
        return grid;
    }
}