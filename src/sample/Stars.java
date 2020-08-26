package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Random;

public class Stars extends GameObjects {
    private final Random random = new Random();
    Stars(int x, int y, int w, int h, Color color,int health) {
        super(x, y, w, h, color,health);
    }
    public void update(){
        if(getTranslateY()<800){
            if ((BonusTimeSlowed.isTimeSLowed)) {
                move(0,0.3);
            } else {
                move(0,0.6);
            }
        }
        else {
            setTranslateY(0);
            setTranslateX(random.nextInt(800));
        }
    }
}
