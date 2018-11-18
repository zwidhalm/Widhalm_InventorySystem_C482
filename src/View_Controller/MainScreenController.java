package View_Controller;


import Model.InHouse;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.MainApp;
import Model.OutSourced;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;



public class MainScreenController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Part> mainPartTableView;

    @FXML
    private TableColumn<Part, Integer> mainPartIDColumn;

    @FXML
    private TableColumn<Part, String> mainPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> mainPartInventoryColumn;

    @FXML
    private TableColumn<Part, Double> mainPartPriceColumn;

    @FXML
    private Label mainPartsLabel;

    @FXML
    private Button mainPartSeachLabel;

    @FXML
    private TextField mainPartSearchTextField;

    @FXML
    private Button mainPartAdd;

    @FXML
    private Button mainPartModify;

    @FXML
    private Button mainPartDelete;

    @FXML
    private TableView<Product> mainProductTableView;

    @FXML
    private TableColumn<Product, Integer> mainProductsIDColumn;

    @FXML
    private TableColumn<Product, String> mainProductsNameColumn;

    @FXML
    private TableColumn<Product, Integer> mainProductsInventoryColumn;

    @FXML
    private TableColumn<Product, Double> mainProductsPriceColumn;

    @FXML
    private Label mainProductsLabel;

    @FXML
    private Button mainProductsSearch;

    @FXML
    private TextField mainProductsSearchTextfield;

    @FXML
    private Button mainProductsAddLabel;

    @FXML
    private Button mainModifyProductsLabel;

    @FXML
    private Button MainScreentExitLabel;
    
    //private Part part;
    private MainApp mainApp;
    

    //opens the add part screen through reference to main app
    @FXML
    void handleMainAddPartEvent(ActionEvent event) throws IOException {
        populateTable();
        Part part = mainApp.showAddPartScreen();
        mainApp.addPart(part);

    }
    
    //opens the add product screen through refernce to main app
    @FXML
    void handleMainAddProductEvent(ActionEvent event) throws IOException {
        populateTable();
        Product product = mainApp.showAddProductScreen();
        mainApp.addProduct(product);

    }
    
    //deletes selected part
    @FXML
    void handleMainDeletePartEvent(ActionEvent event) {
        int selectedIndex = mainPartTableView.getSelectionModel().getSelectedIndex();
        Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            mainPartTableView.getItems().remove(selectedIndex);
            mainApp.deletePart(selectedPart);
        } else {
        // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

    }
    
    //deletes selected product
    @FXML
    void handleMainProductDeleteEvent(ActionEvent event){
        int selectedIndex = mainProductTableView.getSelectionModel().getSelectedIndex();
        Product selectedProduct = mainProductTableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            mainProductTableView.getItems().remove(selectedIndex);
            mainApp.deleteProduct(selectedProduct);
        } else {
        // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }

    }
    //opens Modify Part screen through reference to main app
    @FXML
    void handleMainModifyPartEvent(ActionEvent event) throws IOException {
        populateTable();
        Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
        int partIndex = mainApp.getPartIndex(selectedPart);
        if (selectedPart != null) {
            Part part = mainApp.showModifyPartScreen(selectedPart);
            mainApp.updatePart(partIndex, part);
            //populateTable();
            }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
        populateTable();
    }

    
    //opens Modify Product screen through reference to main app
    @FXML
    void handleMainModifyProductsEvent(ActionEvent event) throws IOException {
        populateTable();
        Product selectedProduct = mainProductTableView.getSelectionModel().getSelectedItem();
        int productIndex = mainApp.getProductIndex(selectedProduct);
        if (selectedProduct != null) {
            Product product = mainApp.showModifyProductScreen(selectedProduct);
            mainApp.updateProduct(productIndex, product);
            }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
        populateTable();
    }
    /**
     * 
     * Searches through the Parts tableview and displays results 
     */
    @FXML
    void handleMainPartSeachEvent(ActionEvent event) {
        ObservableList<Part> partData =  mainPartTableView.getItems();
        ObservableList<Part> searchResultData = FXCollections.observableArrayList();
        searchResultData.clear();
        
        int textFieldSearch = 0;
            try{
            textFieldSearch = Integer.parseInt(mainPartSearchTextField.getText());
            } catch (NumberFormatException nfe){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Match");
                alert.setHeaderText("No Part Found");
                alert.setContentText("The ID entered was not valid");

                alert.showAndWait();
                
            }
            for (Part part : partData){
                if(part.getPartID() == textFieldSearch){
                    searchResultData.add(part);
                    mainPartTableView.setItems((searchResultData));
                }
            }
            if(searchResultData.isEmpty()) {
            // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Match");
                alert.setHeaderText("No Part Found");
                alert.setContentText("The ID entered did not match any ID in the Inventory");

                alert.showAndWait();
                }
        }
    
            
        
     


    /**
     * 
     * Searches through the Products tableview and displays results 
     */
    @FXML
    void handleMainProductsSearchEvent(ActionEvent event) {
        ObservableList<Product> productData =  mainProductTableView.getItems();
        ObservableList<Product> searchResultData = FXCollections.observableArrayList();
        searchResultData.clear();
        
        int textFieldSearch = 0;
            try{
            textFieldSearch = Integer.parseInt(mainProductsSearchTextfield.getText());
            } catch (NumberFormatException nfe){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Match");
                alert.setHeaderText("No Product Found");
                alert.setContentText("The ID entered was not valid");

                alert.showAndWait();
                
            }
            for (Product product : productData){
                if(product.getProductID() == textFieldSearch){
                    searchResultData.add(product);
                    mainProductTableView.setItems((searchResultData));
                }
            }
            if(searchResultData.isEmpty()) {
            // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Match");
                alert.setHeaderText("No Product Found");
                alert.setContentText("The ID entered did not match any ID in the Inventory");

                alert.showAndWait();
                }

    }

    @FXML
    void handleMainScreenExitEvent(ActionEvent event) {
        Platform.exit();

    }
    

    
    //references Main App to disply current Part and Product data
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        mainPartTableView.setItems(mainApp.getPartData());
        mainProductTableView.setItems(mainApp.getProductData());
    }
    
    public void populateTable(){
        mainPartTableView.setItems(mainApp.getPartData());
        mainProductTableView.setItems(mainApp.getProductData());
    }
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        mainPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        mainPartInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        mainPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        mainProductsIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        mainProductsNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        mainProductsInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().productStockProperty().asObject());
        mainProductsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        
    }
    
    
}
        


  
    

    
    

