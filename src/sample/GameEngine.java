package sample;

import com.google.gson.Gson;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class GameEngine{
    Stage stage2 = new Stage();
    //Initialisation du score à 0
    public static int score=0;
    //Création de la fenêtre
    public static Pane root = new Pane();
    //Initialisation du score
    private static GraphicsContext graphicsContext;

    public static final Random random = new Random();

    private static double scoreTemp =0.0;
    private static double moveText=0;
    private static double d=0;
    private final MediaPlayer playVictorySound = new MediaPlayer(new Media(new File("src/resources/sounds/victory.mp3").toURI().toString()));
    public static boolean isGameRunning;
    public static boolean isFirstScreen;
    public static boolean onetime =false;
    private static int positionX =0;
    private static double opacityText=0;
    public static int isLevel;
    public static boolean startIncreasingScore=false;
    private static final GaussianBlur gaussianBlur = new GaussianBlur();

    public static int ameliorationPoint = 0;
    public static boolean nbAmeliorationUpdated = false;
    public int[] tabScore = new int[3];
    public String[] tabNameScore = {"1er", "2eme", "3eme", "4eme", "5eme"};
    private LeaderBoard LeaderBoard = null;
    private static boolean isLeaderBoardUpdated = false;
    private Gson g = new Gson();

    public void setStage(Player player ,Stage stage){
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("SpaceInIsep");
        stage.getIcons().add(new Image("file:src/resources/images/player/playerShip60.png"));
        scene.setOnKeyPressed(player::keyPressed);
        scene.setOnKeyReleased(player::keyReleased);
    }

    public Parent createContent() {
        Canvas canvas = new Canvas(800, 800);
        graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        root.setPrefSize(800, 800);

        root.setStyle("-fx-background-color: #100120;");

        return root;
    }

    private void displayText(String texte,int positionX,int positionY,int size,String colorHex,double opacity){
        Color color = Color.web("0x"+colorHex,opacity);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.loadFont("file:src/resources/fonts/Dark Seed.otf",size));
        graphicsContext.setFill(color);
        graphicsContext.fillText(texte,positionX,positionY);
    }

    private void displayTimerText(int timer){
        Color color;
        if (timer>3){
            color = Color.web("0xecf0f1", 0.1);
        }

        else {
            color = Color.web("0xe01a4f", 0.1);
        }
        graphicsContext.setFill(color);

        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.loadFont("file:src/resources/fonts/Dark Seed.otf",130));

        graphicsContext.fillText(Integer.toString(timer), 400, 500);
    }
    //Methodes qui permettent d afficher les objets à l ecran
    public void addOneElementToDisplay(GameObjects gameObjects){
        root.getChildren().add(gameObjects);
    }

    public void addElementsToDisplay(List<GameObjects> ObjectsList){
        for (var item : ObjectsList) {
            addOneElementToDisplay(item);
        }
    }

    //Methode qui permet de supprimer des objets à l ecran
    public static void removeElementToDisplay(GameObjects gameObjects){
        root.getChildren().remove(gameObjects);
    }

    public void nextLevel(){
        isLevel+=1;
    }

    public static void playGame(){
        nbAmeliorationUpdated = false;
        for (var item : Level.gameElements) {
            removeElementToDisplay(item);
        }
        for (var stars : Level.starsList) {
            removeElementToDisplay(stars);
        }

        scoreTemp =0;
        positionX=0;
        opacityText=0;

        Level.enemy60Level1List.clear();
        Level.enemy120Level1List.clear();
        Level.enemy120Level2List.clear();
        Level.enemy180Level2List.clear();
        Level.enemy60Level3List.clear();
        Level.enemy120Level3List.clear();
        Level.enemy180Level3List.clear();
        Level.enemy600Level3List.clear();

        Level.ammoList.clear();
        Level.enemyList.clear();
        Level.barriereList.clear();
        Level.starsList.clear();
        Level.tempPlayer.clear();
        Level.gameElements.clear();
        Level.bonusList.clear();


        isGameRunning=false;
        Player.isGamePaused=false;
        Player.isGoingRight=false;
        Player.isGoingLeft=false;
        Player.isShooting=false;

        BonusTimeSlowed.isTimeSLowed=false;
        BonusDamage.isDamageIncreased=false;
        BonusScoreX2.isScorex2=false;
        BonusAmmoX2.isAmmoX2 = false;
        startIncreasingScore=false;

        onetime =false;
        Bonus.isAnyBonusActive=false;
        isFirstScreen=false;
        BonusTimeSlowed.bonusTime=0;
        score=0;
        graphicsContext.clearRect(0, 0, 800, 800);
        Level.player.resetPlayer();
        Level.enemyList.add(Level.player);

        if(isLevel==1){
            Level1.initLevel1(Level.gameEngine);
        }
        if(isLevel==2){
            Level2.initLevel2(Level.gameEngine);
        }
        if(isLevel==3){
            Level3.initLevel3(Level.gameEngine);
        }
        if(isLevel == 4)
        {
            isFirstScreen = true;
            isLeaderBoardUpdated = false;
            Level.player.resetAllStatPlayer();
        }
    }

    private void displayScore(int X, double opacityText,boolean start){
        if(scoreTemp<score&&start){
            if(scoreTemp <=10){
                scoreTemp +=0.4;
            }
            else if(scoreTemp >10 && scoreTemp <=20){
                scoreTemp +=0.6;
            }
            else if(scoreTemp >20&& scoreTemp <=100){
                scoreTemp +=1.5;
            }
            else if(scoreTemp>100 && scoreTemp <=400){
                scoreTemp +=3;
            }
            else if(scoreTemp >400){
                scoreTemp +=5;
            }
        }
        else {
            startIncreasingScore=false;
        }

        int scoreFinal=(int) scoreTemp;
        scoreFinal=scoreFinal-scoreFinal%5;

        Color color = Color.web("0xfac22f",opacityText);
        graphicsContext.setTextAlign(TextAlignment.LEFT);
        graphicsContext.setFont(Font.loadFont("file:src/resources/fonts/Dark Seed.otf",60));
        graphicsContext.setFill(color);
        graphicsContext.fillText("Score  :  " + scoreFinal,X,400);
    }

    public void update(){
        //On vide l interface graphique à chaque updates pour eviter les superpositions
        graphicsContext.clearRect(0, 0, 800, 800);
        root.setStyle("-fx-background-color: #090112;");

        if(Player.isGamePaused){

            for (var item : Level.gameElements) {
                item.setVisible(false);
            }

            if (!onetime){
                opacityText=0;
                positionX=0;
                onetime = true;
                d=0;
                startIncreasingScore=false;
                scoreTemp =0.0;
            }

            if(positionX<400){
                positionX+=8;
            }
            else{
                positionX=400;
                startIncreasingScore=true;
                d+=0.2;
                if(d<15){
                    moveText+=0.2;
                }
                else if(d>30){
                    moveText=0;
                    d=0;
                }
                else {
                    moveText-=0.2;
                }
            }
            if (opacityText<1){
                opacityText+=0.006;
            }
            if (opacityText>1){
                opacityText=1;
            }
            int positionY=(int) moveText;

            displayText("Niveau  " + (isLevel),400,80,50,"fac22f",opacityText);
            displayText("Pause",800-positionX,300,130,"fac22f",opacityText);
            displayScore(positionX-120,opacityText,startIncreasingScore);
            displayText("Appuyez  sur  ECHAP  pour  reprendre la partie",800-positionX,600+positionY,40,"fac22f",opacityText);
            displayText("ENTREE  pour  relancer  le  niveau  " + isLevel,positionX,700,20,"fac22f",opacityText);
            isGameRunning=false;
        }
        else {
            isGameRunning=true;
            root.setStyle("-fx-background-color: #100120;");
            for (var item : Level.gameElements) {
                item.setVisible(true);
            }
        }

        if (score<0){
            score=0;
        }

        if(isFirstScreen){
            isGameRunning=false;
            isLevel = 1;

            if(positionX<400){
                positionX+=4;
            }
            else{
                d+=0.2;
                if(d<15){
                    moveText+=0.2;
                }
                else if(d>30){
                    moveText=0;
                    d=0;
                }
                else {
                    moveText-=0.2;
                }
            }

            if (opacityText<1){
                opacityText+=0.006;
            }
            if (opacityText>1){
                opacityText=1;
            }

            int positionY=(int) moveText;

            if (!onetime){
                Level.createStars(Level.gameEngine);
                onetime = true;
            }

            displayText("Space  In  ISEP", positionX,370,130,"fac22f",opacityText);
            displayText("ENTREE  pour  lancer  la  partie",800-positionX,700+positionY,40,"fac22f",opacityText);
        }

        if(isGameRunning){
            displayText("Score  :  "+ score,80,40,30,"fac22f",1);
            displayText("Niveau  "+ isLevel,400,40,30,"fac22f",1);
            displayText((Level.player.getHealth()/20>1) ? "Vies  restantes  :  " + Level.player.getHealth()/20 : "Vie  restante  :  " + Level.player.getHealth()/20,670,40,30,"fac22f",1);

            int timerTimeSlowed = 10-(int)((double)BonusTimeSlowed.bonusTime/140);
            int timerDamage = 10-(int)((double)BonusDamage.bonusDamageTime/140);
            int timerScoreX2 = 10-(int)((double)BonusScoreX2.bonusScoreX2Time/140);
            int timerAmmoX2 = 10 - (int)((double)BonusAmmoX2.bonusAmmoX2Time / 140);

            if(BonusTimeSlowed.isTimeSLowed){
                root.setStyle("-fx-background-color: #090112;");
                displayTimerText(timerTimeSlowed);
                displayText("Slow  Mo",400,390,70,"ecf0f1",0.1);
            }

            else if(BonusDamage.isDamageIncreased){
                root.setStyle("-fx-background-color: #1f0405;");
                displayText("Boost  des  Degats",400,390,70,"ecf0f1",0.1);
                displayTimerText(timerDamage);
            }

            else if(BonusScoreX2.isScorex2){
                root.setStyle("-fx-background-color: #211904;");
                displayText("Score  X2",400,390,70,"ecf0f1",0.1);
                displayTimerText(timerScoreX2);
            }

            else if (BonusAmmoX2.isAmmoX2)
            {
                root.setStyle("-fx-background-color: #1c4918");
                displayText("Munitions X3", 400, 390, 70, "ecf0f1", 0.1);
                displayTimerText(timerAmmoX2);
            }

            else{
                root.setStyle("-fx-background-color: #100120;");
            }
        }

        //Cas où le joueur est mort
        if(Level.player.getHealth() <= 0){
            root.setStyle("-fx-background-color: #100120;");
            graphicsContext.clearRect(0, 0, 800, 800);

            isGameRunning=false;

            if(positionX<400){
                positionX+=4;
            }
            else{
                startIncreasingScore=true;
                d+=0.2;
                if(d<15){
                    moveText+=0.2;
                }
                else if(d>30){
                    moveText=0;
                    d=0;
                }
                else {
                    moveText-=0.2;
                }
            }
            if (opacityText<1){
                opacityText+=0.006;
            }
            if (opacityText>1){
                opacityText=1;
            }
            int positionY=(int) moveText;

            displayText("Niveau  " + (isLevel),400,80,50,"fac22f",opacityText);
            displayText("Game  Over  !",800-positionX,300,130,"fac22f",opacityText);
            displayScore(positionX-100,opacityText,startIncreasingScore);
            displayText("Appuyez  sur  ENTREE  pour  relancer  le  niveau  " + isLevel,800-positionX,700+positionY,40,"fac22f",opacityText);            //Suppresion des objects à l'écran

            for (var item : Level.gameElements) {
                removeElementToDisplay(item);
            }
        }

        //Cas Joueur a gagné un niveau
        if(Level.enemyList.size()==0){
            root.setStyle("-fx-background-color: #100120;");
            graphicsContext.clearRect(0, 0, 800, 800);
            isGameRunning=false;

            if (!onetime) {
                nextLevel();

                playVictorySound.seek(Duration.ZERO);
                playVictorySound.play();
                playVictorySound.setVolume(0.2);
                onetime = true;
            }

            if(positionX<400){
                positionX+=4;
            }
            else{
                startIncreasingScore=true;
                d+=0.2;
                if(d<15){
                    moveText+=0.2;
                }
                else if(d>30){
                    moveText=0;
                    d=0;
                }
                else {
                    moveText-=0.2;
                }
            }
            if (opacityText<1){
                opacityText+=0.006;
            }
            if (opacityText>1){
                opacityText=1;
            }

            int positionY=(int) moveText;

            String JSONscore = "";
            Path path = Paths.get("Score/LeaderBoard.txt");
            if(isLevel == 4)
            {
                if (!new File("Score/LeaderBoard.txt").exists()) {
                    try {
                        new File(path.toUri()).createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    JSONscore = Files.readString(path);
                } catch (Exception ex) {
                    System.err.println("Erreur durant la lecture du fichier des scores");
                }
                try {
                    LeaderBoard = g.fromJson(JSONscore, sample.LeaderBoard.class);
                } catch (Exception ex) {
                    try {
                        Files.writeString(path, "{\n" +
                                "  \"Score1\" : 0,\n" +
                                "  \"Score2\" : 0,\n" +
                                "  \"Score3\" : 0,\n" +
                                "  \"Score4\" : 0,\n" +
                                "  \"Score5\" : 0" +
                                "}", Charset.defaultCharset());
                    } catch (Exception ex2) {
                        System.err.println("Erreur dans l'initialisation du fichier Score/LeaderBoard.txt");
                    }
                }
            }


            if (isLevel - 1 == 1 && !nbAmeliorationUpdated) {
                ameliorationPoint = 1;
                nbAmeliorationUpdated = true;
            } else if (isLevel - 1 == 2 && !nbAmeliorationUpdated) {
                ameliorationPoint += 2;
                nbAmeliorationUpdated = true;
            }

            tabScore[isLevel - 2] = score;

            displayText("Niveau  "+(isLevel-1)+"  reussi !",400,80,50,"fac22f",opacityText);

            displayText("Victoire  !",800-positionX,300,130,"fac22f",opacityText);

            displayScore(positionX-140, opacityText, startIncreasingScore);

            if(isLevel - 1 == 1 || isLevel - 1 == 2)
            {
                displayText("Vous avez " + ameliorationPoint + " point d'amelioration\nVous pouvez ameliorer votre vaisseau\n" +
                                "Appuyer sur W pour ameliorer votre vitesse,\nX pour ameliorer votre vie\nC pour ameliorer vos degats",
                            positionX,
                            500, 30, "fac22f", opacityText);
            }

            if(isLevel < 4){
                displayText("Appuyez  sur  ENTREE  pour  passer  au  niveau  "+(isLevel),800-positionX,750+positionY,40,"fac22f",opacityText);
            }
            else {
                if(LeaderBoard == null)
                {
                    try{
                        JSONscore = Files.readString(path);
                    }
                    catch (Exception ex)
                    {
                        System.err.println("Erreur durant la lecture du fichier des scores");
                    }
                    LeaderBoard = g.fromJson(JSONscore, sample.LeaderBoard.class);
                }
                var totalScore = tabScore[0] + tabScore[1] + tabScore[2];

                if (!isLeaderBoardUpdated) { UpdateLeaderBoard(LeaderBoard, totalScore, path); isLeaderBoardUpdated = true; }

                displayText("Score Total : " + totalScore, positionX, 450, 50, "fac22f", opacityText);
                displayText("Tableau des Scores :", positionX, 520, 50,"fac22f", opacityText);
                for (int i = 0; i < 5; i++)
                {
                    displayText(tabNameScore[i] + " : " + LeaderBoard.getScore(i), positionX, 560 + i * 35, 40, "fac22f", opacityText);
                }
                displayText("Appuyez  sur  ENTREE  pour  retourner  au  menu",800-positionX,750+positionY,40,"fac22f",opacityText);
            }

            for (var item : Level.gameElements) {
                removeElementToDisplay(item);
            }
        }
    }
    public static void UpdateAmeliorationPoint()
    {
        ameliorationPoint--;
    }

    public void UpdateLeaderBoard(LeaderBoard leaderBoard, int totalScore, Path path)
    {
        for(int i = 0; i < 5; i ++)
        {
            if(totalScore > leaderBoard.getScore(i))
            {
                if (i != 4)
                {
                    var tempScore = leaderBoard.getScore(i);
                    var tempScore2 = leaderBoard.getScore(i + 1);
                    leaderBoard.setScore(i, totalScore);
                    for(int j = i + 1; j < 5; j += 2)
                    {
                        if(j != 4)
                        {
                            leaderBoard.setScore(j, tempScore);
                            tempScore = leaderBoard.getScore(j + 1);
                            leaderBoard.setScore(j + 1, tempScore2);
                            if(j + 2 != 5)
                            {
                                tempScore2 = leaderBoard.getScore(j + 2);
                            }
                        }
                    }
                }
                else
                {
                    leaderBoard.setScore(i, totalScore);
                }
                break;
            }
        }
        try {
            Files.writeString(path, g.toJson(LeaderBoard), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}