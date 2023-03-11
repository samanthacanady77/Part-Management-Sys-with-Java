package controller;
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

import static model.Inventory.*;


/** This class creates and manages the Main Form. */
public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField partSearchBar;
    @FXML
    private Button partSearchButton;

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

    @FXML
    private Button partAddButton;
    @FXML
    private Button partModifyButton;
    @FXML
    private Button partDeleteButton;

    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField productSearchBar;
    @FXML
    private Button productSearchButton;
    @FXML
    private Button productAddButton;
    @FXML
    private Button productModifyButton;
    @FXML
    private Button productDeleteButton;
    @FXML
    private Button exitButton;

    /** This method searches for parts on the Main Form using Inventory's lookupPart methods.
     * @param event The action of clicking the search bar button
     * */
    @FXML
    void onActionSearchParts(ActionEvent event) {
        String results = partSearchBar.getText();
        ObservableList<Part> searchParts = lookupPart(results);

        if(searchParts.size() == 0){
            //Caused by: java.lang.NumberFormatException: For input string: "asdfsdf"

            try {
                int partIdSearch = Integer.parseInt(partSearchBar.getText());
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

    /** This method searches for products on the Main Form using Inventory's lookupProduct methods.
     * @param event The action of clicking the search bar button
     * */
    @FXML
    void onActionSearchProducts(ActionEvent event) {
        String results = productSearchBar.getText();
        ObservableList<Product> searchProducts = lookupProduct(results);

        if(searchProducts.size() == 0 ){

            try {
                int productIdSearch = Integer.parseInt(productSearchBar.getText());
                Product product = lookupProduct(productIdSearch);

                if (product != null) {
                    searchProducts.add(product);
                }
                if (searchProducts.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Product not found.");
                    alert.show();
                }
            }
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Product not found.");
                alert.show();
            }
        }
        productTableView.setItems(searchProducts);
    }


    /** This method changes to the Add Part Form.
     * @param actionEvent The action of clicking the part add button.
     * */
    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**This method changes to the Add Product Form.
     * @param actionEvent The action of clicking the product add button.
     * */
    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    /** This method allows you to select a part and change screens to the Modify Part Form.
     * @param actionEvent The action of clicking the part modify button
     * */
    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {
        Part part = partTableView.getSelectionModel().getSelectedItem();

        if(part == null){
            return;
        }
        else{
            ModifyPartFormController.passInfoToModifyPartForm(part);

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyPartForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /** This method allows you to select a product and then changes to the Modify Product Form.
     * @param actionEvent The action of clicking the product modify button
     * */
    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if(selectedProduct == null){
            return;
        }

        else{
            ModifyProductFormController.passInfoToModifyProductForm(selectedProduct);
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyProductForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /** This method deletes a part and confirms it with the user.
     * @param actionEvent The action of clicking the part delete button
     * */
    public void onActionDeletePart(ActionEvent actionEvent) {

        try {
            Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

            if (selectedPart == null) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setContentText("You must select a part to be deleted.");
                alert2.show();
            }
            else{

                // A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.

                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this part?");
                Optional<ButtonType> result = alert1.showAndWait();

                if(result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deletePart(selectedPart);

                    partSearchBar.setText("");
                    partTableView.setItems(Inventory.getAllParts());

                }
            }
        } catch (NullPointerException e) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Error");
            alert3.setContentText("You must select a part to be deleted.");
            alert3.show();
        }
    }

    /** This method deletes a product and confirms it with the user.
     * @param actionEvent The action of clicking the product delete button
     * */
    public void onActionDeleteProduct(ActionEvent actionEvent) {
        try {
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

            if (selectedProduct == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You must select a product to be deleted.");
                alert.show();

            } else if (selectedProduct.getAllAssociatedParts().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("A product with associated parts cannot be deleted.");
                alert.show();

            } else if (selectedProduct.getAllAssociatedParts().size() == 0) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
                Optional<ButtonType> result = alert1.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(selectedProduct);

                    productSearchBar.setText("");
                    productTableView.setItems(Inventory.getAllProducts());
                }
            }
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a product to be deleted.");
            alert.show();
        }


    }

    /** This method exits the program.
     * @param actionEvent The action of clicking the exit button
     * */
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }


    /** This method initializes the form. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}