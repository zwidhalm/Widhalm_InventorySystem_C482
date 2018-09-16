package View_Controller;


import Model.InHouse;
import Model.Inventory;
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
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;

public class MainScreenController {

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
    
    private Part tempPart;
    private MainApp mainApp;

    
   
    
    

    @FXML
    void handleMainAddPartEvent(ActionEvent event) throws IOException {
        Part part = tempPart;
        boolean okClicked= mainApp.showAddPartScreen(tempPart);
        if (okClicked) {
            mainApp.getPartData().add(tempPart);
        }
    }
    
    

    @FXML
    void handleMainAddProductEvent(ActionEvent event) throws IOException {
        Product product = new Product();
        boolean okClicked= mainApp.showAddProductScreen(product);
        if (okClicked) {
            mainApp.getProductData().add(product);
        
        }

    }

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
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

    }

    @FXML
    void handleMainModifyPartEvent(ActionEvent event) throws IOException {
        Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
        int partIndex = mainApp.getPartIndex(selectedPart);
        if (selectedPart != null) {
            boolean okClicked = mainApp.showModifyPartScreen(selectedPart);
            if (okClicked) {
            mainApp.updatePart(partIndex,selectedPart);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part in the table.");

            alert.showAndWait();
        }
    }

    

    @FXML
    void handleMainModifyProductsEvent(ActionEvent event) throws IOException {
        Product selectedProduct = mainProductTableView.getSelectionModel().getSelectedItem();
        int productIndex = mainApp.getProductIndex(selectedProduct);
        if (selectedProduct != null) {
            boolean okClicked = mainApp.showModifyProductScreen(selectedProduct);
            if (okClicked) {
            mainApp.updateProduct(productIndex,selectedProduct);
            }

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

    @FXML
    void handleMainPartSeachEvent(ActionEvent event) {

    }

    @FXML
    void handleMainProductsEvent(ActionEvent event) {

    }

    @FXML
    void handleMainScreenExitEvent(ActionEvent event) {

    }
    

    
    

    @FXML
    void initialize() {
        mainPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        mainPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        mainPartInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        mainPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        mainProductsIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        mainProductsNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        mainProductsInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().productStockProperty().asObject());
        mainProductsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        
//        FilteredList<Part> filteredData = new FilteredList<>(masterPartData, p -> true);
//        
//        // 2. Set the filter Predicate whenever the filter changes.
//        mainPartSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(part -> {
//                // If filter text is empty, display all persons.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                
//                // Compare first name and last name of every person with filter text.
//                int partID = Integer.parseInt(newValue);
//                
//                if (part.getPartID() == partID) {
//                    return true; // Filter matches first name.
//                }
//                return false; // Does not match.
//            });
//        });
//        
//        // 3. Wrap the FilteredList in a SortedList. 
//        SortedList<Part> sortedData = new SortedList<>(filteredData);
//        
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        sortedData.comparatorProperty().bind(mainPartTableView.comparatorProperty());
//        
//        // 5. Add sorted (and filtered) data to the table.
//        mainPartTableView.setItems(sortedData);  
       
    }
    
    
    

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        mainPartTableView.setItems(mainApp.getPartData());
        mainProductTableView.setItems(mainApp.getProductData());
    }

    
    
}
