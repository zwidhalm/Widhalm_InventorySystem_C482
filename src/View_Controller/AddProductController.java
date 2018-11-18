package View_Controller;

import Model.MainApp;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController implements Initializable {
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Part> addProductTableView1;

    @FXML
    private TableColumn<Part, Integer> addProductPartIDTableColumn;

    @FXML
    private TableColumn<Part, String> addProductPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> addProductInventoryColumn;

    @FXML
    private TableColumn<Part, Double> addProductPricePerUnitColumn;

    @FXML
    private TableView<Part> addProductTableView2;

    @FXML
    private TableColumn<Part, Integer> addProductPartIDTableColumn2;

    @FXML
    private TableColumn<Part, String> addProductPartNameColumn2;

    @FXML
    private TableColumn<Part, Integer> addProductInventoryColumn2;

    @FXML
    private TableColumn<Part, Double> addProductPricePerUnitColumn2;

    @FXML
    private Button addProductSearchTableView;

    @FXML
    private TextField addProductSearchTextField;

    @FXML
    private Button addProductSearchTableView2;

    @FXML
    private TextField addProductSearchTextField2;

    @FXML
    private Label addProductLabel;

    @FXML
    private Label addProductIDLabel;

    @FXML
    private Label addProductNameLabel;

    @FXML
    private Label addProductInvLabel;

    @FXML
    private Label addProductPriceLabel;

    @FXML
    private Label addProductMax;

    @FXML
    private TextField addProductIDTextfield;

    @FXML
    private TextField addProductNameTextField;

    @FXML
    private TextField addProductInvTextField;

    @FXML
    private TextField addProductPriceTextField;

    @FXML
    private TextField addProductPriceMaxField;

    @FXML
    private Button addProductAddButtonLabel;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private Button addProductCancelButton;

    @FXML
    private Label addProductMinLabel;

    @FXML
    private TextField addProductMinTextField;
    private Stage dialogStage;
    private boolean saveClicked = false;
    private Product mProduct;
    private MainApp mainApp;
    private ObservableList<Product> tempProducts = FXCollections.observableArrayList();
    private ObservableList<Part> tempParts = FXCollections.observableArrayList();

    
    
    public void initialize(URL location, ResourceBundle resources) {
        addProductPartIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addProductPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        addProductInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        addProductPricePerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        addProductPartIDTableColumn2.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addProductPartNameColumn2.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        addProductInventoryColumn2.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        addProductPricePerUnitColumn2.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        

        
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isSaveClicked(){;
        return saveClicked;
    }

    /**
     * Called when the user clicks the save button.
     */
    @FXML
    private void handleAddProductSaveButtonEvent(ActionEvent event) {
        if (checkValid()) {
            tempProducts.clear();
            int productID =Integer.parseInt(addProductIDTextfield.getText());
            String productName = addProductNameTextField.getText();
            double productPrice = Double.parseDouble(addProductPriceTextField.getText());
            int max = Integer.parseInt(addProductPriceMaxField.getText());
            int min = Integer.parseInt(addProductMinTextField.getText());
            int inStock = Integer.parseInt(addProductInvTextField.getText());
            
            Product product = new Product(productID, productName, productPrice, max, min, inStock);

            addPartToMainList(product);
            tempProducts.add(product);
            tempParts.clear();
            

            saveClicked = true;
            dialogStage.close();
        }
    }
    
    
    //Adds part from main tableview to associated part tableiview
    @FXML
    private void handleAddProductAddButtonEvent(ActionEvent event) {
        Part selectedPart = addProductTableView1.getSelectionModel().getSelectedItem();
        addPartToTempList(selectedPart);
        addProductTableView2.setItems(tempParts);
    }
    
    @FXML
    private void handleAddProductDeleteButtonEvent(ActionEvent event) {
        int selectedIndex = addProductTableView2.getSelectionModel().getSelectedIndex();
        Part selectedPart = addProductTableView2.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            addProductTableView2.getItems().remove(selectedIndex);
            tempParts.remove(selectedPart);
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

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleAddProductCancelButtonEvent(ActionEvent event) {
        dialogStage.close();
    }
    
    public void addPartToMainList(Product product){
        for(Part part : tempParts){
            product.addPart(part);
        }

    }
    
    public void addPartToTempList(Part part){
        tempParts.add(part);   
    }
    

    
    
    /**Checks that input is valid, based on the criteria that 
     * product price cannot be less than parts price
     * 
     */
    
    private boolean checkValid(){
        ObservableList<Part> partData =  addProductTableView2.getItems();
        double price = Double.parseDouble(addProductPriceTextField.getText());
        for(Part part : partData){
            if(part.getPartPrice() > price){
                String errorMessage = "Not a valid Price: Product Price Value must be greater than Part Price Value\n";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid TextFields");
                alert.setHeaderText("Please Fix TextField Errors");
                alert.setContentText(errorMessage);
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
    
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        
        addProductTableView1.setItems(mainApp.getPartData());

    }
    
    public Product getProduct(){
        return tempProducts.get(0);
        
    }
    
    
    //Searches that main Part tableview
    public void handleAddProductSearchTableViewEvent(){
        ObservableList<Part> partData =  addProductTableView1.getItems();
        ObservableList<Part> searchResultData = FXCollections.observableArrayList();
        searchResultData.clear();
        
        int textFieldSearch = 0;
        try{
            textFieldSearch = Integer.parseInt(addProductSearchTextField.getText());
        }
        catch (NumberFormatException nfe){
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
                addProductTableView1.setItems((searchResultData));
            }
        }
            if (searchResultData.isEmpty()){
            // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Match");
                alert.setHeaderText("No Part Found");
                alert.setContentText("The ID entered did not match any ID in the Inventory");

                alert.showAndWait();
                }
    }


    
    // Searches the associated Part Tableview
    public void handleAddProductSearchTableView2Event(){
        ObservableList<Part> partData =  addProductTableView2.getItems();
        ObservableList<Part> searchResultData = FXCollections.observableArrayList();
        searchResultData.clear();
        
        int textFieldSearch = 0;
        try{
            textFieldSearch = Integer.parseInt(addProductSearchTextField2.getText());
        }
        catch (NumberFormatException nfe){
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
                addProductTableView2.setItems((searchResultData));
            }
        }
            if (searchResultData.isEmpty()){
            // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Match");
                alert.setHeaderText("No Part Found");
                alert.setContentText("The ID entered did not match any ID in the Inventory");

                alert.showAndWait();
                }
    }
        


 
}