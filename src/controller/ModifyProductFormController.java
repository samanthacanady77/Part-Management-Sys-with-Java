package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.lookupPart;

public class ModifyProductFormController implements Initializable {
    Stage stage;
    Parent scene;

    //text fields
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
    private TextField priceText;

    //part table
    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    //assoc part table
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

    //search bar and button
    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button cancelButton;

    @FXML
    private Button removeAssocPartButton;

    @FXML
    private Button searchButton;

    public ObservableList<Part> associatedPartsTable = FXCollections.observableArrayList();

    private static Product product = null;
    private static int index = -1;

    public static Product passInfoToModifyProductForm(Product passInfo){
        product = passInfo;
        index = Inventory.getAllProducts().indexOf(passInfo);

        return product;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        associatedPartsTable = product.getAllAssociatedParts();

        //fills text fields with info
        idText.setText(String.valueOf(product.getId()));
        nameText.setText(product.getName());
        inventoryText.setText(String.valueOf(product.getStock()));
        priceText.setText(String.valueOf(product.getPrice()));
        maxText.setText(String.valueOf(product.getMax()));
        minText.setText(String.valueOf(product.getMin()));

        //part
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocPartTableView.setItems(associatedPartsTable);
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part assocPart = (Part) partTableView.getSelectionModel().getSelectedItem();

        if(assocPart == null){
            return;
        }
        else{
            associatedPartsTable.add(assocPart);
        }
    }

    @FXML
    void onActionRemoveAssocPart(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this associated part?");
        Optional<ButtonType> result = alert1.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Part assocPart = (Part) assocPartTableView.getSelectionModel().getSelectedItem();

            if (assocPart == null) {
                return;
            } else {
                associatedPartsTable.remove(assocPart);
            }
        }
    }
    @FXML
    void onActionReturnMainForm(ActionEvent event) throws IOException {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(idText.getText());
            String name = String.valueOf(nameText.getText());
            int stock = Integer.parseInt(inventoryText.getText());
            double price = Double.parseDouble(priceText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());

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
                Product product = new Product(id, name, price, stock, min, max);
                Inventory.updateProduct(index, product);

                for (Part part : associatedPartsTable) {
                    product.addAssociatedPart(part);
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid value for each field.");
            alert.show();
        }
    }

    public void onActionSearchParts(ActionEvent actionEvent) {
        String results = searchBar.getText();
        ObservableList<Part> searchParts = lookupPart(results);

        if(searchParts.size() == 0){
            try {
                int partIdSearch = Integer.parseInt(searchBar.getText());
                Part part = lookupPart(partIdSearch);

                if (part != null) {
                    searchParts.add(part);
                }
            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Part not found.");
                alert.show();
            }
        }
        partTableView.setItems(searchParts);
    }
}