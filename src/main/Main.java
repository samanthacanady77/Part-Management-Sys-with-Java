package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Javadoc files are located in the main folder in a folder titled javadoc
/** This is the main class that loads the first scene, Main Form.
 * <p><b>
 *  FUTURE ENHANCEMENT: I would enhance this inventory management system by adding a SQL database. It could also be enhanced
 *  by adding a way to search and arrange by prices, stock, etc.
 * </b></p>
 * */
public class Main extends Application {
    @Override
    /** This method starts the program and loads the Main Form.
     * @param stage This loads the first stage
     * */
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Main Form");
        stage.setScene(new Scene(root, 900, 375));
        stage.show();
        stage.setResizable(false);

    }

    public static void main(String[] args ){
        launch(args);

    }
}
