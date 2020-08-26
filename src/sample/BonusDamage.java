package sample;

import javafx.scene.paint.Color;

public class BonusDamage extends Bonus{
    BonusDamage(double x, double y, double w, double h, Color color,int health) {
        super(x, y, w, h, color,health);
    }

    public static boolean isDamageIncreased = false;
    public static int bonusDamageTime =0;
    private boolean isTimerStarted = false;
    private boolean hasCollided = false;

    public void update() {

        this.move(0,2);
        if (this.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
            hasCollided=true;
            this.setTranslateX(850);
            GameEngine.score+=50;
            bonusDamageTime =0;
            isTimerStarted=false;
            isAnyBonusActive=false;
            isDamageIncreased =false;
            GameEngine.removeElementToDisplay(this);
            isDamageIncreased =true;
            isTimerStarted=true;
            isAnyBonusActive=true;
        }

        if(isTimerStarted&& isDamageIncreased){
            bonusDamageTime +=1;
            if(bonusDamageTime == 1400) {
                bonusDamageTime = 0;
                isTimerStarted = false;
                isDamageIncreased =false;
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
