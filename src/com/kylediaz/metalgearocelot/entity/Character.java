package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.MovementAnimation;

import java.awt.*;

public class Character extends PhysicalEntity {


    public Character(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Character(Rectangle.Double bounds) {
        super(bounds);
    }

    protected class Move extends Event {

        private MovementAnimation animation;

        public Move(MovementAnimation movementAnimation) {
            this.animation = movementAnimation;
        }

        @Override
        public void periodic() {
            animation.setVelocity(getVelocity());
        }

        @Override
        public void end() {

        }

        @Override
        public MovementAnimation getAnimation() {
            return animation;
        }

    }
}
