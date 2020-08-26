package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Enemy300Level3 extends Enemy {

    private final Image enemy600_Level3_600Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy120.png");
    private final Image enemy600_Level3_500Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy100.png");
    private final Image enemy600_Level3_400Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy80.png");
    private final Image enemy600_Level3_300Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy60.png");
    private final Image enemy600_Level3_200Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy40.png");
    private final Image enemy600_Level3_100Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy20.png");

    private Boolean islifebarcreated = false;

    public Enemy300Level3(int xEnemy, int yEnemy, int w, int h, Color color, int health, boolean isHit) {
        super(xEnemy, yEnemy, w, h, color, health, isHit);
    }

    public void update() {
        for (var enemy : Level.enemy600Level3List) {
            if(enemy.health <= 600) {
                enemy.setFill(new ImagePattern(enemy600_Level3_600Image, 0, 0, 1, 1, true));
            }
            if(enemy.health <= 500) {
                enemy.setFill(new ImagePattern(enemy600_Level3_500Image, 0, 0, 1, 1, true));
            }
            if(enemy.health <= 400) {
                enemy.setFill(new ImagePattern(enemy600_Level3_400Image, 0, 0, 1, 1, true));
            }
            if(enemy.health <= 300) {
                enemy.setFill(new ImagePattern(enemy600_Level3_300Image, 0, 0, 1, 1, true));
            }
            if(enemy.health <= 200) {
                enemy.setFill(new ImagePattern(enemy600_Level3_200Image, 0, 0, 1, 1, true));
            }
            if(enemy.health <= 100) {
                enemy.setFill(new ImagePattern(enemy600_Level3_100Image, 0, 0, 1, 1, true));
            }
        }

        if (Level.enemyList.stream().count() == 1) {
            moveBossLevel3(super.getTranslateY());
            mayShootBossLevel3();
            if (!islifebarcreated){
                LifeBar lifeBar = new LifeBar();
                Level.gameElements.add(lifeBar);
                Level.gameEngine.addOneElementToDisplay(lifeBar);
                islifebarcreated = true;
             }

        }
    }
}
