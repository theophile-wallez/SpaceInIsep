package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Enemy120Level1 extends Enemy {
    public Enemy120Level1(int xEnemy, int yEnemy, int w, int h, Color color, int health, boolean isHit) {
        super(xEnemy, yEnemy, w, h, color, health, isHit);
    }
    private final Image enemy120_Level1_120Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy120.png");
    private final Image enemy120_Level1_100Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy100.png");
    private final Image enemy120_Level1_80Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy80.png");
    private final Image enemy120_Level1_60Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy60.png");
    private final Image enemy120_Level1_40Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy40.png");
    private final Image enemy120_Level1_20Image = new Image("file:src/resources/images/enemy/level1/enemy120/enemy20.png");
    int timer=0;

    public void update() {
        for (var enemy : Level.enemy120Level1List) {
            if(enemy.health<=120) {
                enemy.setFill(new ImagePattern(enemy120_Level1_120Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=100) {
                enemy.setFill(new ImagePattern(enemy120_Level1_100Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=80) {
                enemy.setFill(new ImagePattern(enemy120_Level1_80Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=60) {
                enemy.setFill(new ImagePattern(enemy120_Level1_60Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=40) {
                enemy.setFill(new ImagePattern(enemy120_Level1_40Image, 0, 0, 1, 1, true));
            }
            if(enemy.health<=20) {
                enemy.setFill(new ImagePattern(enemy120_Level1_20Image, 0, 0, 1, 1, true));
            }
        }

        moveEnemyLevel1();
        mayShoot();

        if(timer<150){
            this.move(0,2);
            timer+=1;
        }
        else{
            timer=200;
        }
    }
}
