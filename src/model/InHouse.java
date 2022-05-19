package model;

public class InHouse extends Part {

    //field declaration
    private int machineId;

    //constructor
    public InHouse(int id, String name, double price, int stock, int min, int max){
        super(id, name, price, stock, min, max);
    }

    //setter
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    //getter
    public int getMachineId(){
        return machineId;
    }
}
