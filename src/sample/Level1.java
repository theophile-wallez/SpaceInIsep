package sample;

import javafx.scene.paint.Color;

public class Level1 extends Level {
    public static void initLevel1(GameEngine gameEngine){
        GameEngine.isGameRunning=true;
        enemyList.clear();
        //Création des différentes rangées de vaisseaux ennemis
        for (int i = 0; i < 11; i++) {
            Enemy120Level1 enemy120Level1 = new Enemy120Level1(15 + i*70, -230, 60, 60, Color.RED, 120,false);
            enemy120Level1List.add(enemy120Level1);
        }

        for (int i = 0; i < 8; i++) {
            Enemy60Level1 enemy60Level1 = new Enemy60Level1(130 + i*70, -160, 45, 50, Color.RED,60,false);
            enemy60Level1List.add(enemy60Level1);
        }

        for (int i = 0; i < 5; i++) {
            Enemy60Level1 enemy60Level1 = new Enemy60Level1(235 + i*70, -100, 45, 50,Color.RED,60,false);
            enemy60Level1List.add(enemy60Level1);
        }

        for (int i = 0; i < 2; i++) {
            Enemy60Level1 enemy60Level1 = new Enemy60Level1(340 + i*70, -50, 45, 50, Color.RED,60,false);
            enemy60Level1List.add(enemy60Level1);
        }

        for (int i = 0; i < 1; i++) {
            Enemy60Level1 enemy60Level1 = new Enemy60Level1(375, 0, 45, 50, Color.RED,60,false);
            enemy60Level1List.add(enemy60Level1);
        }

        createStars(Level.gameEngine);
        createBarriere(Level.gameEngine);

        enemyList.addAll(enemy60Level1List);
        enemyList.addAll(enemy120Level1List);

        tempPlayer.add(player);
        gameElements.addAll(tempPlayer);
        gameElements.addAll(enemyList);

        gameEngine.addOneElementToDisplay(player);
        gameEngine.addElementsToDisplay(enemyList);
        gameEngine.createContent();
    }
}
