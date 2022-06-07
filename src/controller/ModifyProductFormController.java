package controller;

import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
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

    private static Product productInfo = null;
    private static int index = -1;

    public static Product passInfoToModifyProductForm(Product passInfo){
        productInfo = passInfo;
        index = Inventory.getAllProducts().indexOf(passInfo);

        return productInfo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fills text fields with info
        idText.setText(String.valueOf(productInfo.getId()));
        nameText.setText(productInfo.getName());
        inventoryText.setText(String.valueOf(productInfo.getStock()));
        priceText.setText(String.valueOf(productInfo.getPrice()));
        maxText.setText(String.valueOf(productInfo.getMax()));
        minText.setText(String.valueOf(productInfo.getMin()));

        //part
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //assoc part
        //assocPartTableView.setItems(Product.getAllAssociatedParts());
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part partObj = (Part) partTableView.getSelectionModel().getSelectedItem();
        Product productObj = new Product(0,"",0,0,0,0);
        if(partObj == null){
            return;
        }
        else{
            //not sure if the original part should remain in the part table, so this may need adjustment
            Inventory.getAllParts().remove(partObj);
            productObj.getAllAssociatedParts().add(partObj);
        }
    }
//maybe works???
    @FXML
    void onActionRemoveAssocPart(ActionEvent event) {
        Part partObj = (Part) assocPartTableView.getSelectionModel().getSelectedItem();
        //not working as intended, temporary constructor implemented to prevented errors
        Product productObj = new Product();
        if(partObj == null){
            return;
        }
        else{
            productObj.getAllAssociatedParts().remove(partObj);
            Inventory.getAllParts().add(partObj);
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
        int id = Integer.parseInt(idText.getText());
        String name = String.valueOf(nameText.getText());
        int stock = Integer.parseInt(inventoryText.getText());
        double price = Double.parseDouble(priceText.getText());
        int max = Integer.parseInt(maxText.getText());
        int min = Integer.parseInt(minText.getText());

        Product product = new Product(id, name, price, stock, max, min);
        Inventory.updateProduct(index, product);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public void onActionSearchParts(ActionEvent actionEvent) {
        String results = searchBar.getText();
        ObservableList<Part> searchParts = lookupPart(results);

        if(searchParts.size() == 0){
            int partIdSearch = Integer.parseInt(searchBar.getText());
            Part part = lookupPart(partIdSearch);

            if(part != null){
                searchParts.add(part);
            }
        }
        partTableView.setItems(searchParts);
    }
}