package sample.engine;

import javafx.scene.input.KeyCode;
import sample.GameController;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * this class is responsible for handling all the gameplay mechanics
 * @author Yash Suresh-modified
 */

public class StartMeUp
{
    private static final String m_GAME_NAME = "BestSokobanEverV6";
    private static GameLogger m_logger;
    private static int m_LevelMovesCount = 0;
    private static boolean m_debug = false;
    private static int m_movesCount = 0;
    private static char m_direction;
    private Level m_currentLevel;
    private List<Level> m_levels;
    private String m_mapSetName;
    private boolean m_gameComplete = false;
    private static int m_popUpMovesCount = 0;
    private static boolean m_isStep = false;
    //m_isStep alternates between the step animation by alternating true & false




    //all accessor methods

    /**
     * checks if the game's debug setting is on
     * @return true or false depending on whether debug is active
     */
    public static boolean isDebugActive()
    {
        return m_debug;
    }

    /**
     * getter for total moves upto the current point for the whole mapset
     * @return the total moves count
     */
    public static int getMovesCount()
    {

        return m_movesCount;
    }


    /**
     * getter for total moves in the current level upto that point
     * i have created 2 getters, but removing ones seems to break the
     * program so i have left it int there
     * @return getM_LevelMovesCount(); the other getter function
     */
    public static int getLevelMovesCount()
    {
        return getM_LevelMovesCount();
    }

    /**
     * returns the direction pressed
     * @return the direction int
     */
    public static int getDirection()
    {

        return m_direction;
    }

    /**
     * getter for the name of the Game
     * @return m_GAME_NAME: which is bestSokoban ever
     */
    public static String getGAME_NAME()
    {
        return m_GAME_NAME;
    }

    /**
     * returns the instance of GameLogger
     * @return the instance
     */
    public static GameLogger getLogger()
    {

        return m_logger;
    }


    /**
     * setter for GameLogger
     * @param m_logger  Gamelogger
     */
    public static void setLogger(GameLogger m_logger)
    {

        StartMeUp.m_logger = m_logger;
    }

    /**
     * getter for the moves count for the pop after each level
     * @return moves count for the pop up in game controller
     */
    public static int getpopUpMovesCount()
    {

        return m_popUpMovesCount;
    }


    /**
     * setter for the moves count for the pop-up
     * @param m_popUpMovesCount integer passed
     */
    public static void setPopUpMovesCount(int m_popUpMovesCount)

    {
        StartMeUp.m_popUpMovesCount = m_popUpMovesCount;
    }

    /**
     * duplicate getter.
     * didn't delete or else it would break the program
     * @return moves count for that level
     */
    public static int getM_LevelMovesCount()
    {
        return m_LevelMovesCount;
    }

    /**
     * setter for the moves count for the current level
     * @param m_LevelMovesCount passed integer
     */
    public static void setLevelMovesCount(int m_LevelMovesCount)
    {

        StartMeUp.m_LevelMovesCount = m_LevelMovesCount;
    }

    /**
     * checks if the sprite has stepped or in rest position
     * @return true or false, whether the sprite has 'stepped'
     */
    public static boolean isStep()
    {
        return m_isStep;
    }


    /**
     * sets step for the sprite
     * @param m_isStep passed boolean
     */
    public static void setStep(boolean m_isStep)
    {
        StartMeUp.m_isStep = m_isStep;
    }


    /**
     * setter for the debug boolean
     * @param m_debug boolean passed
     */
    public static void setDebug(boolean m_debug)
    {

        StartMeUp.m_debug = m_debug;
    }

    /**
     * getter for levels
     * @return level for the game
     */
    public List<Level> getLevels()
    {
        return m_levels;
    }


    /**
     * setter for levels
     * @param m_levels the list
     */
    public void setLevels(List<Level> m_levels)
    {

        this.m_levels = m_levels;
    }

    /**
     * sets the direction based on what key the user pressed
     * @param x the character
     */
    public void setDirection(char x)
    {
        m_direction = x;
    }


    /**
     * getter for the map name
     * @return name of the map set
     */
    public String getMapSetName()
    {
        return m_mapSetName;
    }


    /**
     * getter for the current level
     * @return the current level
     */
    public Level getcurrentLevel()
    {
        return m_currentLevel;
    }


    /**
     * setter for the current level
     * @param m_currentLevel the level
     */
    public void setCurrentLevel(Level m_currentLevel)
    {
        this.m_currentLevel = m_currentLevel;
    }


    /**
     * getter for Current Level
     * @return the function which returns the m_currentLevel
     */
    public Level getCurrentLevel()
    {
        return getcurrentLevel();
    }

    /**
     * checks if map set is complete
     * @return true if the game is complete
     */
    public boolean isGameComplete() {
        return m_gameComplete;
    }


    /**
     * constructor which loads up the map using a .skb file
     * @param input the input file
     * @param production used for the music player
     */
    public StartMeUp(InputStream input, boolean production)
    {
        try {

            setLogger(new GameLogger());
            setLevels(loadGameFile(input));

            if (!GameController.isResetSwitch())
            {
                setCurrentLevel(getNextLevel());
            } else
                {
                GameController.setResetSwitch(false);
                setCurrentLevel(getLevels().get(GameController.getLevelCount() - 1));

            }


            if (production) {
                createPlayer();
            }
        } catch (IOException x) {
            System.out.println("Cannot create logger.");
        } catch (NoSuchElementException e) {
            getLogger().warning("Cannot load the default save file: "
                    + Arrays.toString(e.getStackTrace()));
        } catch (LineUnavailableException e) {
            getLogger().warning("Cannot load the music file: "
                    + Arrays.toString(e.getStackTrace()));
        }
    }

    //declaring all the accessors on top:




    /**
     * this function designs the mechanics behind the players 2-d movements
     * @param code this is basically one of the 4 directional buttons input by the user
     *
     */
    public void handleKey(KeyCode code) throws IOException
    {

        switch (code)
        {

            case UP:
                setDirection('u');
                StepChecker();
                move(new Point(-1, 0));
                break;

            case RIGHT:
                setDirection('r');
                StepChecker();
                move(new Point(0, 1));
                break;

            case DOWN:
                setDirection('d');
                StepChecker();
                move(new Point(1, 0));
                break;

            case LEFT:
                setDirection('l');
                StepChecker();
                move(new Point(0, -1));
                break;

            default:
                // TODO: implement something funny.
        }

        if (isDebugActive())
        {
            System.out.println(code);
        }
    }

    /**
     * checks if the sprite is in rested or 'stepped' state
     */
    public void StepChecker()//this function alternates between the steps
    {
        if(isStep())
        {
            setStep(false);
        }
        else if(!isStep())
        {
            setStep(true);
        }
    }


    /**
     * the function behind the movement mechanics
     * @param delta the path to move by
     */
    public void move(Point delta) {
        if (isGameComplete())
        {
            return;
        }

        Point keeperPosition = getcurrentLevel().getKeeperPosition();
        GameObject keeper = getcurrentLevel().getObjectAt(keeperPosition);
        Point targetObjectPoint = GameGrid.translatePoint(keeperPosition, delta);
        GameObject keeperTarget = getcurrentLevel().getObjectAt(targetObjectPoint);

        if (StartMeUp.isDebugActive())
        {
            System.out.println("Current level state:");
            System.out.println(getcurrentLevel().toString());
            System.out.println("Keeper pos: " + keeperPosition);
            System.out.println("Movement source obj: " + keeper);
            System.out.printf("Target object: %s at [%s]", keeperTarget, targetObjectPoint);
        }

        boolean keeperMoved = false;

        switch (keeperTarget)
        {

            case WALL:
                break;

            case CRATE:

                GameObject crateTarget = getcurrentLevel().getTargetObject(targetObjectPoint, delta);
                if (crateTarget != GameObject.FLOOR)
                {
                    break;
                }

                getcurrentLevel().moveGameObjectBy(keeperTarget, targetObjectPoint, delta);
                getcurrentLevel().moveGameObjectBy(keeper, keeperPosition, delta);
                keeperMoved = true;
                break;

            case FLOOR:
                getcurrentLevel().moveGameObjectBy(keeper, keeperPosition, delta);
                keeperMoved = true;
                break;

            default:
                getLogger().severe("The object to be moved was not a recognised GameObject.");
                throw new AssertionError("This should not have happened. Report this problem to the developer.");
        }

        if (keeperMoved)
        {
            keeperPosition.translate((int) delta.getX(), (int) delta.getY());
            m_movesCount++;
            setLevelMovesCount(getM_LevelMovesCount() + 1);


            if (getcurrentLevel().IsComplete())
            {


                if (isDebugActive())
                {
                    System.out.println("Level complete!");

                }

                setCurrentLevel(getNextLevel());
            }
        }
    }

    /**
     * this function is used to load up the map using the .skb f
     * @param input the .skb file
     * @return the level
     */
    public List<Level> loadGameFile(InputStream input) {
        List<Level> levels = new ArrayList<>(5);
        int levelIndex = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            boolean parsedFirstLevel = false;
            List<String> rawLevel = new ArrayList<>();
            String levelName = "";

            while (true) {
                String line = reader.readLine();

                // Break the loop if EOF is reached
                if (line == null) {
                    if (rawLevel.size() != 0) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                    }
                    break;
                }

                if (line.contains("MapSetName")) {
                    m_mapSetName = line.replace("MapSetName: ", "");
                    continue;
                }

                if (line.contains("LevelName")) {
                    if (parsedFirstLevel) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                        rawLevel.clear();
                    } else {
                        parsedFirstLevel = true;
                    }

                    levelName = line.replace("LevelName: ", "");
                    continue;
                }

                line = line.trim();
                line = line.toUpperCase();
                // If the line contains at least 2 WALLS, add it to the list
                if (line.matches(".*W.*W.*")) {
                    rawLevel.add(line);
                }
            }

        } catch (IOException e) {
            getLogger().severe("Error trying to load the game file: " + e);
        } catch (NullPointerException e) {
            getLogger().severe("Cannot open the requested file: " + e);
        }

        return levels;
    }


    /**
     * creates the music player but unused
     * @throws LineUnavailableException if line cannot be opened if not present
     */
    @Deprecated
    public void createPlayer() throws LineUnavailableException
    {
//        File filePath = new File(getClass().getClassLoader().getResource("music/puzzle_theme.wav").toString());
//        Media music = new Media(filePath.toURI().toString());
//        player = new MediaPlayer(music);
//        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
    } //

    /**
     * gets the next level from the list of levels
     * @return the next level
     */
    public Level getNextLevel()
    {

        if (getcurrentLevel() == null) {
            return getLevels().get(0);
        }


        int currentLevelIndex = getcurrentLevel().getIndex();

        if (currentLevelIndex < getLevels().size()) {
            return getLevels().get(currentLevelIndex);
        }

        m_gameComplete = true;
        return null;
    }


    /**
     * toggles the debug function
     */
    public void toggleDebug()
    {
        setDebug(!m_debug);
    }
}