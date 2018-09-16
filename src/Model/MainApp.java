package Model;

import static Model.Inventory.products;
import View_Controller.AddPartController;
import View_Controller.MainScreenController;
import View_Controller.ModifyPartController;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private  ObservableList<Product> products = FXCollections.observableArrayList();
    private  ObservableList<Product> tempProducts = FXCollections.observableArrayList();
    private  ObservableList<Part> allParts = FXCollections.observableArrayList();
    private  ObservableList<Part> tempParts = FXCollections.observableArrayList();
    


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

//        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
        // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
        
        // Set person overview into the center of root layout.
            //rootLayout.setCenter(personOverview);

        // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
//    public void showPersonOverview() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//            
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(personOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
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
    public boolean showAddPartScreen(Part part) {
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

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean showModifyPartScreen(Part part) {
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


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public boolean showAddProductScreen(Product Product) {
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
            AddPartController controller = loader.getController();
            controller.setDialogStage(dialogStage);


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        public boolean showModifyProductScreen(Product Product) {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View_Controller/ModifyProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

        // Set the person into the controller.
            AddPartController controller = loader.getController();
            controller.setDialogStage(dialogStage);


        // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getPartIndex(Part part){
        return allParts.indexOf(part);
    }
    
    public void updatePart(int index, Part part){
        allParts.set(index, part);
    }

 
}