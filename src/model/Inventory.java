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


    //lookup part
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

    public static Product lookupProduct(int productId){
        for ( Product product : Inventory.getAllProducts()) {
            if(product.getId() == productId){
                //print out message to be deleted eventually
                System.out.println("Product found.");
                return product;
            }
            else{
                //print out message to be deleted eventually
                System.out.println("Product not found.");
            }
        }
        return null;
    }



    public static Part lookupPart(int partId) {
        for ( Part part : Inventory.getAllParts()) {
            if(part.getId() == partId){
                //print out message to be deleted eventually
                System.out.println("Part found.");
                return part;
            }
            else{
                //print out message to be deleted eventually
                System.out.println("Part not found.");
            }
        }
        return null;
    }

//not sure if this works at all
    public static void updatePart(int index, Part selectedPart){
       // might be better elsewhere index = Inventory.getAllParts().indexOf(selectedPart);
        //works but doesn't use an index?? I'm not sure how to.
        for(Part partObj : Inventory.getAllParts()){

            System.out.println(partObj.getName() + " and " + selectedPart.getName());
            if (partObj.getId() == selectedPart.getId()){
                allParts.add(selectedPart);
                deletePart(partObj);
            }
        }

    }

    public static void updateProduct(int index, Product newProduct){

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