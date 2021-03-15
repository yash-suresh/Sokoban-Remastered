package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class marks the entry point of the program.
 * @author Yash Suresh/School of Computer Science/University of Nottingham
 * @version 1.0
 * @since 1.0
 */

public class Main extends Application //Application
{
    /**
     * This is the 'main' function of the 'Main' class,
     * and is the program's entry point.
     *
     * @param args:passing String arguments in the main function by convention.
     */
    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Done!"); //this will be printed after the program exits the application window
    }

    /**
     * This function is called when the application is launched in 'main'
     * function. This then loads the Menu1.fxml and its associated controller onto
     * the 'primaryStage'
     * @param primaryStage: which is by convention the main 'Stage'.
     * @throws Exception if an error occurs when trying to load Menu1
     */
    @Override
    public void start(Stage primaryStage)throws Exception
    {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().
                getResource("Menu1.fxml"));
        //loads the class which gets the 'Menu1.fxml'

        Parent root = loader.load();
        Menu1Controller Men1 = loader.getController();
        //creating a new instance of the Menu1Controller

        Men1.setPrimaryStage(primaryStage);
        primaryStage.setTitle("BestSokobanEverV6");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

}
