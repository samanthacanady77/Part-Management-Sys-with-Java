package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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



        //int id, String name, double price, int stock, int min, int max
        //data
        //this works to add randomized part ID
        /*Random rand = new Random();
        InHouse inHousePart = new InHouse(rand.nextInt(1000), "driver", 4.00, 5, 0, 4, 200);
        InHouse inHousePart2 = new InHouse(rand.nextInt(1000), "adfadsfsdr", 4.00, 5, 0, 4,200);
        Outsourced outsourced1 = new Outsourced(rand.nextInt(1000), "erwe", 4.00, 5, 0, 4,"Hello");
        Part partObj = new InHouse(rand.nextInt(1000), "hammerr", 4.00, 100, 0, 4, 200);

        //input to tableview
        Inventory.addPart(inHousePart);
        Inventory.addPart(inHousePart2);
        Inventory.addPart(outsourced1);
        Inventory.addPart(partObj);

        Product productObj = new Product(rand.nextInt(1000), "asdfasd",4.00, 5,0,1 );
        Inventory.addProduct(productObj);
        productObj.addAssociatedPart(partObj);
*/

        //launch
        launch(args);




    }
}
