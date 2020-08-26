package sample;

import javafx.scene.paint.Color;

public class LifeBar extends GameObjects {
    
    public LifeBar() {
        super(100, 100, 600, 5, Color.web("#7befb2"), 50);

    }

    @Override
    public void update() {
        System.out.println(Level.enemy600Level3List.get(0).health);
        this.setWidth(Level.enemy600Level3List.get(0).health);
    }
}
