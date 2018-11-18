package Model;


import View_Controller.AddPartController;
import View_Controller.MainScreenController;
import View_Controller.ModifyPartController;
import View_Controller.AddProductController;
import View_Controller.ModifyProductController;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Product> tempProducts = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Part> tempParts = FXCollections.observableArrayList();



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Managemnent System");
     

        initRootLayout();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
        // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View_Controller/MainScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public ObservableList<Part> getPartData(){
        return allParts;
    }
    
    public ObservableList<Product> getProductData(){
        return products;
    }
    
    public void deleteProduct(Product product){
        products.remove(product);
    }
    
    public void deletePart(Part part){
        allParts.remove(part);
    }
    
    public void addPart(Part part){
        allParts.add(part);
    }
    
    public int getPartIndex(Part part){
        return allParts.indexOf(part);
    }
    
    public void updatePart(int index, Part part){
        allParts.set(index, part);
    }
    
    public int getProductIndex(Product product){
        return products.indexOf(product);
    }
    
    public void updateProduct(int index, Product product){
        products.set(index, product);
    }
    
    public void addProduct(Product product){
        products.add(product);
    }

    
    public Part showAddPartScreen() {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View_Controller/AddPart.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

        // Set the person into the controller.
            AddPartController controller = loader.getController();
            controller.setDialogStage(dialogStage);


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.getPart();
            
        } catch (IOException e) {
            e.printStackTrace();
            return new InHouse();
        }
    }

    
    public Part showModifyPartScreen(Part part) {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View_Controller/ModifyPart.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

        // Set the person into the controller.
            ModifyPartController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPart(part);


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.getModifiedPart();
        } catch (IOException e) {
            e.printStackTrace();
            return part;
        }
    }
    
    public Product showAddProductScreen() {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View_Controller/AddProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

        // Set the person into the controller.
            AddProductController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            //controller.setProduct(this);


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.getProduct();
            
        } catch (IOException e) {
            e.printStackTrace();
            
            return new Product();
        }
    }
        
    public Product showModifyProductScreen(Product product) {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View_Controller/ModifyProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            


        // Set the person into the controller.
            ModifyProductController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
            controller.setMainApp(this);


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.getModifiedProduct();
        } catch (IOException e) {
            e.printStackTrace();
            return product;
        }
    }


 
}