/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Zach
 */
public class OutSourced extends Part {
    private static StringProperty companyName = new SimpleStringProperty();

    public OutSourced(int partID, String name, double price, int inStock, int min, int max, String companyName){
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.companyName = new SimpleStringProperty(companyName);
        
    }

    public OutSourced() {
    }
    
    

    public static String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyID(StringProperty companyID) {
        this.companyName = companyID;
    }
    
    public static StringProperty companyNameProperty(){
        return companyName;
    }
    
    
    
}
