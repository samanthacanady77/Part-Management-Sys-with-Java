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

/** This class creates and manages the Add Product Form. */
public class AddProductFormController implements Initializable {
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
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;

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

    @FXML
    private Button removeAssocPartButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button searchButton;

    public ObservableList<Part> associatedPartsTableView = FXCollections.observableArrayList();

    static int newId = 99;
    /** This method generates unique IDs.
     * @return Returns the new ID
     * */
    public static int assignId(){
        newId++;

        return newId;
    }

    /** This method adds a part to the product's assocPartTableView ObservableList.
     * @param actionEvent The action of clicking the add button after selecting a part
     * */
    public void onActionAddPart(ActionEvent actionEvent) {
        Part assocPart = partTableView.getSelectionModel().getSelectedItem();

        if(assocPart == null){
            return;
        }
        else{

            associatedPartsTableView.add(assocPart);
        }
    }

    /** This method removes an associated part from the assocPartTableView and confirms it with the user.
     * @param actionEvent The action of clicking the remove button
     * */
    public void onActionRemoveAssocPart(ActionEvent actionEvent) {
        Part assocPart = assocPartTableView.getSelectionModel().getSelectedItem();

        if (assocPart == null) {
            return;

        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this associated part?");
            Optional<ButtonType> result = alert1.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedPartsTableView.remove(assocPart);
            }
        }
    }

    /** This method searches for a selected part using Inventory's lookupPart methods.
     * @param actionEvent The action of clicking the search button
     * */
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
                if (searchParts.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Part not found.");
                    alert.show();
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

    /** This method saves the product information input and the associated parts linked to it.
     * It takes the data from the assocPartTableView and adds it to the product's
     * associatedParts ObservableList.
     * It also validates the data being input to prevent errors.
     * @param actionEvent The action of clicking the save button
     * */
    public void onActionSave(ActionEvent actionEvent) throws IOException {
        try {
            int id = assignId();
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
            if ((stock > max) || (stock < min)) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setContentText("Inventory must be between min and max values.");
                alert2.show();

            }
            if ((max > min) & (stock <= max) & (stock >= min)) {
                if(name.isBlank()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please enter a valid value for each field");
                    alert.show();

                }
                else {
                    Product product = (new Product(id, name, price, stock, min, max));
                    Inventory.addProduct(product);

                    for (Part part : associatedPartsTableView) {
                        product.addAssociatedPart(part);
                    }

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();;
                    scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid value for each field.");
            alert.show();
        }

    }

    /** This method cancels and returns to the Main Form.
     * @param actionEvent The action of clicking the cancel button*/
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /** This method initializes the form and populates the tables. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocPartTableView.setItems(associatedPartsTableView);
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}