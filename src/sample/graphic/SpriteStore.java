package sample.graphic;

import javafx.scene.image.Image;

/**
 * this class creates instances of all the sprite images used in
 * the game
 * this is an example of FactoryPattern
 * @author Yash Suresh
 */
public class SpriteStore
{

    //using setters and getters for the images

    /**
     * getter for brown wall
     * @return brown
     */
    public static Image getBrown()
    {

        return brown;
    }
    /**
     * getter for black wall
     * @return black
     */
    public static Image getBlack()
    {

        return black;
    }

    /**
     * getter for beige wall
     * @return beige
     */
    public static Image getBeige()
    {

        return beige;
    }

    /**
     * getter for grey wall
     * @return grey
     */
    public static Image getGrey()
    {

        return grey;
    }

    /**
     * getter for red crate image
     * @return redCrate
     */
    public static Image getRedCrate()
    {

        return redCrate;
    }


    /**
     * getter for yellow crate image
     * @return yellowCrate
     */
    public static Image getYellowCrate()
    {

        return yellowCrate;
    }

    /**
     * getter for blue crate image
     * @return blueCrate
     */
    public static Image getBlueCrate()
    {

        return blueCrate;
    }

    /**
     * getter for black crate image
     * @return blackCrate
     */
    public static Image getBlackCrate()
    {

        return blackCrate;
    }

    /**
     * getter for destination image
     * @return dest
     */
    public static Image getDest()
    {

        return dest;
    }

    /**
     * getter for tick image
     * @return tick
     */
    public static Image getTick()
    {

        return tick;
    }

    /**
     * @return upLetKeeper
     */
    public static Image getUpLeftKeeper()
    {

        return upLeftKeeper;
    }

    /**
     *
     * @return downLeftKeeper
     */
    public static Image getDownLeftKeeper()
    {

        return downLeftKeeper;
    }

    /**
     *
     * @return getDownRightKeeper
     */
    public static Image getDownRightKeeper()
    {

        return downRightKeeper;
    }

    /**
     *
     * @return LeftKeeper
     */
    public static Image getLeftKeeper()
    {

        return leftKeeper;
    }

    /**
     *
     * @return leftStep
     */
    public static Image getLeftStep()
    {

        return leftStep;
    }

    /**
     *
     * @return rightStep
     */
    public static Image getRightStep()
    {

        return rightStep;
    }

    /**
     *
     * @return rightKeeper
     */
    public static Image getRightKeeper()
    {

        return rightKeeper;
    }

    /**
     *
     * @return defaultKeeper
     */
    public static Image getDefaultKeeper()
    {

        return defaultKeeper;
    }

    /**
     *
     * @return upRightKeeper
     */
    public static Image getUpRightKeeper()
    {

        return upRightKeeper;
    }

    /**
     *
     * @return stepsImage
     */
    public static Image getStepsImage()
    {

        return stepsImage;
    }

    /**
     *
     * @return levels
     */
    public static Image getLevels() {

        return levels;
    }

    /**
     *
     * @return stopwatch
     */
    public static Image getStopwatch()
    {

        return stopwatch;
    }




    private static final Image brown = new Image("brownWall.png");
    private static final Image black = new Image("blackWall.png");
    private static final Image beige = new Image("beigeWall.png");
    private static final Image grey = new Image("greyWall.png");

    private static final Image redCrate = new Image("redCrate.png");
    private static final Image yellowCrate = new Image("yellowCrate.png");
    private static final Image blueCrate = new Image("blueCrate.png");
    private static final Image blackCrate = new Image("blackCrate.png");

    private static final Image dest = new Image("Destination.png");
    private static final Image tick = new Image("tick.png");
    private static final Image upLeftKeeper = new Image("upLeftStepKeeper.png");
    private static final Image upRightKeeper = new Image("upRightStepKeeper.png");
    private static final Image downLeftKeeper = new Image("downLeftStepKeeper.png");
    private static final Image downRightKeeper = new Image("downRightStepKeeper.png");
    private static final Image leftKeeper = new Image("leftKeeper.png");
    private static final Image leftStep = new Image("leftStepKeeper.png");
    private static final Image rightStep = new Image("rightStepKeeper.png");
    private static final Image rightKeeper = new Image("rightKeeper.png");
    private static final Image defaultKeeper = new Image("downKeeper.png");

    private static final Image stepsImage = new Image("stepsImage.png");
    private static final Image levels = new Image("Levels.png");
    private static final Image stopwatch = new Image("stopwatch.png");
    private static final Image trophy = new Image("trophy.png");


}
