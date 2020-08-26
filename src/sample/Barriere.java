package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Barriere extends GameObjects {

    Barriere(int x, int y, int w, int h, Color color, int health) {
        super(x, y, w, h, color,health);
    }

    private final Image barriere_60Image = new Image("file:src/resources/images/barriere/barriere60.png");
    private final Image barriere_40Image = new Image("file:src/resources/images/barriere/barriere40.png");
    private final Image barriere_20Image = new Image("file:src/resources/images/barriere/barriere20.png");

    public void update() {
        for (var barriere : Level.barriereList) {
            if(barriere.health<=60) {
                barriere.setFill(new ImagePattern(barriere_60Image, 0, 0, 1, 1, true));
            }
            if(barriere.health<=40) {
                barriere.setFill(new ImagePattern(barriere_40Image, 0, 0, 1, 1, true));
            }
            if(barriere.health<=20) {
                barriere.setFill(new ImagePattern(barriere_20Image, 0, 0, 1, 1, true));
            }
        }
    }
}
