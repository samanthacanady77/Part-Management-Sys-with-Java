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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class creates and manages the Add Part Form. */
public class AddPartFormController implements Initializable {
    Stage stage;
    Parent scene;

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

    static int newId = 99;

    /** This method generates unique IDs.
     * @return Returns the new, unique ID */
    public static int assignId(){

        newId++;

        return newId;
    }

    /** This method returns the user to the Main Form upon clicking the cancel button.
     * @param actionEvent The action of clicking the cancel button
     * */
    public void onActionReturnMainForm(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method saves the data input into the form and adds it as an InHouse or Outsourced part.
     * The form alternates between creating a InHouse or Outsourced part depending on which radio
     * button is selected. It also validates proper data is being input.
     *<p><b>
     * RUNTIME ERROR: I experienced a java.lang.NumberFormatException when attempting to generate the machine ID in the fields.
     * I had to make separate sections for passed in InHouse and Outsourced parts because Strings cannot be set as an int.
     *</b></p>
     * @param actionEvent The actions of clicking the save button
     */
    public void onActionSave(ActionEvent actionEvent) throws IOException {
       try {
           if (inHouseRadioButton.isSelected()) {

               int id = assignId();
               String name = String.valueOf(nameText.getText());
               int stock = Integer.parseInt(inventoryText.getText());
               double price = Double.parseDouble(priceText.getText());
               int max = Integer.parseInt(maxText.getText());
               int min = Integer.parseInt(minText.getText());
               int machineId = Integer.parseInt(machineIdText.getText());

               if (min >= max) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setContentText("The min value must be less than the max.");
                   alert.show();
               }

               if (stock > max || stock < min) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setContentText("Inventory must be between min and max values.");
                   alert.show();
               }
               if ((max > min) & (stock <= max) & (stock >= min)) {
                   if(name.isBlank()){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setContentText("Please enter a valid value for each field");
                       alert.show();

                   }
                   else {
                       Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

                       stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                       scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                       stage.setScene(new Scene(scene));
                       stage.show();
                   }
               }
           }
           else {
               int id = assignId();
               String name = String.valueOf(nameText.getText());
               int stock = Integer.parseInt(inventoryText.getText());
               double price = Double.parseDouble(priceText.getText());
               int max = Integer.parseInt(maxText.getText());
               int min = Integer.parseInt(minText.getText());
               String companyName = String.valueOf(companyNameText.getText());

               if (min >= max) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setContentText("The min value must be less than the max.");
                   alert.show();
               }
               if ((stock > max) || (stock < min)) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setContentText("Inventory must be between min and max values.");
                   alert.show();
               }
               if ((max > min) & (stock <= max) & (stock >= min)) {

                   if(name.isBlank() ||companyName.isBlank()){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setContentText("Please enter a valid value for each field");
                       alert.show();
                   }
                   else {
                       Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));

                       stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                       scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                       stage.setScene(new Scene(scene));
                       stage.show();
                   }
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

    /** This method changes the machine ID label to company name when the outsourcedRadioButton is selected.
     @param actionEvent The action of selecting the outsourcedRadioButton
     */
    public void onActionChangeViewToOutsourced(ActionEvent actionEvent){
        machineIdOrCompanyNameLabel.setText("Company Name");
        machineIdText.setVisible(false);
        companyNameText.setVisible(true);
        machineIdText.clear();
    }

    /** This method changes the company name label to machine ID when the inHouseRadioButton is selected.
     * @param actionEvent The action of selecting the inHouseRadioButton
     * */
    public void OnActionChangeViewToInHouse(ActionEvent actionEvent) {
        machineIdOrCompanyNameLabel.setText("Machine ID");
        machineIdText.setVisible(true);
        companyNameText.setVisible(false);
        companyNameText.clear();
    }

    /** This method initializes the class at start. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}