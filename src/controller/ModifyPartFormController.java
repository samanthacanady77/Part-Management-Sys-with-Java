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

    //button
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

    public static void passInfoToModifyPartForm(Part passInfo){

        partInfo = passInfo;
        //gets the index needed to call update()
        index = Inventory.getAllParts().indexOf(passInfo);
    }

    public void assignTextFieldsInHouse(){
        idText.setText(String.valueOf(partInfo.getId()));
        nameText.setText(partInfo.getName());
        inventoryText.setText(String.valueOf(partInfo.getStock()));
        priceText.setText(String.valueOf(partInfo.getPrice()));
        maxText.setText(String.valueOf(partInfo.getMax()));
        minText.setText(String.valueOf(partInfo.getMin()));
        machineIdText.setText(String.valueOf(((InHouse)partInfo).getMachineId()));
        System.out.println("first assign fields: " + partInfo.getMax());
    }

    public void assignTextFieldsOutsourced(){
        idText.setText(String.valueOf(partInfo.getId()));
        nameText.setText(partInfo.getName());
        inventoryText.setText(String.valueOf(partInfo.getStock()));
        priceText.setText(String.valueOf(partInfo.getPrice()));
        maxText.setText(String.valueOf(partInfo.getMax()));
        minText.setText(String.valueOf(partInfo.getMin()));
        companyNameText.setText(String.valueOf(((Outsourced)partInfo).getCompanyName()));
        System.out.println("second assign fields: " + partInfo.getMax());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (partInfo instanceof InHouse) {
            System.out.println("first instance of " + partInfo.getMax());
            inHouseRadioButton.setSelected(true);
            machineIdOrCompanyNameLabel.setText("Machine ID");

            machineIdText.setVisible(true);
            companyNameText.setVisible(false);

            assignTextFieldsInHouse();
        }
        else if (partInfo instanceof Outsourced) {
            System.out.println("second instance of " + partInfo.getMax());
            outsourcedRadioButton.setSelected(true);

            machineIdOrCompanyNameLabel.setText("Company Name");

            machineIdText.setVisible(false);
            companyNameText.setVisible(true);

            assignTextFieldsOutsourced();
        }
    }

    public void onActionReturnMainForm(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSave(ActionEvent actionEvent) throws IOException {
        try {
            if (inHouseRadioButton.isSelected()) {
                System.out.println("inhouse body " + maxText + " " + minText);
                int id = Integer.parseInt(idText.getText());
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
                    InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.updatePart(index, inHouse);

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
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
                if (stock > max || stock < min) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setContentText("Inventory must be between min and max.");
                    alert2.show();
                }
                if (max > min & stock < max & stock > min) {
                    Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(index, outsourced);

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
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

    //switches between the two ModifyPartForm views based on Radio Button Selection
    public void onActionChangeViewToOutsourced(ActionEvent actionEvent) throws IOException {
        machineIdOrCompanyNameLabel.setText("Company Name");
        machineIdText.setVisible(false);
        companyNameText.setVisible(true);

    }

    public void onActionChangeViewToInHouse(ActionEvent actionEvent) {
        machineIdOrCompanyNameLabel.setText("Machine ID");
        machineIdText.setVisible(true);
        companyNameText.setVisible(false);

    }
}