package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * this is the controller class for Menu2 fxml
 * @author Yash Suresh
 */
public class Menu2Controller
{
    private static String m_playerName; //player name
    private static int m_colourChoice = 0;
    private Stage primaryStage = null;


    @FXML
    private TextField nameField;


    /**
     * Class Constructor
     * @throws IOException if error occurs during loading menu3 fxml
     */
    public Menu2Controller() throws IOException
    {}

    /**
     * getter for player name
     * @return m_playerName : String which stores player name
     */
    public static String getName()
    {
        return m_playerName;
    }

    /**
     * getter for wall colour choice
     * @return m_colourChoice : stores wall colour choice
     */
    public static int getColourChoice()
    {

        return m_colourChoice;
    }

    /**
     * setter for player name
     * @param name : player name
     */
    public static void setName(String name)
    {
        Menu2Controller.m_playerName = name;
    }

    /**
     * setter for wall colour choice
     * @param a : wall colour choice
     */
    public void setColourChoice(int a)
    {

        m_colourChoice = a;
    }

    FXMLLoader loader = new FXMLLoader(getClass().
            getClassLoader().getResource("Menu3.fxml"));
    Parent root = loader.load();


    /**
     * if button 1 was clicked
     */
    @FXML
    void one() {

        setColourChoice(1);

        if (nameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(5);
            alert.setContentText("Field cannot be left blank");
            alert.show();
        }
        else {
            setName(nameField.getText());
            Menu3Controller Men3 = loader.getController();
            Men3.setPrimaryStage(primaryStage);
            primaryStage.setTitle("BestSokobanEverV6");
            primaryStage.setScene(new Scene(root, 300, 400));
            primaryStage.show();
        }
    }


    /**
     * if button 2 was clicked
     */
    @FXML
    void two() {
        setColourChoice(2);

        if (nameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(15);
            alert.setContentText("Field cannot be left blank");
            alert.show();
        } else
            {
            setName(nameField.getText());
            Menu3Controller Men3 = loader.getController();
            Men3.setPrimaryStage(primaryStage);
            primaryStage.setTitle("BestSokobanEverV6");
            primaryStage.setScene(new Scene(root, 300, 400));
            primaryStage.show();
        }
    }

    /**
     * if button 3 was clicked
     */
    @FXML
    void three() {
        setColourChoice(3);
        if (nameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(15);
            alert.setContentText("Field cannot be left blank");
            alert.show();
        }
        else
            {
            setName(nameField.getText());
            Menu3Controller Men3 = loader.getController();
            Men3.setPrimaryStage(primaryStage);
            primaryStage.setTitle("BestSokobanEverV6");
            primaryStage.setScene(new Scene(root, 300, 400));
            primaryStage.show();
        }
    }

    /**
     * if button 4 was clicked
     */
    @FXML
    void four() {
        setColourChoice(4);

        if (nameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(15);
            alert.setContentText("Field cannot be left blank");
            alert.show();
        } else {
            setName(nameField.getText());
            Menu3Controller Men3 = loader.getController();
            Men3.setPrimaryStage(primaryStage);
            primaryStage.setTitle("BestSokobanEverV6");
            primaryStage.setScene(new Scene(root, 300, 400));
            primaryStage.show();
        }
    }


    /**
     * setter for primary stage
     * @param primaryStage the stage to change scene
     */
    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }
}

