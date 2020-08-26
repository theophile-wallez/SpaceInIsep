package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level extends Application {

    public static final Random random = new Random();
    public static GameEngine gameEngine;

    public static List<GameObjects> ammoList = new ArrayList<>();
    public static List<GameObjects> enemyList = new ArrayList<>();
    public static List<GameObjects> enemy60Level1List = new ArrayList<>();
    public static List<GameObjects> enemy120Level1List = new ArrayList<>();
    public static List<GameObjects> enemy120Level2List = new ArrayList<>();
    public static List<GameObjects> enemy180Level2List = new ArrayList<>();
    public static List<GameObjects> enemy60Level3List =  new ArrayList<>();
    public static List<GameObjects> enemy120Level3List = new ArrayList<>();
    public static List<GameObjects> enemy180Level3List = new ArrayList<>();
    public static List<GameObjects> enemy600Level3List = new ArrayList<>();
    public static List<GameObjects> barriereList = new ArrayList<>();
    public static List<GameObjects> starsList = new ArrayList<>();
    public static List<GameObjects> bonusList = new ArrayList<>();
    public static List<GameObjects> tempPlayer = new ArrayList<>();
    //Liste dans laquelle on ajoute tous les éléments
    public static List<GameObjects> gameElements = new ArrayList<>();



    public static void main(String[] args) {
        launch(args);
    }

    //Déclaration du player
    public static Player player;

    public void start(Stage gameStage) {
        gameEngine = new GameEngine();

        player = new Player(280, 720, 50, 50,  Color.GHOSTWHITE,true,60);

        //GameEngine.isGameRunning=false;

        gameEngine.setStage(player,gameStage);

        /*Ajout de player à enemyList pour éviter de passer sur l'écran de fin de niveau dès le début
        le player est ensuite supprimé de la liste à chaque début de niveau*/
        enemyList.add(player);

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                update();
            }
        };

        timer.start();

        GameEngine.isFirstScreen=true;
    }

    public static void removeGameObject(GameObjects object, List<GameObjects> gameObjectsList){
        GameEngine.removeElementToDisplay(object);
        gameObjectsList.remove(object);
        gameElements.remove(object);
    }

    public static void addObject(GameObjects gameObject, List<GameObjects> gameObjectList){
        gameObjectList.add(gameObject);
        gameElements.add(gameObject);
        gameEngine.addOneElementToDisplay(gameObject);
    }

    public static void createStars(GameEngine gameEngine){
        for(int i=0; i<400;i++){
            int ranX = random.nextInt(800);
            int ranY = random.nextInt(800);
            int ranSize= random.nextInt(3);
            Stars stars = new Stars(ranX,ranY,ranSize,ranSize,Color.rgb(255,255,255,0.5),0);
            starsList.add(stars);
        }
        gameEngine.addElementsToDisplay(starsList);
    }

    public static void createBarriere(GameEngine gameEngine){
        for (int i = 0; i < 2; i++) {
            Barriere barriere = new Barriere(35 +i*145 , 650, 110, 15, Color.DARKCYAN, 60);
            barriereList.add(barriere);
        }
        for (int i = 0; i < 2; i++) {
            Barriere barriere = new Barriere(505 +i*145 , 650, 110, 15, Color.DARKCYAN, 60);
            barriereList.add(barriere);
        }
        gameElements.addAll(barriereList);
        gameEngine.addElementsToDisplay(barriereList);
    }

    private static void update() {
        if(GameEngine.isGameRunning){
            var tempList = gameElements.toArray(new GameObjects[0]);
            for (var item: tempList) {
                item.update();
            }
        }
        for (var stars : starsList){
            stars.update();
        }
        gameEngine.update();
    }
}
