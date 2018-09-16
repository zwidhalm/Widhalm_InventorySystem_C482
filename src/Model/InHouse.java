/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Zach
 */
public class InHouse extends Part {
    private static IntegerProperty machineID = new SimpleIntegerProperty();
    
    
    public InHouse(){ 
    }

    public InHouse(int partID, String name, double price, int inStock, int min, int max, int machineID){
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.machineID = new SimpleIntegerProperty(machineID);
        
    }

    
    

    public static int getMachineID() {
        return machineID.get();
    }

    public void setMachineID(IntegerProperty machineID) {
        this.machineID = machineID;
    }
    
    public IntegerProperty machineIDProperty() {
        return machineID;
    }
    
    
    
}
