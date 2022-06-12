package model;
/** This class creates in-house parts for the inventory management system.*/
public class InHouse extends Part {

    private int machineId;

    /** This is the InHouse part constructor.
     * @param id In-house part ID
     * @param name In-house part name
     * @param price In-house part price
     * @param stock In-house part stock
     * @param min In-house part minimum stock
     * @param max In-house part maximum stock
     * @param machineId In-house part machine ID
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /** This method sets the machine ID for the in-house parts when called.
     * @param machineId The machine id being assigned
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /** This method gets the machine ID for the in-house parts when called.
     * @return Returns the machine ID */
    public int getMachineId(){
        return machineId;
    }
}
