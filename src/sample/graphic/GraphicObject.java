package sample.graphic;


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.GameController;
import sample.Menu2Controller;
import sample.Menu3Controller;
import sample.engine.GameObject;
import sample.engine.StartMeUp;

/**
 * this class creates an instance of the visual
 * representation of each object on the grid
 * @author Yash Suresh-modified
 */
public class GraphicObject extends Rectangle
{
    private Timeline m_timeline;
    private int m_seconds = 0;

    /**
     * getter for the seconds for the sprite timer
     * @return m_seconds the time in seconds
     */
    public int getSeconds()
    {
        return m_seconds;
    }

    /**
     * setter for the seconds for sprite timer
     * @param m_seconds seconds for timer
     */
    public void setSeconds(int m_seconds)
    {
        this.m_seconds = m_seconds;
    }


    /**
     * creating a series of cases based on what the object is
     * @param obj this is an instance of
     *            the object which can be a wall, keeper etc
     */
    public GraphicObject(GameObject obj)
    {
        Paint color;
        switch (obj)
        {
            case WALL:
                caseWall();
                break;

            case CRATE:
                caseCrate();
                break;

            case EMPTY:
                color = Color.CORNSILK;
                this.setFill(color);
                break;


            case DIAMOND:
                this.setFill(new ImagePattern(SpriteStore.getDest()));
                if (StartMeUp.isDebugActive()) {
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.2);
                    ft.setCycleCount(Timeline.INDEFINITE);
                    ft.setAutoReverse(true);
                    ft.play();
                }
                break;

            case KEEPER:
                caseKeeper();
                break;

            case FLOOR:
                color = Color.WHITE;
                this.setFill(color);
                break;

            case CRATE_ON_DIAMOND:
                this.setFill(new ImagePattern(SpriteStore.getTick()));
                break;

            default:
                String message = "Error in Level constructor. Object not recognized.";
                StartMeUp.getLogger().severe(message);
                throw new AssertionError(message);
        }


        this.setHeight(30);
        this.setWidth(30);

        if (obj == GameObject.KEEPER)
        {
            this.setArcHeight(50);
            this.setArcWidth(50); //this will make the individual grid things circular
        }

        if (StartMeUp.isDebugActive())
        {
            this.setStroke(Color.RED);
            this.setStrokeWidth(0.25);
        }
    }

    /**
     * times whether the keeper has been stood in the same position
     * for a while to decide whether to use the default resting sprite
     */
    public void spriteTimer()
    {
        if(getSeconds() <1)
        {
            setSeconds(getSeconds() + 1);
        }
        else
        {
            m_timeline.stop();
            if(StartMeUp.getDirection() == 'l')
            {
                this.setFill(new ImagePattern(SpriteStore.getLeftKeeper()));
            }
            if(StartMeUp.getDirection() == 'r')
            {
                this.setFill(new ImagePattern(SpriteStore.getRightKeeper()));
            }

        }


    }

    /**
     * broke down the GraphicObject constructor for wall object
     */
    public void caseWall()
    {

        if (Menu2Controller.getColourChoice() == 1) {
            this.setFill(new ImagePattern(SpriteStore.getGrey()));
        }
        if (Menu2Controller.getColourChoice() == 2) {
            this.setFill(new ImagePattern(SpriteStore.getBeige()));
        }
        if (Menu2Controller.getColourChoice() == 3) {
            this.setFill(new ImagePattern(SpriteStore.getBrown()));
        }
        if (Menu2Controller.getColourChoice() == 4) {
            this.setFill(new ImagePattern(SpriteStore.getBlack()));
        }

    }


    /**
     * broke down the GraphicObject constructor for crate object
     */
    public void caseCrate()
    {
        if(Menu3Controller.getColourChoice() == 1)
        {
            this.setFill(new ImagePattern(SpriteStore.getRedCrate()));
        }
        if(Menu3Controller.getColourChoice() == 2)
        {
            this.setFill(new ImagePattern(SpriteStore.getYellowCrate()));
        }
        if(Menu3Controller.getColourChoice() == 3)
        {
            this.setFill(new ImagePattern(SpriteStore.getBlueCrate()));
        }
        if(Menu3Controller.getColourChoice() == 4)
        {
            this.setFill(new ImagePattern(SpriteStore.getBlackCrate()));
        }

    }


    /**
     * broke down the GraphicObject constructor for keeper object
     */
    public void caseKeeper()
    {
        if (StartMeUp.getDirection() == 'u')
        {
            if(StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getUpLeftKeeper()));
            }
            if(!StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getUpRightKeeper()));
            }
        }
        else if (StartMeUp.getDirection() == 'd')
        {
            if(StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getDownLeftKeeper()));
            }
            if(!StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getDownRightKeeper()));
            }
        } else if (StartMeUp.getDirection() == 'l')
        {


            setSeconds(0);
            m_timeline = new Timeline(new KeyFrame(Duration.seconds(1), //the timer will
                    // contd .... update every one second;
                    e -> spriteTimer()));
            m_timeline.setCycleCount(Timeline.INDEFINITE);

            if(StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getLeftKeeper()));
            }
            if(!StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getLeftStep()));
            }
            m_timeline.play();



        }
        else if (StartMeUp.getDirection() == 'r')
        {
            setSeconds(0);
            m_timeline = new Timeline(new KeyFrame(Duration.seconds(1), //the timer will
                    // contd .... update every one second;
                    e -> spriteTimer()));
            m_timeline.setCycleCount(Timeline.INDEFINITE);

            if(StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getRightKeeper()));
            }
            if(!StartMeUp.isStep())
            {
                this.setFill(new ImagePattern(SpriteStore.getRightStep()));
            }
            m_timeline.play();
        }
        else
        {
            this.setFill(new ImagePattern(SpriteStore.getDefaultKeeper()));
        }
        if(GameController.isSpriteDefault())
        {
            this.setFill(new ImagePattern(SpriteStore.getDefaultKeeper()));
            GameController.setIsSpriteDefault(false);
        }

    }


}
