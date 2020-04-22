package cs146s20.larson.project03;

public class Cell {
    //Boolean values to represent walls--true = wall; false = no wall
    private boolean north, south, east, west;
    private int id = 0;

    public Cell(int id) {
        north = true;
        south = true;
        east = true;
        west = true;
        this.id = id;
    }

    //Getters
    public boolean getNorth() {
        return north;
    }
    public boolean getSouth() {
        return south;
    }
    public boolean getEast() {
        return east;
    }
    public boolean getWest() {
        return west;
    }
    public int getId() {
        return id;
    }

    //Setters
    public void setNorth(boolean wall) {
        north = wall;
    }
    public void setSouth(boolean wall) {
        south = wall;
    }
    public void setEast(boolean wall) {
        east = wall;
    }
    public void setWest(boolean wall) {
        west = wall;
    }

    //Methods
    @Override
    public String toString() {
        return "cell-" + id;
    }
    @Override
    public int hashCode() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        return ((Cell)o).getId() == this.getId();
    }
}
