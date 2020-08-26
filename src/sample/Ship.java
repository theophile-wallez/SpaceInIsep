package sample;

import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

public class Ship extends GameObjects {
    private final boolean isHuman;
    private final double width;
    private final double height;
    private static final MotionBlur motionBlurEnemyAmmo = new MotionBlur();
    protected int health;

    Ship(int x, int y, int w, int h, Color color, boolean isHuman, int health) {
        super(x, y, w, h, color,health);
        this.width=(double)w;
        this.height=(double)h;
        this.isHuman = isHuman;
        this.health=health;
    }
    double velocity;
    public void shoot() {
        int largeur, hauteur;
        double plusY;
        //vitesse des balles en fonction du vaisseau
        if ((BonusTimeSlowed.isTimeSLowed)) {
            velocity = (this.isHuman) ? (-1) : 0.2;
        } else {
            velocity = (this.isHuman) ? (-1) : 0.5;
        }
        if(this.isHuman){
            plusY=-(this.height);
            largeur=5;
            hauteur=50;
            motionBlurEnemyAmmo.setRadius(0);
        }
        else{
            plusY = this.height;
            largeur=2;
            hauteur=25;
            motionBlurEnemyAmmo.setRadius(15);
            motionBlurEnemyAmmo.setAngle(-90);

        }
        //Création de la balle
        Ammo ammo;
        Ammo ammoRIGHT = null;
        Ammo ammoLEFT = null;

        if(BonusAmmoX2.isAmmoX2 && isHuman)
        {
            ammo = new Ammo(getTranslateX()+(this.width/2)-(2.5),getTranslateY()+plusY, largeur, hauteur, Color.BLUEVIOLET,velocity,true,Player.damageOfPlayerAmmo, TypeOfAmmo.CENTER);
            ammoRIGHT = new Ammo(getTranslateX()+(this.width/2)-(2.5),getTranslateY()+plusY, largeur, hauteur, Color.BLUEVIOLET,velocity,true,Player.damageOfPlayerAmmo, TypeOfAmmo.RIGHT);
            ammoLEFT = new Ammo(getTranslateX()+(this.width/2)-(2.5),getTranslateY()+plusY, largeur, hauteur, Color.BLUEVIOLET,velocity,true,Player.damageOfPlayerAmmo, TypeOfAmmo.LEFT);
        }
        else
        {
            ammo = new Ammo(getTranslateX()+(this.width/2)-(2.5),getTranslateY()+plusY, largeur, hauteur, Color.BLUEVIOLET,velocity,isHuman,(this.isHuman) ? Player.damageOfPlayerAmmo : 20, TypeOfAmmo.CENTER);
        }

        if(ammoRIGHT != null)
        {
            ammoRIGHT.setEffect(motionBlurEnemyAmmo);
            ammoLEFT.setEffect(motionBlurEnemyAmmo);
            Level.addObject(ammo, Level.ammoList);
            Level.addObject(ammoRIGHT, Level.ammoList);
            Level.addObject(ammoLEFT, Level.ammoList);
        }
        else{
            ammo.setEffect(motionBlurEnemyAmmo);
            //Ajout de l'ammo dans ammoList et affiche l'ammo à l'écran
            Level.addObject(ammo,Level.ammoList);
        }

        if(isHuman){
            if(BonusDamage.isDamageIncreased){
                Image laserPlayerDamage = new Image("file:src/resources/images/laser/laserPlayerDamage.png");
                ammo.setFill(new ImagePattern(laserPlayerDamage, 0, 0, 1, 1, true));
                if (ammoRIGHT != null)
                {
                    ammoRIGHT.setFill(new ImagePattern(laserPlayerDamage, 0, 0, 1, 1, true));
                    ammoLEFT.setFill(new ImagePattern(laserPlayerDamage, 0, 0, 1, 1, true));
                }
            }
            else{
                Image laserPlayer = new Image("file:src/resources/images/laser/laserPlayer.png");
                ammo.setFill(new ImagePattern(laserPlayer, 0, 0, 1, 1, true));
                if (ammoRIGHT != null)
                {
                    ammoRIGHT.setFill(new ImagePattern(laserPlayer, 0, 0, 1, 1, true));
                    ammoLEFT.setFill(new ImagePattern(laserPlayer, 0, 0, 1, 1, true));
                }
            }
        }
    }
    
    public void shootBossLevel3()
    {
        int largeur, hauteur;
        double plusY;
        //vitesse des balles en fonction du vaisseau
        if ((BonusTimeSlowed.isTimeSLowed)) {
            velocity = 0.2;
        } else {
            velocity = 0.5;
        }
        plusY = this.height;
        largeur=2;
        hauteur=25;
        motionBlurEnemyAmmo.setRadius(15);
        motionBlurEnemyAmmo.setAngle(-90);
        //Création de la balle
        var nbAmmo = Level.random.nextInt(5);
        List<Ammo> AmmoList = new ArrayList<>();
        var bound = 800 / (nbAmmo * 4 + 1);
        
        for(int i = 1; i < nbAmmo * 4; i++)
        {
            Ammo ammo = new Ammo(bound * i, getTranslateY() + plusY, largeur, hauteur, Color.BLUEVIOLET, velocity, false, 20, TypeOfAmmo.CENTER);
            AmmoList.add(ammo);
        }

        for (Ammo a : AmmoList) {
            a.setEffect(motionBlurEnemyAmmo);
            Level.addObject(a, Level.ammoList);
        }
//        ammo = new Ammo(getTranslateX()+(this.width/2)-(2.5),getTranslateY()+plusY, largeur, hauteur, Color.BLUEVIOLET,velocity,isHuman,(this.isHuman) ? Player.damageOfPlayerAmmo : 20, TypeOfAmmo.CENTER);
//
//        
//        ammo.setEffect(motionBlurEnemyAmmo);
//        //Ajout de l'ammo dans ammoList et affiche l'ammo à l'écran
//        Level.addObject(ammo,Level.ammoList);
    }
}