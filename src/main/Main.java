package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        //works for MainForm, 900 by 325, AddPartForm/ModifyPartForm 600 by 400, AddProductForm/ModifyProductForm 766 by 500
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
