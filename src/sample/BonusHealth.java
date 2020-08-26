package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class BonusHealth extends Bonus{
    BonusHealth(double x, double y, double w, double h, Color color,int health) {
        super(x, y, w, h, color,health);
    }
    private final MediaPlayer playBonusHealthSound = new MediaPlayer(new Media(new File("src/resources/sounds/healthBonus.mp3").toURI().toString()));

    public void update() {
        this.move(0,2);
        if (this.getTranslateY()>850){
            Level.removeGameObject(this,Level.bonusList);
        }
        if (this.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
            Level.removeGameObject(this,Level.bonusList);
            Level.player.addHealth(20);
            GameEngine.score-=20;
            playBonusHealthSound.seek(Duration.ZERO);
            playBonusHealthSound.play();
            playBonusHealthSound.setVolume(0.2);
        }
    }
}