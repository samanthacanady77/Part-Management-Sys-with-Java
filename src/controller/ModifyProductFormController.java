package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductFormController implements Initializable {
    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("This too is working");
    }

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<?, ?> assocPartIdCol;

    @FXML
    private TableColumn<?, ?> assocPartInventoryCol;

    @FXML
    private TableColumn<?, ?> assocPartNameCol;

    @FXML
    private TableColumn<?, ?> assocPartPriceCol;

    @FXML
    private TableView<?> assocPartTableView;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idText;

    @FXML
    private TextField inventoryText;

    @FXML
    private TextField maxText;

    @FXML
    private TextField minText;

    @FXML
    private TextField nameText;

    @FXML
    private TableColumn<?, ?> partIdCol;

    @FXML
    private TableColumn<?, ?> partInventoryCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partPriceCol;

    @FXML
    private TableView<?> partTableView;

    @FXML
    private TextField priceText;

    @FXML
    private Button removeAssocPartButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchBar;

    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionRemoveAssocPart(ActionEvent event) {

    }

    @FXML
    void onActionReturnMainForm(ActionEvent event) throws IOException {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

}