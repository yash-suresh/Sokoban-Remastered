package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.IO.writer;
import sample.engine.GameObject;
import sample.engine.Level;
import sample.engine.StartMeUp;
import sample.graphic.GraphicObject;
import sample.graphic.SpriteStore;
import sample.utilities.SoundFX;

import java.awt.*;
import java.io.*;


/**
 * the controller for the actions of the player in the game
 * @author Yash Suresh/School of Computer Science/University of Nottingham
 */

public class GameController extends Application //Application
{
    private static int m_LevelCount = 1; //this keeps count of current level
    private static boolean m_resetSwitch = false; //this variable essentially
    // contd...specifies if the reset function is being used
    private static final Text m_stepCount = new Text();//for the display
    private static final Text m_LevelDisplay = new Text();//for the display
    private static boolean isSpriteDefault = false;
    private Stage m_primaryStage;
    private StartMeUp m_gameEngine;// 'StartMeUp' is a class we created
    private GridPane m_gameGrid;//  'GridPane' forms a grid-layout
    private File m_saveFile; // 'File' maintains pathfiles
    private MenuBar m_MENU; //'MenuBar is the class which allows us to create a menu
    private boolean m_isLoad = false;
    private static boolean m_LevelComplete = false;
    private final HBox m_Panel = new HBox();
    private Timeline m_timeline;
    private int TIME_INTERVAL = 300;
    private final Label m_timeLabel = new Label("" + TIME_INTERVAL);
    private final ImageView m_steps = new ImageView(SpriteStore.getStepsImage());
    private final ImageView m_levels = new ImageView(SpriteStore.getLevels());
    private final ImageView m_stopwatch = new ImageView(SpriteStore.getStopwatch());
    
    //Declaring all the accessors at the top:
    //all accessor methods are public


    /**
     * getter for level number
     * @return m_LevelCount current level number
     */
    public static int getLevelCount()
    {
        return m_LevelCount;
    }

    /**
     * setter for level number
     * @param levelCount current level
     */
    public static void setLevelCount(int levelCount)
    {
        m_LevelCount = levelCount;
    }

    /**
     * getter tells us if the resetSwitch (if player is resetting the level)
     * is true or false
     * @return m_resetSwitch states whether level is being resetted or not
     */
    public static boolean isResetSwitch()
    {
        return m_resetSwitch;
    }

    /**
     * setter for resetSwitch
     * @param resetSwitch whether the user is resetting or not
     */
    public static void setResetSwitch(boolean resetSwitch)
    {

        GameController.m_resetSwitch = resetSwitch;
    }


    /**
     * getter for the boolean which tells which level is complete or not
     * @return isM_LevelComplete() this calls another function
     */
    public static boolean islevelComplete()
    {
        return isLevelComplete();
    }


    /**
     * sets the boolean value depending on whether the level is complete
     * @param m_LevelComplete if the level is complete or not
     */
    public static void setLevelComplete(boolean m_LevelComplete)
    {
        GameController.setM_LevelComplete(m_LevelComplete);
    }


    /**
     * setter for whether the level is complete
     * @return m_LevelComplete level complete or not
     */
    public static boolean isLevelComplete()
    {

        return m_LevelComplete;
    }

    /**
     * setter for the level complete
     * @param m_LevelComplete if level complete or not
     */
    public static void setM_LevelComplete(boolean m_LevelComplete)
    {
        GameController.m_LevelComplete = m_LevelComplete;
    }

    /**
     * getter for if the default sprite is activated or not
     * @return true or false depending on whether the default sprite is being used
     */
    public static boolean isSpriteDefault()
    {

        return isSpriteDefault;
    }

    /**
     * setter for setting to default sprite, when game is reset, or level changed
     * @param isSpriteDefault stores the boolean
     */
    public static void setIsSpriteDefault(boolean isSpriteDefault)

    {
        GameController.isSpriteDefault = isSpriteDefault;
    }


    /**
     * getter for whether a separate file is being loaded or not
     * @return true if load file is being called
     */
    public boolean isLoad()
    {
        return m_isLoad;
    }

    /**
     * setter if the load file function is being used
     * @param m_isLoad if load is being used
     */
    public void setLoad(boolean m_isLoad)
    {
        this.m_isLoad = m_isLoad;
    }


    /**
     * this function updates the countdown timer
     * and return the time left, or stop game
     * @return the amount of time left
     */
    private int timer()
    {
        if (TIME_INTERVAL > 0)
        {
            TIME_INTERVAL--; //the timer will go down.
        } else if (TIME_INTERVAL <= 0)
        {
            TIME_INTERVAL = 0;
            m_timeline.stop(); //the countdown stops
            System.out.println("You Lost: You were on Level " + getLevelCount() +
                    ", before the time ran out!! ");
            showLossMessage();
        }
        else if (m_gameEngine.isGameComplete())
        {
            m_timeline.stop();//timer will stop if game finishes before it runs down.
        }
        return TIME_INTERVAL;
    }


    /**
     * updates the display panel based on moves and levels.
     */
    private static void updateDisplayPanel() //yash
    {
        m_LevelDisplay.setText(" LEVEL: " + getLevelCount() + "  ");
        m_stepCount.setText(" STEPS: " + StartMeUp.getLevelMovesCount() + "  ");
    }

    /**
     * sets up the in-game menu bar
     */
    @FXML
    private void menuBarSetUP()
    {
        m_MENU = new MenuBar();
        MenuItem menuItemLoadGame = new MenuItem("Load Game");
        menuItemLoadGame.setOnAction(actionEvent ->
        { try
            { loadGame(); }
            catch (IOException e)
            { e.printStackTrace();
            }
        });
        MenuItem menuItemExit = new MenuItem("Exit");
        menuItemExit.setOnAction(actionEvent -> closeGame());
        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(menuItemLoadGame,
                new SeparatorMenuItem(), menuItemExit);
        RadioMenuItem radioMenuItemMusic = new RadioMenuItem("Toggle Music");
        radioMenuItemMusic.setOnAction(actionEvent -> toggleMusic());
        RadioMenuItem radioMenuItemDebug = new RadioMenuItem("Toggle Debug");
        radioMenuItemDebug.setOnAction(actionEvent -> {
            try {
                toggleDebug();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        MenuItem menuItemResetLevel = new MenuItem("Reset Level");
        menuItemResetLevel.setOnAction(actionEvent ->
        {
            try { resetLevel();
            } catch (IOException e)
            { e.printStackTrace();
            }
        });
        menuItemResetLevel.setAccelerator(KeyCombination.valueOf("Space"));
        //Addition 1: Space-Bar Shortcut

        Menu menuLevel = new Menu("Level");
        menuLevel.getItems().addAll(radioMenuItemMusic, radioMenuItemDebug,
                new SeparatorMenuItem(), menuItemResetLevel);
        MenuItem menuItemGame = new MenuItem("About This Game");
        Menu menuAbout = new Menu("About");
        menuAbout.setOnAction(actionEvent -> showAbout());
        menuAbout.getItems().addAll(menuItemGame);
        m_MENU.getMenus().addAll(menuFile, menuLevel, menuAbout);
    }

    /**
     * sets up the in game display panel
     */
    private void panelSetUp()
    {

        m_timeline = new Timeline(new KeyFrame(Duration.seconds(1), //the timer will
                // contd .... update every one second;
                e -> m_timeLabel.setText(" " + timer())));
        //we get the timer count from timer() function, but the program stops after 0;

        m_timeline.setCycleCount(Timeline.INDEFINITE);
        //will happen perpetually until stopped;

        m_LevelDisplay.setText(" LEVEL: " + getLevelCount() + "  ");
        m_stepCount.setText(" STEPS: " + StartMeUp.getLevelMovesCount() + "  ");

        m_Panel.getChildren().add(m_levels);
        m_Panel.getChildren().add(m_LevelDisplay);
        m_Panel.getChildren().add(m_steps);
        m_Panel.getChildren().add(m_stepCount);
        m_Panel.getChildren().add(m_stopwatch);
        m_Panel.getChildren().add(m_timeLabel);

        m_Panel.setAlignment(Pos.CENTER);
        m_Panel.getStylesheets().add("style.css");
        //customising the panel using css;
        m_Panel.getStyleClass().add("Panel");
        //linking it;

    }

    /**
     * @param primaryStage which is the root 'Stage' - object.
     * @throws Exception if error loading the default save game
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        menuBarSetUP();
        panelSetUp();
        //calling the two functions to set up the panel
        m_gameGrid = new GridPane(); //creating a grid for the game
        GridPane root = new GridPane();// creating a root grid
        // I'm keeping it as root not m_root, because of convention;

        root.add(m_MENU, 0, 0);
        root.add(m_Panel, 0, 1);
        root.add(m_gameGrid, 0, 2);

        primaryStage.setTitle(StartMeUp.getGAME_NAME());
        primaryStage.setScene(new Scene(root));//add opening scene here
        SoundFX sfx = SoundFX.getInstance(); //getting instance of singleton class;
        sfx.Play(); //playing the music
        m_timeline.play(); //staring the timer
        loadDefaultSaveFile(primaryStage);
        primaryStage.show();
    }


    /**
     * this method initialises the board.
     * @param primaryStage which is the application window passed from 'start()'
     * @throws IOException if error loading save file
     */
    private void loadDefaultSaveFile(Stage primaryStage) throws IOException
    {
        this.m_primaryStage = primaryStage;
        System.out.println("Hi");
        InputStream in = getClass().getClassLoader().getResourceAsStream
                ("yashLevel.skb");
        System.out.println(in);
        initializeGame(in);
        System.out.println("Hi");
        setEventFilter();
        System.out.println("Hi");
        System.out.println(Menu2Controller.getName());
    }


    /**
     * function for loading up the default save file
     * @param input this is the file name '.skb' for starting up the game.
     * @throws IOException if error loading up file
     */
    private void initializeGame(InputStream input) throws IOException
    {
        m_gameGrid.setStyle("-fx-background-color: WHITE;");
        m_gameEngine = new StartMeUp(input, true);
        reloadGrid();
    }

    /**
     * function listens for the user pressing a key
     */
    private void setEventFilter()
    {
        m_primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event ->
                {
                    try {
                        m_gameEngine.handleKey(event.getCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    updateDisplayPanel();//yash
                    try {
                        reloadGrid();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }


    /**
     * function loads a different game file from default
     * @throws IOException if error loading file
     */
    private void loadGameFile() throws IOException
    {
        setLoad(true);
        /*this means that the load function is being used by player
        this helps us in the resetLevel function*/

        setLevelCount(1); //we are assuming that the load game file begins at level 1.

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("Sokoban save file",
                        "*.skb"));
        m_saveFile = fileChooser.showOpenDialog(m_primaryStage);

        if (m_saveFile != null)
        {
            if (StartMeUp.isDebugActive())
            {
                StartMeUp.getLogger().info("Loading save file: " + m_saveFile.getName());
            }

            initializeGame(new FileInputStream(m_saveFile));
        }
    }

    /**
     * function reloads grid after each move
     * @throws IOException if error loading file
     */
    private void reloadGrid() throws IOException
    {
        //once game is complete, and victory message shown, no further action is taken
        if (m_gameEngine.isGameComplete())
        {
            fileWriter();
            showVictoryMessage();
            return;
        }
        if(islevelComplete())
        {
            fileWriter();
            setLevelComplete(false);
            /*after every level is finished, we reset the boolean to false,when the
            new level starts so that the pop-up can be shown after each level*/
            setIsSpriteDefault(true);//the default sprite position
            popUp(); //calling the popup after the level is completed
        }
        Level currentLevel = m_gameEngine.getCurrentLevel();
        Level.LevelIterator levelGridIterator =
                (Level.LevelIterator) currentLevel.iterator();
        m_gameGrid.getChildren().clear();
        while (levelGridIterator.hasNext())
        {
            addObjectToGrid(levelGridIterator.next(),
                    levelGridIterator.GetCurrentPosition());
        }
        m_gameGrid.autosize();
        m_primaryStage.sizeToScene();
    }

    /**
     * this functions resets the level from the current mapset or different on
     * @throws IOException if the file not loading
     */
    public void resetLevel() throws IOException
    {
        StartMeUp.setLevelMovesCount(0);
        //setting the moves count of the level to 0
        setIsSpriteDefault(true);
        setResetSwitch(true);
            /*the concept is simple: if the reset level function is being called..
            this boolean tells the control flow to reload the same level.
            */

        if (!isLoad())
        {
            InputStream in = getClass().getClassLoader().getResourceAsStream
                    ("yashLevel.skb");


            initializeGame(in);
        }
        else if (isLoad()) //if the user is loading a game in
        {
            initializeGame(new FileInputStream(m_saveFile)); //the new map is initialised
        }

        m_stepCount.setText(" STEPS: " + 0 + "  ");
        //the step count is set to 0, the moment the function is called.

    }

    /**
     * victory message pop up if game completed before time runs out
     */
    private void showVictoryMessage()
    {
        String dialogTitle = "Game Over: Success!";
        String dialogMessage = "You completed " + m_gameEngine.getMapSetName()
                + " in " + StartMeUp.getMovesCount() + " moves!";
        //telling the player the total number of moves in the whole game

        MotionBlur mb = new MotionBlur(2, 3);
        Button ok = new Button("Go To Leaderboard");
        ok.setOnAction(actionEvent -> leaderBoard());
        newDialog(dialogTitle, dialogMessage, mb, ok);

    }

    /**
     * loss message pop up if the game is not completed in time
     */
    private void showLossMessage()
    {
        String dialogTitle = "You Lost";
        String dialogMessage = "You were on level " + getLevelCount() +
                " , before time ran out!!";
        MotionBlur mb = new MotionBlur(2, 3);
        Button ok = new Button("OK");
        ok.setOnAction(actionEvent -> closeGame());
        //closes the game the instance the player closes the dialog box

        newDialog(dialogTitle, dialogMessage, mb, ok);
    }

    /**
     * creates a popup after every level giving the move count, best score and name
     */
    private void popUp()
    {

            String dialogTitle = "Level Complete";
            String dialogMessage = "Name: " + Menu2Controller.getName() + "\n" +
                    "Level: " + (getLevelCount() -1) + "\n" + "Moves: " +
                    StartMeUp.getpopUpMovesCount() + "\n" +
                    "Best Score for this level: " + writer.getScoresList().get(0);
            /*since the level count shows the current level, and the pop up is
            shown after the level is finished, we use (LevelCount-1) to refer
            to the moves in the previous level */

        Button lb = new Button("Go To Leaderboard");
        MotionBlur mb = new MotionBlur(2, 3);
        lb.setOnAction(actionEvent -> leaderBoard());
            newDialog(dialogTitle, dialogMessage, mb, lb);

    }


    /**
     * creates a dialog template for each pop up
     * @param dialogTitle what the pop is titled
     * @param dialogMessage the message
     * @param dialogMessageEffect graphic effects
     * @param button the button for the player action
     */
    private void newDialog(String dialogTitle, String dialogMessage,
                          Effect dialogMessageEffect, Button button)
    {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(m_primaryStage);
        dialog.setResizable(false);
        dialog.setTitle(dialogTitle);

        Text text1 = new Text(dialogMessage);
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setFont(javafx.scene.text.Font.font(14));
        if (dialogMessageEffect != null)
        {
            text1.setEffect(dialogMessageEffect);
        }
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setBackground(Background.EMPTY);
        dialogVbox.getChildren().add(text1);
        if (button != null)
        {
            dialogVbox.getChildren().add(button);
        }

        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        dialog.setScene(dialogScene);
        dialog.show();
    }


    /**
     * adds the object to the game grid
     * @param gameObject type of GameObject like crate, wall etc
     * @param location the location where it is
     */
    private void addObjectToGrid(GameObject gameObject, Point location)
    {
        GraphicObject graphicObject = new GraphicObject(gameObject);
        m_gameGrid.add(graphicObject, location.y, location.x);
    }

    /**
     * exits the game
     */
    private void closeGame()
    {
        System.exit(0);
    }


    /**
     * load in a separate .skb file
     * @throws IOException if error loading file
     */
    private void loadGame() throws IOException
    {
        try
        {
            loadGameFile();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * pop us telling us game rules
     */
    private void showAbout()
    {
        //giving the player a breakdown of rules;
        String title = "About This Game";
        String message = "1) Cannot pull crate \n 2) Can only push one crate\n";
        newDialog(title, message, null, null);
    }

    /*depending on the boolean value provided,
     this function alternates between music 'on' and 'off'*/

    /**
     * calls singleton class for toggling music
     */
    private void toggleMusic()
    {
        SoundFX sfx = SoundFX.getInstance();
        sfx.Toggle(); //calling the function in the class
    }

    /**
     * toggling the debug function to see the game as a grid
     * @throws IOException
     */
    private void toggleDebug() throws IOException
    {
        m_gameEngine.toggleDebug();
        reloadGrid();
    }

    /**
     * leaderboard pop up for best scores in each level
     */
    private void leaderBoard()
    {

        String dialogTitle = "LEADERBOARD : level " + (getLevelCount()-1);
        String dialogMessage =
                "Best Score, Player Name" + "\n"+
                        "1:  " + writer.getScoresList().get(0) + "\n" +
                        "2:  " + writer.getScoresList().get(1) + "\n" +
                        "3:  " + writer.getScoresList().get(2) + "\n" +
                        "3:  " + writer.getScoresList().get(3) + "\n" +
                        "4:  " + writer.getScoresList().get(4) + "\n" +
                        "5:  " + writer.getScoresList().get(5) + "\n";


        MotionBlur mb = new MotionBlur(2, 3);

        //closes the game the instance the player closes the dialog box

        newDialog(dialogTitle, dialogMessage, mb, null);
    }

    /**
     * calls the singleton class which writes and reads
     * bestscores to and from separate txt files
     * @throws FileNotFoundException if error loading file
     */
    private void fileWriter() throws FileNotFoundException
    {
        writer w = writer.getInstance();
        writer.main(StartMeUp.getMovesCount());
        writer.read();
    }

}

