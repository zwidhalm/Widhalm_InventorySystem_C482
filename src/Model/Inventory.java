/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Zach
 */
public class Inventory {
    
    static  ObservableList<Product> products = FXCollections.observableArrayList();
    static  ObservableList<Product> tempProducts = FXCollections.observableArrayList();
    static  ObservableList<Part> allParts = FXCollections.observableArrayList();
    static  ObservableList<Part> tempParts = FXCollections.observableArrayList();

    private Part part;
 

    public Inventory() {
    }
    
    
    
    public void addProduct(Product product){
        products.add(product);
    }
    
    public boolean removeProduct(int product){
        return false;
    }
    
    public void deleteProduct(Product product){
        products.remove(product);
    }
    
    public void addTempProduct(Product selectedProduct) {
        tempProducts.add(selectedProduct);
    }
    
    public int lookupProduct(int product){
        return product; 
    }
    
    public static void updateProduct(Product product){
       
       
    }
    
    
    public void cancelModifyProduct() {
        for(Product product : tempProducts){
            products.add(product);
        }

    }

    public void clearTempProduct() {
        tempProducts.clear();
    }
    
    public void addPart(Part part){
        allParts.add(part);
    }
    
    public boolean deletePart(int part){
        return false;
    }
    
    public void removePart(Part part){
        allParts.remove(part);
    }
    
    public void addTempPart(Part part){
        tempParts.add(part);
    }
    
    public void clearTempPart(){
        tempParts.clear();
    }
    
    public int lookupPart(int part){
        return part;
    }
    
    public void updatePart(Part part){
    }
    
    public  void cancelModifyPart() {
        
        for(Part part : tempParts){
            allParts.add(part);
        }        
 
    }
    
    
    public ObservableList<Part> showPartsInventory(){
        return allParts;
        
    }
    
    public ObservableList<Product> showProductInventory(){
        return products;
        
    }
    
    public Part getRecentPart(Part part){
        return allParts.get(allParts.size() - 1);
        
    }
    
    public Product getRecentProduct(Product product){
        return products.get(products.size() - 1);
        
    }
    
    public int getPartIndex(Part part){
        int partIndex = allParts.indexOf(part);
        return partIndex;
    }
    
}
