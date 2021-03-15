package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This is the controller class for Menu1FXMl
 * @author Yash Suresh
 * @version 1.0
 * @since 1.0
 *
 */


public class Menu1Controller
{

    private Stage primaryStage;


    /**
     * This function changes the scene to the 2nd menu, and loads the FXMl
     * and its associated controller.
     * @throws IOException : if error occurs while loading the fxml.
     */
    @FXML
    void goToMenu2() throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().
                getClassLoader().getResource("Menu2.fxml"));
        Parent root = loader.load();

        Menu2Controller Men2 = loader.getController();
        Men2.setPrimaryStage(primaryStage);
        primaryStage.setTitle("BestSokobanEverV6");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();

    }

    /**
     * Setter for the primary stage
     * @param primaryStage : the stage to change the scene on
     *
     */
    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }
}
