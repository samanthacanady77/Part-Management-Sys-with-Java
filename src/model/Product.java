package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.sun.tools.doclint.Entity.part;

public class Product {
// cannot be static
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    //field declaration
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    //constructor
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public Product() {

    }

    //setters
    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
//experimental
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for (Part partObj : Inventory.getAllParts()) {
            if(partObj == selectedAssociatedPart) {
                return getAllAssociatedParts().remove(part);
            }
        }
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}

