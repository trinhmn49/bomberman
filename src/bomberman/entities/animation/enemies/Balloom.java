package bomberman.entities.animation.enemies;

import bomberman.entities.GameScene;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.LowAI;
import javafx.scene.image.Image;

public class Balloom extends Enemy {
    public Balloom(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
        this.speed = 1;
        ai = new LowAI();

        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
        Image[] dieOfBalloom = new Image[1];
        dieOfBalloom[0] = getFxImage("/sprites/balloom_dead.png");
        images[4] = dieOfBalloom;
    }

    public void findTransition(Image[] image, Transition t) {
        if (isLiving) {
            if (t == Transition.UP || t == Transition.RIGHT) {
                image[0] = getFxImage("/sprites/balloom_right1.png");
                image[1] = getFxImage("/sprites/balloom_right2.png");
                image[2] = getFxImage("/sprites/balloom_right3.png");
            } else if (t == Transition.DOWN || t == Transition.LEFT) {
                image[0] = getFxImage("/sprites/balloom_left1.png");
                image[1] = getFxImage("/sprites/balloom_left2.png");
                image[2] = getFxImage("/sprites/balloom_left3.png");
            }
        }
    }
    public Transition moveEnemy() {
        if ((this.getX() % GameScene.SIZE != 0) || (this.getY() % GameScene.SIZE != 0)) {
            return transition;
        }
        return super.moveEnemy();
    }

    @Override
    public boolean onActionCollideEnemy(DynamicObject dynamicObject) {
        return false;
    }
}
