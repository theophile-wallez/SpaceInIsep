package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Enemy180Level2 extends Enemy {
    public Enemy180Level2(int xEnemy, int yEnemy, int w, int h, Color color, int health, boolean isHit) {
        super(xEnemy, yEnemy, w, h, color, health, isHit);
    }

    private final Image enemy180_Level2_180Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy180.png");
    private final Image enemy180_Level2_160Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy160.png");
    private final Image enemy180_Level2_140Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy140.png");
    private final Image enemy180_Level2_120Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy120.png");
    private final Image enemy180_Level2_100Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy100.png");
    private final Image enemy180_Level2_80Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy80.png");
    private final Image enemy180_Level2_60Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy60.png");
    private final Image enemy180_Level2_40Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy40.png");
    private final Image enemy180_Level2_20Image = new Image("file:src/resources/images/enemy/level2/enemy180/enemy20.png");

    public void update() {
        for (var enemy : Level.enemy180Level2List) {
            if(enemy.health<=180) {
                enemy.setFill(new ImagePattern(enemy180_Level2_180Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=160) {
                enemy.setFill(new ImagePattern(enemy180_Level2_160Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=140) {
                enemy.setFill(new ImagePattern(enemy180_Level2_140Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=120) {
                enemy.setFill(new ImagePattern(enemy180_Level2_120Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=100) {
                enemy.setFill(new ImagePattern(enemy180_Level2_100Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=80) {
                enemy.setFill(new ImagePattern(enemy180_Level2_80Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=60) {
                enemy.setFill(new ImagePattern(enemy180_Level2_60Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=40) {
                enemy.setFill(new ImagePattern(enemy180_Level2_40Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=20) {
                enemy.setFill(new ImagePattern(enemy180_Level2_20Image, 0, 0, 1, 1, true));
            }
        }

        mayShoot();
        moveEnemyLevel2(-1);
    }
}
