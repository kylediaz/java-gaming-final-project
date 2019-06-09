package com.kylediaz.metalgearocelot.util;

public enum Direction {
    /*
    Remember - positive y is down, so this is flipped horizontally
     */
    RIGHT(0),
    UP(270),
    LEFT(180),
    DOWN(90),
    UPRIGHT(315),
    UPLEFT(225),
    DOWNLEFT(135),
    DOWNRIGHT(45);
    int angle;
    Direction(int angle) {
        this.angle = angle;
    }
    public int getAngle() {
        return angle;
    }
    public boolean isCardinal() {
        return angle % 90 == 0;
    }
    public boolean isDiagonal() {
        return angle % 90 != 0 && angle % 45 == 0;
    }
}
