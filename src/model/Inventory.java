package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**This class handles the management of products and parts.*/
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /** This method adds parts to the allParts ObservableList.
     * @param newPart The part being added to newParts
     * */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** This method adds products to the allProducts ObservableList.
     * @param newProduct The product being added to allProducts
     * */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }


    /**This method does a part lookup using the part ID.
     * @param partId The part ID being used to search
     * @return Returns the located part
     * */
    public static Part lookupPart(int partId) {
        for ( Part part : Inventory.getAllParts()) {
            if(part.getId() == partId){
                return part;
            }
        }
        return null;

    }

    /** This method does a product lookup using the product ID.
     * @param productId The product ID being used to search
     * @return Returns the located product
     * */
    public static Product lookupProduct(int productId){
        for ( Product product : Inventory.getAllProducts()) {
            if(product.getId() == productId){
                return product;
            }
        }
        return null;

    }


    /** This method does a part lookup using the part name.
     @param partName The full or partial part name being used to search
     @return Returns an ObservableList of matching parts
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> listedParts = FXCollections.observableArrayList();

        for(Part part : Inventory.getAllParts()){
            if(part.getName().contains(partName)){
                listedParts.add(part);
            }
        }
        return listedParts;
    }

    /**This method does a product lookup using the product name.
     * @param productName The full or partial product name being used to search
     * @return Returns an ObservableList of match products
     * */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> listedProducts = FXCollections.observableArrayList();

        for(Product product : Inventory.getAllProducts()){
            if(product.getName().contains(productName)){
                listedProducts.add(product);
            }
        }
        return listedProducts;
    }


    /** This method updates a part.
     * @param index The index of the part being updated in the allParts ObservableList
     * @param selectedPart The new part information being used to update
     */
    public static void updatePart(int index, Part selectedPart){
        for(Part part : Inventory.getAllParts()){

            if (allParts.indexOf(part) == index){

                allParts.set(index, selectedPart);
            }
        }
    }

    /** This method updates a product.
     * @param index The index of the product being updated in the allProducts ObservableList
     * @param newProduct The new product information being used to update
     */
    public static void updateProduct(int index, Product newProduct){
        for(Product product : Inventory.getAllProducts()){

            if (allProducts.indexOf(product) == index){

                allProducts.set(index, newProduct);
            }
        }
    }


    /** This method deletes a part.
     * @param selectedPart The part being deleted
     * @return Returns false if not found, removes part if found
     * */
    public static boolean deletePart(Part selectedPart){
        for(Part partObj : Inventory.getAllParts()){
            if (partObj.getId() == selectedPart.getId()){
                return getAllParts().remove(selectedPart);
            }
        }
        return false;
    }

    /** This method deletes a product.
     * @param selectedProduct The part being deleted
     * @return Returns false if not found, removes product if found
     * */
    public static boolean deleteProduct(Product selectedProduct){
        for(Product productObj : Inventory.getAllProducts()){
            if (productObj.getId() == selectedProduct.getId()){
                return getAllProducts().remove(selectedProduct);
            }
        }
        return false;


    }


    /** This method returns all parts inside the ObservableList allParts.
     * @return Returns allParts
     * */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /** This method returns all products inside the ObservableList allProducts.
     * @return Returns allProducts
     * */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }




}