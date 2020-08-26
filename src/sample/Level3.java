package sample;

import javafx.scene.paint.Color;

public class Level3 extends Level{
    public static void initLevel3(GameEngine gameEngine)
    {
        GameEngine.isGameRunning = true;
        enemyList.clear();


        //Création des rangées d'ennemis
        Enemy300Level3 enemy300Level3 = new Enemy300Level3(320, -230, 180, 150, Color.RED, 600, false);
        enemy600Level3List.add(enemy300Level3);

        for(int i = 0; i < 11; i++)
        {
            Enemy180Level3 enemy180Level3 = new Enemy180Level3(20 + i * 70, 60, 50, 60, Color.RED, 180, false);
            enemy180Level3List.add(enemy180Level3);
        }
        for(int i = 0; i < 11; i++)
        {
            Enemy120Level3 enemy120Level3 = new Enemy120Level3(20 + i * 70, 120, 50, 60, Color.RED, 120, false);
            enemy120Level3List.add(enemy120Level3);
        }
        for(int i = 0; i < 11; i++)
        {
            Enemy120Level3 enemy120Level3 = new Enemy120Level3(20 + i * 70, 180, 50, 60, Color.RED, 120, false);
            enemy120Level3List.add(enemy120Level3);
        }
        for(int i = 0; i < 11; i++)
        {
            Enemy60Level3 enemy60Level3 = new Enemy60Level3(20 + i * 70, 240 + i * 30 , 50, 60, Color.RED, 60, false);
            enemy60Level3List.add(enemy60Level3);
        }

        createStars(Level.gameEngine);
        createBarriere(Level.gameEngine);

        enemyList.addAll(enemy60Level3List);
        enemyList.addAll(enemy120Level3List);
        enemyList.addAll(enemy180Level3List);
        enemyList.addAll(enemy600Level3List);

        tempPlayer.add(player);
        gameElements.addAll(tempPlayer);
        gameElements.addAll(enemyList);

        gameEngine.addOneElementToDisplay(player);
        gameEngine.addElementsToDisplay(enemyList);

        gameEngine.createContent();
    }
}
