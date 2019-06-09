package com.kylediaz.metalgearocelot.util;

public class Vector {

    private final double xComponent, yComponent;

    public Vector() {
        xComponent = 0;
        yComponent = 0;
    }

    public static final Vector ZERO = new Vector(0, 0);
    /*
     * constructor is made private to force client to specify what kind of coordinate system they are using
     */
    private Vector(double xComponent, double yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
    }
    public static Vector polar(double magnitude, double degrees) {
        if (magnitude == 0) return ZERO;
        degrees %= 360;
        return new Vector(magnitude * Math.cos(degrees), magnitude * Math.sin(degrees));
    }
    public static Vector rectangular(double xComponent, double yComponent) {
        if (xComponent == 0 && yComponent == 0) return ZERO;
        return new Vector(xComponent, yComponent);
    }

    public double getXComponent() {
        return xComponent;
    }
    public double getYComponent() {
        return yComponent;
    }

    public Vector add(Vector vector) {
        return rectangular(this.xComponent + vector.xComponent, this.yComponent + vector.yComponent);
    }
    public Vector subtract(Vector vector) {
        return rectangular(this.xComponent - vector.xComponent, this.yComponent - vector.yComponent);
    }
    public Direction getDirection() throws Exception {
        for (Direction d : Direction.values()) {
            if (Math.abs(angle() - d.getAngle()) < 1)
                return d;
        }
        throw new Exception("Not a direction");
    }
    public boolean isInDirection(Direction direction) {
        return Math.abs(angle() - direction.getAngle()) < 1;
    }
    public double angle() {
        double angle = Math.toDegrees(Math.atan2(yComponent, xComponent));
        if (angle < 0) angle += 360;
        return angle;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", xComponent, yComponent);
    }
}
