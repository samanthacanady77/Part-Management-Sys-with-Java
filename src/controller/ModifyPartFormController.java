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

/** This class creates and manages the Modify Part Form.*/
public class ModifyPartFormController<index> implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField inventoryText;
    @FXML
    private TextField maxText;
    @FXML
    private TextField minText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField machineIdText;
    @FXML
    private TextField companyNameText;
    @FXML
    private Label machineIdOrCompanyNameLabel;
    @FXML
    private Label machineIdLabel;

    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private ToggleGroup inHouseOrOutsourced;

    private static Part partInfo = null;
    private static int index = -1;

    /** This method allows part data to be passed in from the Main Form.
     * @param passInfo The part data being passed in
     * */
    public static void passInfoToModifyPartForm(Part passInfo){
        partInfo = passInfo;
        index = Inventory.getAllParts().indexOf(passInfo);

    }

    /** This method populates the text fields with the InHouse part data passed in. */
    public void assignTextFieldsInHouse(){
        idText.setText(String.valueOf(partInfo.getId()));
        nameText.setText(partInfo.getName());
        inventoryText.setText(String.valueOf(partInfo.getStock()));
        priceText.setText(String.valueOf(partInfo.getPrice()));
        maxText.setText(String.valueOf(partInfo.getMax()));
        minText.setText(String.valueOf(partInfo.getMin()));
        machineIdText.setText(String.valueOf(((InHouse)partInfo).getMachineId()));

    }
    /** This method populates the text fields with the Outsourced part data passed in. */
    public void assignTextFieldsOutsourced(){
        idText.setText(String.valueOf(partInfo.getId()));
        nameText.setText(partInfo.getName());
        inventoryText.setText(String.valueOf(partInfo.getStock()));
        priceText.setText(String.valueOf(partInfo.getPrice()));
        maxText.setText(String.valueOf(partInfo.getMax()));
        minText.setText(String.valueOf(partInfo.getMin()));
        companyNameText.setText(String.valueOf(((Outsourced)partInfo).getCompanyName()));

    }

    /** This method returns the user to the Main Form on clicking the cancel button.
     * @param actionEvent The action of clicking the cancel button
     * */
    public void onActionReturnMainForm(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method saves the data input into the text fields and adds it to the proper part class.
     * Which class the part is added to is based on which radio button is selected. Data is also validated.
     * @param actionEvent The action of clicking the save button
     * */
    public void onActionSave(ActionEvent actionEvent) throws IOException {
        try {
            if (inHouseRadioButton.isSelected()) {
                int id = Integer.parseInt(idText.getText());
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
                        InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.updatePart(index, inHouse);

                        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();

                    }
                }
            }
            else {
                int id = Integer.parseInt(idText.getText());
                String name = String.valueOf(nameText.getText());
                int stock = Integer.parseInt(inventoryText.getText());
                double price = Double.parseDouble(priceText.getText());
                int max = Integer.parseInt(maxText.getText());
                int min = Integer.parseInt(minText.getText());
                String companyName = String.valueOf(companyNameText.getText());

                if (min > max) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setContentText("The min value must be less than the max.");
                    alert1.show();

                }
                if ((stock > max) || (stock < min)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Inventory must be between min and max.");
                    alert.show();

                }
                if ((max > min) & (stock <= max) & (stock >= min)) {
                    if(name.isBlank() || companyName.isBlank()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Please enter a valid value for each field");
                        alert.show();

                    }
                    else {
                        Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                        Inventory.updatePart(index, outsourced);

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

    /** This method changes the machine ID label to company name if the outsourcedRadioButton is selected.
     * @param actionEvent The action of selecting the outsourcedRadioButton
     * */
    public void onActionChangeViewToOutsourced(ActionEvent actionEvent){
        machineIdOrCompanyNameLabel.setText("Company Name");
        machineIdText.setVisible(false);
        companyNameText.setVisible(true);

    }

    /** This method changes the company name label to machine ID if the inHouseRadioButton is selected.
     * @param actionEvent The action of selecting the inHouseRadioButton
     * */
    public void onActionChangeViewToInHouse(ActionEvent actionEvent) {
        machineIdOrCompanyNameLabel.setText("Machine ID");
        machineIdText.setVisible(true);
        companyNameText.setVisible(false);

    }

    /** This method initializes the form and decides what label is shown based on the type of part is passed in. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (partInfo instanceof InHouse) {

            inHouseRadioButton.setSelected(true);
            machineIdOrCompanyNameLabel.setText("Machine ID");

            machineIdText.setVisible(true);
            companyNameText.setVisible(false);

            assignTextFieldsInHouse();
        }
        else if (partInfo instanceof Outsourced) {

            outsourcedRadioButton.setSelected(true);

            machineIdOrCompanyNameLabel.setText("Company Name");

            machineIdText.setVisible(false);
            companyNameText.setVisible(true);

            assignTextFieldsOutsourced();
        }
    }
}