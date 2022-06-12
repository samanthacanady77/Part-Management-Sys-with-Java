package model;
/** This class creates parts for the inventory management system.*/
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Part constructor.
     * @param id The part ID
     * @param name The part name
     * @param price The part price
     * @param stock The part stock
     * @param min The part inventory min
     * @param max The part inventory max
     * */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This method sets a part's ID.
     * @param id The ID being assigned
     */
    public void setId(int id){
        this.id = id;
    }

    /** This method sets a part's name.
     * @param name The name being assigned
     * */
    public void setName(String name){
        this.name = name;
    }

    /** This method sets a part's price.
     * @param price The price being assigned
     * */
    public void setPrice(double price){
        this.price = price;
    }

    /** This method sets a part's stock.
     * @param stock The stock being assigned
     * */
    public void setStock(int stock){
        this.stock = stock;
    }

    /** This method sets a part's maximum inventory.
     * @param max The maximum inventory being assigned
     * */
    public void setMax(int max){
        this.max = max;
    }

    /** This method sets a part's minimum inventory.
     * @param min The minimum inventory being assigned
     * */
    public void setMin(int min){
        this.min = min;
    }


    /** This method gets a part's ID.
     * @return Returns the ID
     * */
    public int getId() {
        return id;
    }

    /** This method gets a part's ID.
     * @return Returns the name
     * */
    public String getName() {
        return name;
    }

    /** This method gets a part's price.
     * @return Returns the price
     * */
    public double getPrice() {
        return price;
    }

    /** This method gets a part's stock.
     * @return Returns the stock */
    public int getStock() {
        return stock;
    }

    /** This method gets a part's minimum inventory.
     * @return Returns the minimum inventory */
    public int getMin() {
        return min;
    }

    /** This method gets a part's maximum inventory.
     * @return Returns the maximum inventory */
    public int getMax() {
        return max;
    }
}