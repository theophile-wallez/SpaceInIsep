package sample;

import javafx.scene.paint.Color;

public class BonusAmmoX2 extends Bonus{

    BonusAmmoX2(double x, double y, double w, double h, Color color, int health) {
        super(x, y, w, h, color, health);
    }

    public static boolean isAmmoX2 = false;
    public static int bonusAmmoX2Time = 0;
    private boolean isTimerStarted = false;
    private boolean hasCollided = false;

    public void update()
    {
        this.move(0, 2);

        if (this.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
            hasCollided = true;
            this.setTranslateX(850);
            GameEngine.score += 50;
            bonusAmmoX2Time = 0;
            isTimerStarted = false;
            isAnyBonusActive = false;
            isAmmoX2 = false;
            GameEngine.removeElementToDisplay(this);
            isAmmoX2 = true;
            isTimerStarted = true;
            isAnyBonusActive = true;
        }

        if(isTimerStarted && isAmmoX2){
            bonusAmmoX2Time += 1;
            if(bonusAmmoX2Time == 1400) {
                bonusAmmoX2Time = 0;
                isTimerStarted = false;
                isAmmoX2 = false;
                Level.removeGameObject(this,Level.bonusList);
                hasCollided = false;
                isTimerStarted = false;
                isAnyBonusActive = false;
            }
        }

        if(this.getTranslateY() > 850)
        {
            if(hasCollided)
            {
                GameEngine.removeElementToDisplay(this);
            }
            else
            {
                Level.removeGameObject(this, Level.bonusList);
            }
        }
    }
}
