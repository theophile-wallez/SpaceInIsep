package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class Ammo extends GameObjects {
    double velocity;
    boolean isHuman;
    TypeOfAmmo typeOfAmmo;

    Ammo(double x, double y, double w, double h, Color color, double velocity,boolean isHuman,int health, TypeOfAmmo typeOfAmmo) {
        super(x, y, w, h, color,health);
        this.velocity = velocity;
        this.isHuman = isHuman;
        this.typeOfAmmo = typeOfAmmo;
        if (this.typeOfAmmo == TypeOfAmmo.RIGHT)
            this.setRotate(10);
        else if (this.typeOfAmmo == TypeOfAmmo.LEFT)
            this.setRotate(-10);
    }
    private final MediaPlayer playshipDeadSound = new MediaPlayer(new Media(new File("src/resources/sounds/shipDead.mp3").toURI().toString()));
    private final MediaPlayer playPlayerGotHit = new MediaPlayer(new Media(new File("src/resources/sounds/playerGotHit.mp3").toURI().toString()));

    public void update(){

        if(BonusAmmoX2.isAmmoX2 && isHuman && (TypeOfAmmo.RIGHT == typeOfAmmo || TypeOfAmmo.LEFT == typeOfAmmo))
        {
            teleportObject();
            if(typeOfAmmo == TypeOfAmmo.RIGHT) {
                this.move(2, 8 * this.velocity);
            }
            else {
                this.move(-2, 8 * this.velocity);
            }
        }
        else this.move(0,8 * this.velocity);

        if (this.getTranslateY() < -50 || this.getTranslateY() > 850){
            Level.removeGameObject(this, Level.ammoList);
        }
        if(this.getTranslateY() < -50 && !BonusScoreX2.isScorex2){
            GameEngine.score -= 5;
        }

        if(this.isHuman){
            var tempEnnemyList = Level.enemyList.toArray(new GameObjects[0]);
            for (var enemy : tempEnnemyList) {
                //Collision entre une balle du joueur et un ennemi
                if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    //Level.destroyGameObjectsAndAmmo(enemy,this,Level.enemyList);
                    enemy.health -= Player.damageOfPlayerAmmo;

                    Level.removeGameObject(this,Level.ammoList);
                    if(BonusScoreX2.isScorex2){
                        GameEngine.score += 10;
                    }
                    else {
                        GameEngine.score += 5;
                    }

                    //Probabilité d apparition d un bonus
                    if(enemy.health <= 0){
                        //Augmentation du score si un ennemi est éliminé
                        playshipDeadSound.seek(Duration.ZERO);
                        playshipDeadSound.play();
                        playshipDeadSound.setVolume(0.2);

                        if(BonusScoreX2.isScorex2){
                            GameEngine.score += 30;
                        }
                        else {
                            GameEngine.score += 15;
                        }

                        Level.enemy60Level1List.remove(enemy);
                        Level.enemy120Level1List.remove(enemy);
                        Level.enemy180Level2List.remove(enemy);
                        Level.enemy120Level2List.remove(enemy);
                        Level.removeGameObject(enemy,Level.enemyList);
                        Bonus.addBonus(enemy);
                    }
                }
            }
        }
        else {
            //Collision entre une balle d un ennmi et le joueur
            //parcours sur une copie de la liste qui va être modifiée
            var tempPlayerList = Level.tempPlayer.toArray(new GameObjects[0]);
            for (var ignored : tempPlayerList) {
                if (this.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                    Level.removeGameObject(this,Level.ammoList);
                    Level.player.health-=20;
                    playPlayerGotHit.seek(Duration.ZERO);
                    playPlayerGotHit.play();
                    playPlayerGotHit.setVolume(0.2);
                }
            }

            //Collision entre une balle d'un ennemi et une barriere de protection
            //parcours sur une copie de la liste qui va être modifiée
            var tempBarriereList = Level.barriereList.toArray(new GameObjects[0]);
            for (var barriere : tempBarriereList) {
                if (this.getBoundsInParent().intersects(barriere.getBoundsInParent())) {
                    barriere.health-=20;
                    Level.removeGameObject(this,Level.ammoList);
                    if(barriere.health==0){
                        Level.removeGameObject(barriere,Level.barriereList);
                    }
                }
            }
        }
    }
}