package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.MovementAnimation;
import com.kylediaz.metalgearocelot.util.geom.Rectangle;

public class Character extends PhysicalEntity {


    public Character(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Character(Rectangle bounds) {
        super(bounds);
    }

    public class Move extends Event {

        private MovementAnimation animation;

        public Move(MovementAnimation movementAnimation) {
            this.animation = movementAnimation;
        }

        @Override
        public void tick(double deltaTime) {
            super.tick(deltaTime);
            animation.setVelocity(getVelocity());
        }

        @Override
        public void periodic(double deltaTime) {
        }

        @Override
        public void end() {
            super.end();
        }

        @Override
        public MovementAnimation getAnimation() {
            return animation;
        }

    }
}
