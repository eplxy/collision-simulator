package edu.vanier.collisionsimulator.controllers;

public class CustomVector {

    public double[] direction;
    public double conventionalAngle;
    public double x, destX, y, destY, length;

    /**
     * This is a default constructor which will take a Cartesian coordinate.
     *
     * @param x X coordinate of a point on a Cartesian system.
     * @param y Y coordinate of a point on a Cartesian system.
     */
    public CustomVector(double x, double y) {
        this.x = x;
        this.y = y;
        this.length = this.computeLength();
        this.direction = this.computeDirection();
    }

    public CustomVector(boolean isNormAndAngle, double magnitude, double angle) {
        if (isNormAndAngle) {
            this.length = magnitude;
            this.direction = parseDirectionAngle(angle);
            double[] components = this.computeComponents();
            this.x = components[0];
            this.y = components[1];
        }

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
    private double quadrant() {
        int q = 0;
        if (x > 0 && y > 0) {
            q = 4;
        } else if (x < 0 && y > 0) {
            q = 3;
        } else if (x < 0 && y < 0) {
            q = 2;
        } else if (x > 0 && y < 0) {
            q = 1;
        }
        return q;
    }

    private void quadrantAngleModification() {
        int q = (int) this.direction[1];
        double a = this.direction[0];
        switch (q) {
            case 1 ->
                this.conventionalAngle = a;
            case 2 ->
                this.conventionalAngle = 180 - a;
            case 3 ->
                this.conventionalAngle = 180 + a;
            case 4 ->
                this.conventionalAngle = 360 - a;
        }
    }

    private double[] quadrantComponentModification(double x, double y) {
        int q = (int) this.direction[1];
        switch (q) {
            case 1 ->
                y *= -1;
            case 2 -> {
                x *= -1;
                y *= -1;
            }
            case 3 ->
                x *= -1;
            case 4 -> {
            }
        }
        return new double[]{x, y};
    }

    /**
     * method to use with predefined vector components
     *
     * @return double array, value 1 is angle, value 2 is quadrant
     */
    public double[] computeDirection() {

        double[] a = new double[]{Math.toDegrees(Math.atan2(Math.abs(y), Math.abs(x))), this.quadrant()};

        return a;
    }

    /**
     * method to use with undefined vector components parse a double value
     * between -360 and 360
     *
     * @return double array, value 1 is angle, value 2 is quadrant
     */
    private double[] parseDirectionAngle(double angle) {
        int q = 0;

        //positive angle
        if (angle >= 0 && angle <= 90) {
            return new double[]{angle, 1};
        }

        if (angle > 90 && angle <= 180) {
            return new double[]{180 - angle, 2};
        }

        if (angle > 180 && angle <= 270) {
            return new double[]{angle - 180, 3};
        }

        if (angle > 270 && angle <= 360) {
            return new double[]{360 - angle, 4};
        }

        //negative angle
        if (angle < 0 && angle >= -90) {
            return new double[]{Math.abs(angle), 4};
        }

        if (angle < -90 && angle >= -180) {
            return new double[]{180 + angle, 3};
        }

        if (angle < -180 && angle >= -270) {
            return new double[]{Math.abs(angle + 180), 2};
        }

        if (angle < -270 && angle >= -360) {
            return new double[]{360 + angle, 1};
        }

        return null;
    }

    private double[] computeComponents() {
        double xTemp = this.length * Math.cos(Math.toRadians(this.direction[0]));
        double yTemp = this.length * Math.sin(Math.toRadians(this.direction[0]));

        return quadrantComponentModification(xTemp, yTemp);
    }

    //for debugging purposes
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public double computeLength() {
        return (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }

    public CustomVector normalize() {

        this.length = this.computeLength();
        if (this.length == 0) {
            return new CustomVector(0, 0);
        }

        return new CustomVector(this.x / this.length, this.y / this.length);
    }

    public static double dotProduct(CustomVector a, CustomVector b) {
        return (a.x * b.x + a.y * b.y);
    }

    public CustomVector scalarMult(double scalar) {
        return new CustomVector(this.x * scalar, this.y * scalar);
    }

    public double getAngle() {
        this.direction = this.computeDirection();
        this.quadrantAngleModification();
        return conventionalAngle;
    }

}
