package com.example.spacetrader.model.system;

/**
 * Holds the x, y position of a planet in space
 */
public class Position {
    private int x;
    private int y;

    /**
     * Initializes a position
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX () {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    /**
     * Gives the manhattan distance of x-distance plus y-distance between 2 points
     * @param other the position we measure the distance to
     * @return the the manhattan distance of the point to this position
     */
    public int getManhattanDistanceTo(Position other) {
        return Math.abs(x - other.x) + Math.abs((y - other.y));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (!(other instanceof Position)) {
            return false;
        } else {
            Position otherPos = (Position) other;
            return (x == otherPos.getX() && y == otherPos.getY());
        }
    }
}
