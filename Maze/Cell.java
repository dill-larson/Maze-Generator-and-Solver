package Maze;

import java.util.Random;

public class Cell {
    private boolean northWall;
    private boolean southWall;
    private boolean eastWall;
    private boolean westWall;
    public int index;
    public String edgeWall;
    public Cell(){
        northWall = true;
        southWall= true;
        eastWall= true;
        westWall= true;
    }

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
    public String getRandomWall(){
        String[] walls = {"north","south","east","west"};
        Random rg = new Random();
        return walls[rg.nextInt(4)];
    }
    public boolean wallsIntact(){
        if (northWall == true && eastWall == true && southWall == true && westWall==true){
            return true;
        }
        return false;
    }



}
