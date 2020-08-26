package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Bonus extends GameObjects{
    Bonus(double x, double y, double w, double h, Color color,int health) {
        super(x, y, w, h, color,health);
    }
    public static boolean isAnyBonusActive = false;

    private static final String bonusHealthImagePath = "file:src/resources/images/bonus/health.png";
    private static final String bonusTimeSlowedImagePath = "file:src/resources/images/bonus/timeSlowed.gif";
    private static final String bonusDamagePath = "file:src/resources/images/bonus/damage.png";
    private static final String bonusScoreX2Path = "file:src/resources/images/bonus/scorex2.png";
    private static final String bonusAmmoX2Path = "file:src/resources/images/bonus/ammox2.png";

    public static void addBonus(GameObjects gameObjects){
        int typeOfBonus;
        double bonusX = gameObjects.getTranslateX();
        double bonusY = gameObjects.getTranslateY();

        if(Level.player.health<60){
            typeOfBonus = Level.random.nextInt(6);
        }
        else{
            typeOfBonus = Level.random.nextInt(5);
        }

        double n = Level.random.nextDouble();

        if (n<0.5) {
            if(Level.bonusList.size()==0){
                if(typeOfBonus == 1){
                    BonusDamage bonusDamage = new BonusDamage(bonusX,bonusY, 25, 32, Color.RED,0);
                    Image bonusDamageImage = new Image(bonusDamagePath);
                    bonusDamage.setFill(new ImagePattern(bonusDamageImage, 0, 0, 1, 1, true));
                    Level.addObject(bonusDamage,Level.bonusList);
                }
                else if(typeOfBonus == 2){
                    BonusTimeSlowed bonusTimeSlowed = new BonusTimeSlowed(bonusX,bonusY, 25, 25, Color.GOLD,0);
                    Image bonusTimeSlowedImage = new Image(bonusTimeSlowedImagePath);
                    bonusTimeSlowed.setFill(new ImagePattern(bonusTimeSlowedImage, 0, 0, 1, 1, true));
                    Level.addObject(bonusTimeSlowed,Level.bonusList);
                }
                else if(typeOfBonus == 3){
                    BonusScoreX2 bonusScoreX2 = new BonusScoreX2(bonusX, bonusY, 25, 25, Color.GREEN, 0);
                    Image bonusScoreX2Image = new Image(bonusScoreX2Path);
                    bonusScoreX2.setFill(new ImagePattern(bonusScoreX2Image, 0, 0, 1, 1, true));
                    Level.addObject(bonusScoreX2,Level.bonusList);
                }
                else if(typeOfBonus == 4)
                {
                    BonusAmmoX2 bonusAmmoX2 = new BonusAmmoX2(bonusX, bonusY, 25, 25, Color.BLUE, 0);
                    Image bonusAmmoX2Image = new Image(bonusAmmoX2Path);
                    bonusAmmoX2.setFill(new ImagePattern(bonusAmmoX2Image, 0, 0, 1, 1, true));
                    Level.addObject(bonusAmmoX2, Level.bonusList);
                }
            }
            if (n<0.25){
                if(typeOfBonus == 5){
                    BonusHealth bonusHealth = new BonusHealth(bonusX, bonusY, 20, 20, Color.GREEN, 0);
                    Image bonusHealthImage = new Image(bonusHealthImagePath);
                    bonusHealth.setFill(new ImagePattern(bonusHealthImage, 0, 0, 1, 1, true));
                    Level.addObject(bonusHealth,Level.bonusList);
                }
            }

        }
    }
}
