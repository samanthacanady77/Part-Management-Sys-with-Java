package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddPartFormController implements Initializable {
    Stage stage;
    Parent scene;

    //variables
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField inventoryText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField maxText;
    @FXML
    private TextField minText;
    @FXML
    private TextField machineIdText;
    @FXML
    private TextField companyNameText;
    @FXML
    private Label machineIdOrCompanyNameLabel;


    //buttons
    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private Button cancelButton;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private Button saveButton;
    @FXML
    private ToggleGroup InHouseOrOutsourced;

    //generates unique IDs
    static int newId = 99;

    public static int assignId(){

        newId++;

        return newId;
    }

    public void onActionReturnMainForm(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSave(ActionEvent actionEvent) throws IOException {
       try {
           if (inHouseRadioButton.isSelected()) {
               //calls a method that generates a sequential and unique id for each item
               int id = assignId();
               String name = String.valueOf(nameText.getText());
               int stock = Integer.parseInt(inventoryText.getText());
               double price = Double.parseDouble(priceText.getText());
               int max = Integer.parseInt(maxText.getText());
               int min = Integer.parseInt(minText.getText());
               int machineId = Integer.parseInt(machineIdText.getText());

               if (min >= max) {
                   Alert alert1 = new Alert(Alert.AlertType.ERROR);
                   alert1.setTitle("Error");
                   alert1.setContentText("The min value must be less than the max.");
                   alert1.show();
               }

               if (stock > max || stock < min) {
                   Alert alert2 = new Alert(Alert.AlertType.ERROR);
                   alert2.setTitle("Error");
                   alert2.setContentText("Inventory must be between min and max values.");
                   alert2.show();
               }
               if (max > min & stock <= max & stock >= min) {
                   Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

                   stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                   scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                   stage.setScene(new Scene(scene));
                   stage.show();
               }
           }
           //java.lang.NumberFormatException: For input string: ""
           //fixed by alter machineId, moved it inside of the loop
           else {
               int id = assignId();
               String name = String.valueOf(nameText.getText());
               int stock = Integer.parseInt(inventoryText.getText());
               double price = Double.parseDouble(priceText.getText());
               int max = Integer.parseInt(maxText.getText());
               int min = Integer.parseInt(minText.getText());
               String companyName = String.valueOf(companyNameText.getText());

               if (min >= max) {
                   Alert alert1 = new Alert(Alert.AlertType.ERROR);
                   alert1.setTitle("Error");
                   alert1.setContentText("The min value must be less than the max.");
                   alert1.show();
               }
               if (stock > max || stock < min) {
                   Alert alert2 = new Alert(Alert.AlertType.ERROR);
                   alert2.setTitle("Error");
                   alert2.setContentText("Inventory must be between min and max values.");
                   alert2.show();
               }
               if (max > min & stock <= max & stock >= min) {
                   System.out.println(max);
                   Part tempPartTest = new Outsourced(id, name, price, stock, min, max, companyName);
                   System.out.println(tempPartTest.getMax());
                   Inventory.addPart(tempPartTest);

                   stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();;
                   scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                   stage.setScene(new Scene(scene));
                   stage.show();
               }
           }
       }
       catch(NumberFormatException e) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setContentText("Please enter a valid value for each field.");
           alert.show();
       }
    }

    //switches between the two AddPartForm views based on Radio Button Selection
    public void onActionChangeViewToOutsourced(ActionEvent actionEvent) throws IOException {
        machineIdOrCompanyNameLabel.setText("Company Name");
        machineIdText.setVisible(false);
        companyNameText.setVisible(true);
        machineIdText.clear();
    }

    public void OnActionChangeViewToInHouse(ActionEvent actionEvent) {
        machineIdOrCompanyNameLabel.setText("Machine ID");
        machineIdText.setVisible(true);
        companyNameText.setVisible(false);
        companyNameText.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}