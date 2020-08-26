package sample;

import javafx.event.EventDispatchChain;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.io.File;

public class Player extends Ship implements javafx.event.EventTarget{
    //Son du tir d'une balle tirée par le joueur
    private final MediaPlayer playShootSound = new MediaPlayer(new Media(new File("src/resources/sounds/shootSound.mp3").toURI().toString()));

    //Images du joueur
    private final Image playerShip_60Image = new Image("file:src/resources/images/player/playerShip60.png");
    private final Image playerShip_40Image = new Image("file:src/resources/images/player/playerShip40.png");
    private final Image playerShip_20Image = new Image("file:src/resources/images/player/playerShip20.png");

    public static boolean isGoingRight=false;
    public static boolean isGoingLeft=false;
    public static boolean isShooting =false;
    public static boolean isGamePaused=false;
    public static boolean shootOneTime =false;
    int x,y;
    public static int damageOfPlayerAmmo=20;
    public static int nbAmeliorationDamage = 0;
    public static int velocity = 1;
    public static int healthMax;
    private static final MotionBlur motionBlur= new MotionBlur();

    Player(int x, int y, int w, int h, Color color, boolean isHuman, int health) {
        super(x, y, w, h, color, isHuman, health);
        healthMax = health;
        this.x=x;
        this.y=y;
    }

    public void keyPressed(KeyEvent keyEvent) {
        if(GameEngine.isGameRunning){
            switch (keyEvent.getCode()) {
                case Q -> isGoingLeft=true;
                case D -> isGoingRight=true;
                case SPACE -> isShooting=true;
            }
        }
        if(!GameEngine.isFirstScreen&&Level.player.health!=0&&Level.enemyList.size()!=0){
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                isGamePaused=!isGamePaused;
                GameEngine.onetime=false;
                GameEngine.startIncreasingScore=false;
            }
        }


        if(!GameEngine.isGameRunning){
            if (keyEvent.getCode() == KeyCode.ENTER){
                GameEngine.playGame();
            }
        }

        if(!GameEngine.isGameRunning && GameEngine.ameliorationPoint != 0)
        {
            if (keyEvent.getCode() == KeyCode.W)
            {
                velocity++;
                GameEngine.UpdateAmeliorationPoint();
            }
            else if(keyEvent.getCode() == KeyCode.X)
            {
                healthMax += 40;
                Level.player.health += 40;
                GameEngine.UpdateAmeliorationPoint();
            }
            else if (keyEvent.getCode() == KeyCode.C)
            {
                nbAmeliorationDamage++;
                GameEngine.UpdateAmeliorationPoint();
            }
        }
    }
    public void keyReleased(KeyEvent keyEvent){
        if(GameEngine.isGameRunning){
            switch (keyEvent.getCode()) {
                case Q -> isGoingLeft=false;
                case D -> isGoingRight=false;
                case SPACE -> {
                    isShooting=false;
                    shootOneTime=false;
                }
            }
        }
    }

//ajout de ces lignes par IntelliJ
    @Override
    public EventDispatchChain buildEventDispatchChain(EventDispatchChain eventDispatchChain) {
        return null;
    }

    public void addHealth(int healthBonusToAdd) {
        health+=healthBonusToAdd;
    }

    public int getHealth() {
        return health;
    }

    public void resetPlayer(){
        health = healthMax;
        this.setTranslateX(280);
        this.setTranslateY(720);
    }

    public void resetAllStatPlayer()
    {
        healthMax = 60;
        health = 60;
        velocity = 1;
        damageOfPlayerAmmo = 20;
        nbAmeliorationDamage = 0;
    }

    private void playShootSound(){
        playShootSound.seek(Duration.ZERO);
        playShootSound.play();
        playShootSound.setVolume(0.2);
    }
    public void update(){
        this.setRotate(0);
        motionBlur.setRadius(0);
        this.setEffect(motionBlur);
        if(isGoingRight){
            this.move(2 * velocity, 0);
            this.setRotate(5);
            motionBlur.setRadius(10);
            motionBlur.setAngle(0);

            if(isShooting){
                if (!shootOneTime){
                    this.shoot();
                    playShootSound();
                    isShooting=false;
                    shootOneTime = true;
                }
            }
        }
        if(isGoingLeft){
            this.move(-2 * velocity, 0);
            this.setRotate(-5);
            motionBlur.setRadius(10);
            motionBlur.setAngle(180);
            this.setEffect(motionBlur);
            if(isShooting){
                if (!shootOneTime){
                    this.shoot();
                    playShootSound();
                    isShooting=false;
                    shootOneTime = true;
                }
            }
        }
        if(isShooting){
            if (!shootOneTime){
                this.shoot();
                playShootSound();
                isShooting=false;
                shootOneTime = true;
            }
        }


        teleportObject();

        if(BonusDamage.isDamageIncreased){
            damageOfPlayerAmmo = 20 + nbAmeliorationDamage * 10 + 10;
        }
        else{
            damageOfPlayerAmmo = 20 + nbAmeliorationDamage * 10;
        }


        //Variations de l'image du joueur
//        if(Level.player.health>40&&Level.player.health<=60) {
//            Level.player.setFill(new ImagePattern(playerShip_60Image, 0, 0, 1, 1, true));
//        }
//        if(Level.player.health>20&&Level.player.health<=40) {
//            Level.player.setFill(new ImagePattern(playerShip_40Image, 0, 0, 1, 1, true));
//        }
//        if(Level.player.health>0&&Level.player.health<=20) {
//            Level.player.setFill(new ImagePattern(playerShip_20Image, 0, 0, 1, 1, true));
//        }

        double txt = (double)Level.player.health / (double)healthMax;
        var percentHealth = Math.floor((double)Level.player.health / (double)healthMax * 100);
        if(percentHealth > 66 && percentHealth <= 100)
            Level.player.setFill(new ImagePattern(playerShip_60Image, 0, 0, 1, 1, true));
        if(percentHealth > 33 && percentHealth <= 66)
            Level.player.setFill(new ImagePattern(playerShip_40Image, 0, 0, 1, 1, true));
        if(percentHealth > 0 && percentHealth <= 33)
            Level.player.setFill(new ImagePattern(playerShip_20Image, 0, 0, 1, 1, true));

        //Empêche la vie du joueur de dépasser 3
        if(Level.player.health > healthMax){
            Level.player.health = healthMax;
        }
    }
}
