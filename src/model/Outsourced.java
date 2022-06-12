package model;
/** This class creates outsourced parts for the inventory management system.*/
public class Outsourced extends Part {

    private String companyName;

    /** This is the Outsourced part constructor.
     * @param id Outsourced part ID
     * @param name Outsourced part name
     * @param price Outsourced part price
     * @param stock Outsourced part stock
     * @param min Outsourced part minimum stock
     * @param max Outsourced part maxmium stock
     * @param companyName Outsourced part company name
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** This method sets an outsourced part's company name when called.
     * @param companyName The company name being assigned
     * */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /** This method gets an outsourced part's company name when called.
     *@return Returns company name
     *  */
    public String getCompanyName(){
        return companyName;
    }
}
