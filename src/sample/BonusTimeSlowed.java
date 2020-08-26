package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class BonusTimeSlowed extends Bonus{
    BonusTimeSlowed(double x, double y, double w, double h, Color color,int health) {
        super(x, y, w, h, color,health);
    }
    private final MediaPlayer playBonusTimeSlowedSound = new MediaPlayer(new Media(new File("src/resources/sounds/timeSlowedSound.mp3").toURI().toString()));
    public static boolean isTimeSLowed = false;
    static int bonusTime=0;
    boolean isTimerStarted = false;
    boolean hasCollided = false;


    public void update() {

        this.move(0,2);
        if (this.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
            hasCollided=true;
            this.setTranslateX(850);
            GameEngine.score+=50;
            bonusTime=0;
            isTimerStarted=false;
            isTimeSLowed=false;
            isAnyBonusActive=false;
            GameEngine.removeElementToDisplay(this);

            for (var ammo : Level.ammoList) {
                GameEngine.removeElementToDisplay(ammo);
                Level.gameElements.remove(ammo);
            }
            Level.ammoList.clear();
            isTimeSLowed=true;
            isTimerStarted=true;
            isAnyBonusActive=true;
            playBonusTimeSlowedSound.seek(Duration.ZERO);
            playBonusTimeSlowedSound.play();
        }

        if(isTimerStarted&&isTimeSLowed){
            bonusTime+=1;
            if(bonusTime==1400) {
                bonusTime = 0;
                isTimerStarted = false;
                isTimeSLowed=false;

                for (var ammo : Level.ammoList ) {
                    GameEngine.removeElementToDisplay(ammo);
                    Level.gameElements.remove(ammo);
                }

                Level.ammoList.clear();
                Level.removeGameObject(this,Level.bonusList);
                hasCollided=false;
                isTimerStarted=false;
                isAnyBonusActive=false;
            }
        }

        if (this.getTranslateY()>850){
            if(hasCollided)
                GameEngine.removeElementToDisplay(this);
            else
                Level.removeGameObject(this,Level.bonusList);
        }
    }
}
