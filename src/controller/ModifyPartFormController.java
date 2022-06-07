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

    public static void passIndexToModifyPart(int passIndex){

    }

    public void assignTextFieldsInHouse(){
        idText.setText(String.valueOf(partInfo.getId()));
        nameText.setText(partInfo.getName());
        inventoryText.setText(String.valueOf(partInfo.getStock()));
        priceText.setText(String.valueOf(partInfo.getPrice()));
        maxText.setText(String.valueOf(partInfo.getMax()));
        minText.setText(String.valueOf(partInfo.getMin()));
        machineIdText.setText(String.valueOf(((InHouse)partInfo).getMachineId()));
    }
//made all of this static but that may have to change later
    public void assignTextFieldsOutsourced(){
        idText.setText(String.valueOf(partInfo.getId()));
        nameText.setText(partInfo.getName());
        inventoryText.setText(String.valueOf(partInfo.getStock()));
        priceText.setText(String.valueOf(partInfo.getPrice()));
        maxText.setText(String.valueOf(partInfo.getMax()));
        minText.setText(String.valueOf(partInfo.getMin()));
        companyNameText.setText(String.valueOf(((Outsourced)partInfo).getCompanyName()));
    }

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

    public void onActionReturnMainForm(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSave(ActionEvent actionEvent) throws IOException {

        if(inHouseRadioButton.isSelected()){
            int id = Integer.parseInt(idText.getText());
            String name = String.valueOf(nameText.getText());
            int stock = Integer.parseInt(inventoryText.getText());
            double price = Double.parseDouble(priceText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());
            int machineId = Integer.parseInt(machineIdText.getText());

            InHouse inHouse = new InHouse(id, name, price, stock, max, min, machineId);

            Inventory.updatePart(index,inHouse);
        }
        else {
            int id = Integer.parseInt(idText.getText());
            String name = String.valueOf(nameText.getText());
            int stock = Integer.parseInt(inventoryText.getText());
            double price = Double.parseDouble(priceText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());
            String companyName = String.valueOf(companyNameText.getText());

            Outsourced outsourceObj = new Outsourced(id, name, price, stock, max, min, companyName);

            Inventory.updatePart(index,outsourceObj);
        }

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();;
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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