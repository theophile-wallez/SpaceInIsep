package sample;

import javafx.scene.paint.Color;

public class Level2 extends Level{
    public static void initLevel2(GameEngine gameEngine){
        GameEngine.isGameRunning=true;
        enemyList.clear();

        //Création des différentes rangées de vaisseaux ennemis
        for (int i = 0; i < 6; i++) {
            Enemy120Level2 enemy120Level2 = new Enemy120Level2(15 + i*140, 70, 50, 60, Color.RED, 120,false);
            enemy120Level2List.add(enemy120Level2);
        }

        for (int i = 0; i < 5; i++) {
            Enemy180Level2 enemy180Level2 = new Enemy180Level2(85 + i*140, 140, 50, 60, Color.RED,180,false);
            enemy180Level2List.add(enemy180Level2);
        }

        for (int i = 0; i < 6; i++) {
            Enemy120Level2 enemy120Level2 = new Enemy120Level2(15 + i*140, 210, 50, 60,Color.RED,120,false);
            enemy120Level2List.add(enemy120Level2);
        }

        for (int i = 0; i < 5; i++) {
            Enemy180Level2 enemy180Level2 = new Enemy180Level2(85 + i*140, 280, 50, 60, Color.RED,180,false);
            enemy180Level2List.add(enemy180Level2);
        }

        createStars(Level.gameEngine);
        createBarriere(Level.gameEngine);

        enemyList.addAll(enemy120Level2List);
        enemyList.addAll(enemy180Level2List);

        tempPlayer.add(player);
        gameElements.addAll(tempPlayer);
        gameElements.addAll(enemyList);

        gameEngine.addOneElementToDisplay(player);
        gameEngine.addElementsToDisplay(enemyList);

        gameEngine.createContent();
    }
}
