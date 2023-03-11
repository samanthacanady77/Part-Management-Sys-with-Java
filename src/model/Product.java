package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class creates products and associated parts for the inventory management system.*/
public class Product {


    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Product constructor.
     * @param id The product ID
     * @param name The product name
     * @param price The product price
     * @param stock The product stock
     * @param min The product inventory min
     * @param max The product inventory max
     * */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This method sets the product ID.
     * @param id The product's ID being assigned */
    public void setID(int id) {
        this.id = id;
    }

    /** This method sets the product's name.
     * @param name The name being assigned */
    public void setName(String name) {
        this.name = name;
    }

    /** This method sets the product's price.
     * @param price The price being assigned */
    public void setPrice(double price) {
        this.price = price;
    }

    /** This method sets the product's stock.
     * @param stock The stock being assigned*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This method sets the product's maximum inventory.
     * @param max The maximum inventory being assigned
     * */
    public void setMax(int max) {
        this.max = max;
    }

    /** This method sets the product's minimum inventory.
     * @param min The minimum inventory being assigned
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /** This method gets the product's ID.
     * @return Returns the ID
     * */
    public int getId() {
        return id;
    }

    /** This method gets the product's name.
     * @return Returns the name
     * */
    public String getName() {
        return name;
    }

    /** This method gets the product's price.
     * @return Returns the price
     * */
    public double getPrice() {
        return price;
    }

    /** This method gets the product's stock.
     * @return Returns the stock
     * */
    public int getStock() {
        return stock;
    }

    /** This method gets the product's minimum inventory.
     * @return Returns the minimum inventory
     * */
    public int getMin() {
        return min;
    }

    /** This method gets the product's maximum inventory.
     * @return Returns the maximum inventory
     * */
    public int getMax() {
        return max;
    }

    /** This method adds an associated part to the ObservableList associatedParts.
     * @param part The part being added
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** This method deletes an associated part.
     * @param selectedAssociatedPart The associated part to be deleted
     * @return Returns false if not found, deletes the part if found
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for (Part part : Inventory.getAllParts()) {
            if(part == selectedAssociatedPart) {
                return getAllAssociatedParts().remove(part);
            }
        }
        return false;
    }

    /** This method gets all associated parts from the ObservableList associatedParts.
     * @return Returns associatedParts */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}

