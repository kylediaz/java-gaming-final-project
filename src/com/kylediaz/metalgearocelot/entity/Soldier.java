package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.directional.DirectionalAnimation;
import com.kylediaz.metalgearocelot.util.Direction;

import java.awt.geom.Rectangle2D;

public class Soldier extends Character {

    public Soldier(Rectangle2D.Double bounds) {
        super(bounds);
    }

    public class Shoot extends Event {

        private DirectionalAnimation animation;

        public Shoot(DirectionalAnimation animation) {
            animation.reset();
            this.animation = animation;
        }

        @Override
        public void tick(double deltaTime) {
            super.tick(deltaTime);
            Direction direction = getVelocity().getDirection();
            if (direction != null)
                animation.setCurrentDirection(direction);
        }

        @Override
        public void periodic(double deltaTime) {

        }

        @Override
        public DirectionalAnimation getAnimation() {
            return animation;
        }
    }


}
