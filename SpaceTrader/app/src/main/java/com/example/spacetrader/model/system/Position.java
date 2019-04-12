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
    /**
     * Gets the X-coordinate of a position
     * @return the X-coordinate of a position
     */
    public int getX () {return x;}

    /**
     * Sets the X-coordinate of a position
     * @param x the X-coordinate to set a position to
     */
    public void setX(int x) {this.x = x;}

    /**
     * Gets the Y-coordinate of a position
     * @return the Y-coordinate of a position
     */
    public int getY() {return y;}

    /**
     * Sets the Y-coordinate of a position
     * @param y the Y-coordinate to set a position to
     */
    public void setY(int y) {this.y = y;}

    /**
     * Gives the manhattan distance of x-distance plus y-distance between 2 points
     * @param other the position we measure the distance to
     * @return the manhattan distance of the point to this position
     */
    public int getManhattanDistanceTo(Position other) {
        return Math.abs(x - other.x) + Math.abs((y - other.y));
    }

    /**
     * Gives the rounded euclidean (true) distance between 2 points in 2d space
     * @param other the position we measure the distance to
     * @return the rounded euclidean distance of the point to this position
     */
    public int getEuclideanDistanceTo(Position other) {
        return (int) Math.sqrt(Math.pow((double) (other.getX() - x), 2) +
                Math.pow((double) (other.getY() - y),  2));
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
