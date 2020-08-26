package sample;
import javafx.scene.paint.Color;

import java.util.Random;

public class Enemy extends Ship{

    Random random;
    int initialHealth;
    public Enemy(int xEnemy, int yEnemy, int w, int h, Color color, int health,boolean isHit) {
        super(xEnemy, yEnemy, w, h, color,false,health);
        this.random = new Random();
    }

    double t;
    public void mayShoot() {
        t+=0.16;
        if(t>3){
            double n = random.nextDouble();
            if (n>0.99){
                this.shoot();
            }
            t=0;
        }
    }

    double t2;
    public void mayShootLevel3()
    {
        t2 += 0.1;
        if(t2 > 4)
        {
            double n = random.nextDouble();
            if(n > 0.78)
            {
                this.shoot();
            }
            t2 = 0;
        }
    }

    double t3;
    public void mayShootBossLevel3()
    {
       t3 += 0.1;
       if(t3 > 6)
       {
           double n = random.nextDouble();
           if(n > 0.5)
           {
               this.shootBossLevel3();
           }
           t3 = 0;
       }
    }

    double timeMoveEnemy;
    public void moveEnemyLevel1(){
        timeMoveEnemy += 0.16;
        if(timeMoveEnemy > 60){
            timeMoveEnemy = 0;
        }

        if(timeMoveEnemy < 30){
            if ((BonusTimeSlowed.isTimeSLowed)) {

                this.move(0.05, 0);
            } else {
                this.move(0.1, 0);
            }
        }
        else{
            if ((BonusTimeSlowed.isTimeSLowed)) {
                this.move(-0.05, 0);
            } else {
                this.move(-0.1, 0);
            }
        }
    }

    public void moveEnemyLevel2(int velocity){
        teleportObject();

        if ((BonusTimeSlowed.isTimeSLowed)) {
            this.move(velocity*0.25,0);
        } else {
            this.move(velocity*0.5,0);
        }
    }

    public void moveEnemyLevel3Enemy60(boolean touchDown)
    {
        teleportObject();

        if(BonusTimeSlowed.isTimeSLowed)
        {
            if(touchDown)
                this.move(2, -2);
            else
                this.move(2, 2);
        }
        else{
            if(touchDown)
                this.move(4, -4);
            else
                this.move(4, 4);
        }
    }
    int tmpPos = 0;
    int tmpX = 1;
    int speed = 2;
    public void moveBossLevel3(double positionY)
    {

        if (positionY == 100)
        {
            teleportObject();


            if (tmpPos > 200) {
                tmpX = Level.random.nextBoolean() ? 1 : -1;
                speed = Level.random.nextInt(4);
                tmpPos = 0;
            }

            if ((BonusTimeSlowed.isTimeSLowed)) {
                this.move(speed * tmpX * 0.25, 0);
            } else {
                this.move(speed * tmpX * 0.5, 0);
            }

            tmpPos++;        }
        else{
            this.move(0,5);
        }
    }
}