package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * controller class for menu3 fxml
 * @author Yash Suresh
 */
public class Menu3Controller
{

    private static int m_colourChoice = 0;
    private Stage primaryStage = null;

    /**
     * setter for primary stage
     * @param primaryStage setting the stage for the scene change
     */
    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }


    /**
     * getter for crate colour choice
     * @return m_colourChoice which stores the choice of colour
     */
    public static int getColourChoice()
    {
        return m_colourChoice;
    }

    /**
     * setter for colour choice
     * @param a colour choice
     */
    public void setColourChoice(int a)
    {

        m_colourChoice = a;
    }


    /**
     * if button 1 was clicked
     * @throws Exception if error loading gamecontroller fxml
     */
    @FXML
    void startGame1() throws Exception
    {
        setColourChoice(1);
        GameController startGame = new GameController();
        startGame.start(primaryStage);
    }

    /**
     * if button 2 was clicked
     * @throws Exception if error loading gamecontroller fxml
     */
    @FXML
    void startGame2() throws Exception
    {
        setColourChoice(2);
        GameController startGame = new GameController();
        startGame.start(primaryStage);
    }

    /**
     * if button 3 was clicked
     * @throws Exception if error loading gamecontroller fxml
     */
    @FXML
    void startGame3() throws Exception
    {
        setColourChoice(3);
        GameController startGame = new GameController();
        startGame.start(primaryStage);
    }

    /**
     * if button 4 was clicked
     * @throws Exception if error loading gamecontroller fxml
     */
    @FXML
    void startGame4() throws Exception
    {
        setColourChoice(4);
        GameController startGame = new GameController();
        startGame.start(primaryStage);
    }

}
