package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
//https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=058e6f9d-631b-4f38-9ed9-ab49011c71c4
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public void lookupPart(int partID){

    }

    public void lookupProduct(int productID){

    }

    public ObservableList<Part> lookupPart(String partName){
        //might need to change return statement
        return allParts;
    }

    public ObservableList<Product> lookupProduct(String productName){
//might need to change return statement
        return allProducts;
    }

    public void updatePart(int index, Part selectedPart){

    }

    public void updateProduct(int index, Product newProduct){

    }

    public boolean deletePart(Part selectedPart){
//might need to change return statement
        return false;
    }

    public boolean deleteProduct(Product selectedProduct){
//might need to change return statement
        return false;
    }
// https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=058e6f9d-631b-4f38-9ed9-ab49011c71c4
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }



}