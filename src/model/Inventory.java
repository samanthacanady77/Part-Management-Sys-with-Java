package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class Inventory {
//https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=058e6f9d-631b-4f38-9ed9-ab49011c71c4
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }


    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }


    //lookup
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> listedParts = FXCollections.observableArrayList();

        for(Part part : Inventory.getAllParts()){
            if(part.getName().contains(partName)){
                listedParts.add(part);

            }
            else {

            }
        }
        return listedParts;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> listedProducts = FXCollections.observableArrayList();

        for(Product product : Inventory.getAllProducts()){
            if(product.getName().contains(productName)){
                listedProducts.add(product);
            }
        }
        return listedProducts;
    }


    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for ( Part part : Inventory.getAllParts()) {
            if(part.getId() == partId){
                return part;
            }
            else{
                //print out message to be deleted eventually
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Part not found.");
                alert.show();;
            }
        }
        if(Inventory.getAllParts().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Part not found.");
            alert.show();;
        }
        return null;
    }

    public static Product lookupProduct(int productId){
        for ( Product product : Inventory.getAllProducts()) {
            if(product.getId() == productId){
                return product;
            }
            else{
                //print out message to be deleted eventually
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Product not found.");
                alert.show();;
            }
        }
        if(Inventory.getAllProducts().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Product not found.");
            alert.show();
        }
        return null;
    }


    public static void updatePart(int index, Part selectedPart){
        for(Part part : Inventory.getAllParts()){

            if (allParts.indexOf(part) == index){

                allParts.set(index, selectedPart);
            }
        }
    }

    public static void updateProduct(int index, Product newProduct){
        for(Product product : Inventory.getAllProducts()){

            if (allProducts.indexOf(product) == index){

                allProducts.set(index, newProduct);
            }
        }
    }

    public static boolean deletePart(Part selectedPart){
        for(Part partObj : Inventory.getAllParts()){
            if (partObj.getId() == selectedPart.getId()){
                return getAllParts().remove(selectedPart);
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        for(Product productObj : Inventory.getAllProducts()){
            if (productObj.getId() == selectedProduct.getId()){
                return getAllProducts().remove(selectedProduct);
            }
        }
        return false;
    }
// https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=058e6f9d-631b-4f38-9ed9-ab49011c71c4




}