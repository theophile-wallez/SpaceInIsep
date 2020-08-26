package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObjects extends Rectangle {
   //Est-ce que je rajoute static ?
    protected int health;

    //ajouter une methode pour diminuer la vie (utiliser this)

    public GameObjects(double x, double y, double w, double h, Color color,int health) {
        super(w, h, color);
        this.health=health;
        setTranslateX(x);
        setTranslateY(y);
    }
    public void move(double x,double y){
        setTranslateX(getTranslateX() + x);
        setTranslateY(getTranslateY()+y);
    }

    public void teleportObject(){
        if(getTranslateX()<=-50){
            setTranslateX(800);
        }
        else if(getTranslateX()>=800){
            setTranslateX(-50);
        }
    }
    public void update(){}
}