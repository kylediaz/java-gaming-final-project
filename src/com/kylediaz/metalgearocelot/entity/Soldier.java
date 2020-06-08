package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.directional.DirectionalAnimation;
import com.kylediaz.metalgearocelot.util.Direction;
import com.kylediaz.metalgearocelot.util.geom.Rectangle;

import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Soldier extends Character {

    public Soldier(Rectangle bounds) {
        super(bounds);
    }

    private Set<Bullet> createdBullets = new HashSet<>();
    @Override
    public void updateEntities(EntityManager entities) {

        createdBullets.clear();
    }

    public class Shoot extends Event {

        private DirectionalAnimation animation;

        public Shoot(DirectionalAnimation animation, Bullet... bullets) {
            animation.reset();
            this.animation = animation;
            createdBullets.addAll(Arrays.asList(bullets));
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
