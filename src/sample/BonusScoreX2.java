package sample;

import javafx.scene.paint.Color;

public class BonusScoreX2 extends Bonus {
    BonusScoreX2(double x, double y, double w, double h, Color color, int health) {
        super(x, y, w, h, color, health);

    }
    public static boolean isScorex2 = false;
    public static int bonusScoreX2Time =0;
    private boolean isTimerStarted = false;
    private boolean hasCollided = false;

    public void update() {

        this.move(0,2);
        if (this.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
            hasCollided=true;
            this.setTranslateX(850);
            GameEngine.score+=50;
            bonusScoreX2Time =0;
            isTimerStarted=false;
            isAnyBonusActive=false;
            isScorex2 =false;
            GameEngine.removeElementToDisplay(this);
            isScorex2 =true;
            isTimerStarted=true;
            isAnyBonusActive=true;
        }

        if(isTimerStarted&& isScorex2){
            bonusScoreX2Time +=1;
            if(bonusScoreX2Time == 1400) {
                bonusScoreX2Time = 0;
                isTimerStarted = false;
                isScorex2 =false;
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
