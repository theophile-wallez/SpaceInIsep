package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Enemy60Level3 extends Enemy {
    public boolean touchDown;
    public boolean touchUp;

    public Enemy60Level3(int xEnemy, int yEnemy, int w, int h, Color color, int health, boolean isHit) {
        super(xEnemy, yEnemy, w, h, color, health, isHit);
    }

    private final Image enemy60_Level3_60Image = new Image("file:src/resources/images/enemy/level1/enemy60/enemy60.png");
    private final Image enemy60_Level3_40Image = new Image("file:src/resources/images/enemy/level1/enemy60/enemy40.png");
    private final Image enemy60_Level3_20Image = new Image("file:src/resources/images/enemy/level1/enemy60/enemy20.png");

    public void update() {
        for (var enemy : Level.enemy60Level3List) {
            if(enemy.health<=60) {
                enemy.setFill(new ImagePattern(enemy60_Level3_60Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=40) {
                enemy.setFill(new ImagePattern(enemy60_Level3_40Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=20) {
                enemy.setFill(new ImagePattern(enemy60_Level3_20Image, 0, 0, 1, 1, true));
            }

        }

        if (super.getTranslateY() >= 570)
        {
            touchDown = true;
            touchUp = false;
        }
        else if (super.getTranslateY() <= 240)
        {
            touchDown = false;
            touchUp = true;
        }

        moveEnemyLevel3Enemy60(touchDown);
        mayShoot();
    }
}
