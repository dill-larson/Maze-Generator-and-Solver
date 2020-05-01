package cs146s20.larson.project03;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private boolean northWall, southWall, eastWall, westWall;
    private int x, y;

    public Cell(int x, int y){
        northWall = true;
        southWall= true;
        eastWall= true;
        westWall= true;
        this.x = x;
        this.y = y;
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
