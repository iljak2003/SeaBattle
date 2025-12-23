package ru.vsu.cs.krasnoperov.course2.pi.gr11;

public class Coordinate {
    private int xCor;
    private int yCor;

    public Coordinate(int x, int y){
        xCor = x;
        yCor = y;
    }

    public int getX(){
        return xCor;
    }

    public int getY(){
        return yCor;
    }

    public String toString(){
        return "(" + xCor + ", " + yCor + ")";
    }
}
