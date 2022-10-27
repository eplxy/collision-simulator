package edu.vanier.collisionsimulator.controllers;

public class CustomVector {

    //TODO:find angles
    
    
    public double x,destX,y,destY;
    /**
     * This is a default constructor which will take a Cartesian coordinate.
     *
     * @param x X coordinate of a point on a Cartesian system.
     * @param y Y coordinate of a point on a Cartesian system.
     */
    public CustomVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CustomVector(double destX, double destY, double objX, double objY) {
        this.x = deltaX(destX, objX);
        this.y = deltaY(destY, objY);
        this.destX = destX;
        this.destY = destY;
    }

    /**
     * Returns a Cartesian coordinate system's quadrant from 1 to 4.
     * <pre>
     *     first quadrant - 1 upper right
     *     second quadrant - 2 upper left
     *     third quadrant - 3 lower left
     *     fourth quadrant - 4 lower right
     * </pre>
     *
     * @return int quadrant number 1 through 4
     */
    public int quadrant() {
        int q = 0;
        if (x > 0 && y > 0) {
            q = 1;
        } else if (x < 0 && y > 0) {
            q = 2;
        } else if (x < 0 && y < 0) {
            q = 3;
        } else if (x > 0 && y < 0) {
            q = 4;
        }
        return q;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ") quadrant=" + quadrant();
    }

    /**
     * Converts point's X screen coordinate into a Cartesian system.
     *
     * @param mouseX Converts the mouse X coordinate into Cartesian system based
     * on the ship center X (originX).
     * @param originX The ship center point's X coordinate.
     * @return double value of a Cartesian system X coordinate based on the
     * origin X.
     */
    static double deltaX(double mouseX, double originX) {
        return mouseX - originX;
    }

    /**
     * Converts point's Y screen coordinate into a Cartesian system.
     *
     * @param mouseY Converts the mouse Y coordinate into Cartesian system based
     * on the ship center Y (originY).
     * @param originY The ship center point's Y coordinate.
     * @return double value of a Cartesian system Y coordinate based on the
     * origin Y.
     */
    static double deltaY(double mouseY, double originY) {
        return originY - mouseY;
    }

}
