package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartFormController implements Initializable {

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Everything is working");
    }

    @FXML
    private Button cancelText;

    @FXML
    private TextField idText;

    @FXML
    private RadioButton inhouseRadioButton;

    @FXML
    private TextField inventoryText;

    @FXML
    private TextField machineIdText;

    @FXML
    private TextField maxText;

    @FXML
    private TextField minText;

    @FXML
    private TextField nameText;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField priceText;

    @FXML
    private Button saveButton;

    @FXML
    private ToggleGroup InhouseOrOutsourced;

    public void onActionReturnMainForm(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSave(ActionEvent actionEvent) {
    }


}