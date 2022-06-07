package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
                System.out.println("Part not found.");
            }
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
                System.out.println("Product not found.");
            }
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

            System.out.println("if hasnt fired");
            if (allProducts.indexOf(product) == index){

                System.out.println("if fired");
                allProducts.set(index, newProduct);
            }
        }
    }




    public static boolean deletePart(Part selectedPart){
        for(Part partObj : Inventory.getAllParts()){
            if (partObj.getId() == selectedPart.getId()){
                getAllParts().remove(selectedPart);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        for(Product productObj : Inventory.getAllProducts()){
            if (productObj.getId() == selectedProduct.getId()){
                getAllParts().remove(selectedProduct);
                return true;
            }
        }
        return false;
    }
// https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=058e6f9d-631b-4f38-9ed9-ab49011c71c4




}