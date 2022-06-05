package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import model.*;

import javax.swing.*;
import java.awt.event.InputMethodEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

import static com.sun.tools.doclint.Entity.part;

import static java.sql.Types.NULL;
import static model.Inventory.*;


public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;

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

    //part buttons and search bar
    @FXML
    private TextField partSearchBar;

    @FXML
    private Button partAddButton;

    @FXML
    private Button partModifyButton;

    @FXML
    private Button partDeleteButton;

    //product table
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

    //product button and search bar
    @FXML
    private TextField productSearchBar;

    @FXML
    private Button productAddButton;

    @FXML
    private Button productModifyButton;

    @FXML
    private Button productDeleteButton;

    //exit button
    @FXML
    private Button exitButton;

    @FXML
    void onActionSearchParts(ActionEvent event) {
            String results = partSearchBar.getText();
            ObservableList<Part> part = lookupPart(results);

            partTableView.setItems(part);

    }

    @FXML
    void onActionSearchProducts(ActionEvent event) {
        String results = productSearchBar.getText();
        ObservableList<Product> product = lookupProduct(results);

        productTableView.setItems(product);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //reassigns Id numbers each time the main form is pulled up, not sure if this is the proper functionality
        //AddPartFormController.assignId();

        //populate part table view
        partTableView.setItems(Inventory.getAllParts());
        //partTableView.setItems(filter("Sam")); //temp
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //populate product table view
        productTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //search function
        if(search(4) == true){
            System.out.println("Search Found");
        }
        else{
            System.out.println("Search not found");
        }

    }

    public boolean search(int id){

        for(Part part : Inventory.getAllParts()) {
            if (part.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void onActionAddPart(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {

        Part partObj = (Part)partTableView.getSelectionModel().getSelectedItem();
        if(partObj == null){
            return;
            //maybe add a message saying you must select a part???
        }
        else{
            ModifyPartFormController.passInfoToModifyPartForm(partObj);

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyPartForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void onActionDeletePart(ActionEvent actionEvent) {
        Part selectedPart = (Part)partTableView.getSelectionModel().getSelectedItem();

        if(selectedPart == null){
            return;
        }
        else{
            Inventory.getAllParts().remove(selectedPart);
            //not sure if it should work like this, makes the search bar blank and restores original updated list

            partSearchBar.setText("");
            partTableView.setItems(Inventory.getAllParts());

        }

    }

    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product)productTableView.getSelectionModel().getSelectedItem();

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

    public void onActionDeleteProduct(ActionEvent actionEvent) {
        Product selectedProductObj = (Product)productTableView.getSelectionModel().getSelectedItem();
        if(selectedProductObj == null){
            return;
        }
        else{
            Inventory.getAllProducts().remove(selectedProductObj);
            //not sure if it should work like this, makes the search bar blank and restores original updated list
            //will probably update to make dynamic eventually
            productSearchBar.setText("");
            productTableView.setItems(Inventory.getAllProducts());
        }
    }

    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }


}