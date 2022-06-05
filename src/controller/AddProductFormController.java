package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
    Stage stage;
    Parent scene;

    //text fields
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

    //part table
    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    //associated part table
    @FXML
    private TableView<Part> assocPartTableView;

    @FXML
    private TableColumn<Part, Integer> assocPartIdCol;

    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    @FXML
    private TableColumn<Part, Integer> assocPartInventoryCol;

    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    //button and search bar
    @FXML
    private Button removeAssocPartButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollBar slideBar;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button searchButton;

    public static Product product = null;
    public static void passInfo (Product passIt){
        product = passIt;
    }

    static int newId = 0;
    public static int assignId(){

        newId++;

        return newId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

//not populating
        //not designed as intended

        //assocPartTableView.setItems(Product.getAllAssociatedParts());
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void onActionAddPart(ActionEvent actionEvent) {
        //not complete functionality,
        //5/27/22 maybe functional now?
        Part partObj = (Part) partTableView.getSelectionModel().getSelectedItem();
        //probably not working as intended because static function issues
        int id = assignId();
        String name = "dfgfdsg";
        int stock = 0;
        double price = 0.00;
        int max = 0;
        int min = 0;

        Product productObj = new Product(id, name, price, stock, max, min);

        if(partObj == null){
            return;
        }
        else{
            //functionality here may need adjustment
            Inventory.getAllParts().remove(partObj);
            productObj.getAllAssociatedParts().add(partObj);
        }
    }

    public void onActionSave(ActionEvent actionEvent) throws IOException {

        int id = assignId();
        String name = String.valueOf(nameText.getText());
        int stock = Integer.parseInt(inventoryText.getText());
        double price = Double.parseDouble(priceText.getText());
        int max = Integer.parseInt(maxText.getText());
        int min = Integer.parseInt(minText.getText());

        Inventory.addProduct(new Product(id, name, price, stock, max, min));

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

    }
    //maybe works???
    public void onActionRemoveAssocPart(ActionEvent actionEvent) {
        Part partObj = (Part) assocPartTableView.getSelectionModel().getSelectedItem();
        // not sure that this is working as intended

        if(partObj == null){
            return;
        }
        else{

           //.getAllAssociatedParts().remove(partObj);


        }
    }

    public void onActionSearchParts(ActionEvent actionEvent) {
    }
}