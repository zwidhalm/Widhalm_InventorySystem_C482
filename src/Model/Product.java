/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Zach
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private IntegerProperty productID = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty inStock = new SimpleIntegerProperty();
    private IntegerProperty min = new SimpleIntegerProperty();
    private IntegerProperty max = new SimpleIntegerProperty();
    
    public Product(){
    }
    
    public Product(int productID, String name, double price, int inStock, int min, int max){
        this.productID = new SimpleIntegerProperty(productID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    
    }

    public final int getProductID() {
        return productIDProperty().get();
    }

    public final void setProductID(int productID) {
        productIDProperty().set(productID);
    }
    
    public IntegerProperty productIDProperty (){
        return productID;
    }

    public final String getName() {
        return productNameProperty().get();
    }

    public void setProductName(String name) {
        productNameProperty().set(name);
    }
    public StringProperty productNameProperty(){
        return name;
    }
    
    public final double getPrice() {
        return productPriceProperty().get();
    }

    public DoubleProperty productPriceProperty() {
        return price;
    }

    public void setProductPrice(double price) {
        productPriceProperty().set(price);
    }

    public final int getInStock() {
        return productStockProperty().get();
    }

    public void setInStock(int inStock) {
        productStockProperty().set(inStock);
    }
    
    public IntegerProperty productStockProperty (){
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
    
    public void addPart(Part part){
        associatedParts.add(part);
    }
    
    public void deletePart(Part part){
        associatedParts.remove(part);
    }    
    
    public ObservableList<Part> getPart(){
        return associatedParts;
    }
    
    public void clearList(){
        associatedParts.clear();
    }
    
    
    
    
    
    
}
