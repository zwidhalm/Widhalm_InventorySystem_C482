/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Zach
 */
public abstract class Part {
    
    protected IntegerProperty partID = new SimpleIntegerProperty();
    protected StringProperty name = new SimpleStringProperty();
    protected DoubleProperty price = new SimpleDoubleProperty();
    protected IntegerProperty inStock = new SimpleIntegerProperty();
    protected IntegerProperty min = new SimpleIntegerProperty();
    protected IntegerProperty max = new SimpleIntegerProperty();
    
    
    
    public Part(){
    }

    public final int getPartID() {
        return partIDProperty().get();
    }

    public final void setPartID(int partID) {
        partIDProperty().set(partID);
    }
    
    public IntegerProperty partIDProperty (){
        return partID;
    }

    public final String getPartName() {
        return partNameProperty().get();
    }

    public void setPartName(String name) {
        partNameProperty().set(name);
    }
    public StringProperty partNameProperty(){
        return name;
    }
    
    public final double getPartPrice() {
        return priceProperty().get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPartPrice(double price) {
        priceProperty().set(price);
    }

    public final int getInStock() {
        return stockProperty().get();
    }

    public void setInStock(int inStock) {
        stockProperty().set(inStock);
    }
    
    public IntegerProperty stockProperty (){
        return inStock;
    }

    public int getMin() {
        return minProperty().get();
    }

    public void setMin(int min) {
        minProperty().set(min);
    }
    
    public IntegerProperty minProperty(){
        return min;
    }

    public int getMax() {
        return maxProperty().get();
    }

    public void setMax(int max) {
        maxProperty().set(max);
    }
    
    public IntegerProperty maxProperty(){
        return max;
    }

    
    
    
    
}
