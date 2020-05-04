

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private boolean northWall, southWall, eastWall, westWall, visited;
    private int x, y, foundNdex;

    public Cell(int x, int y){
        northWall = true;
        southWall= true;
        eastWall= true;
        westWall= true;
        this.x = x;
        this.y = y;
        visited = false;
        foundNdex = -1;
    }

    //Getters
    public boolean getNorthWall() {
        return northWall;
    }
    public boolean getSouthWall() {
        return southWall;
    }
    public boolean getEastWall() {
        return eastWall;
    }
    public boolean getWestWall() {
        return westWall;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean visited() {
        return visited;
    }
    public int getFoundNdex(){
        return foundNdex;
    }

    //Setters
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }
    public void markAsVisited() { visited = true;}
    public void setFoundNdex(int n){foundNdex = n%10;}


    //Methods
    public boolean isIntact() {
        return northWall && southWall && eastWall && westWall;
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    @Override
    public boolean equals(Object o) {
        Cell otherCell = (Cell) o;
        return otherCell != null && otherCell.getX() == this.getX() && otherCell.getY() == this.getY();
    }
}
