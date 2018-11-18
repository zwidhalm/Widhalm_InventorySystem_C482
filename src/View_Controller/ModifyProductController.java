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

public class ModifyProductController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Part> modifyProductTableView1;
    
    @FXML
    private TableView<Part> modifyProductTableView2;

    @FXML
    private TableColumn<Part, Integer> modifyProductPartIDTableColumn;

    @FXML
    private TableColumn<Part, String> modifyProductPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> modifyProductInventoryColumn;

    @FXML
    private TableColumn<Part, Double> modifyProductPricePerUnitColumn;

    @FXML
    private TableColumn<Part, Integer> modifyProductPartIDTableColumn2;

    @FXML
    private TableColumn<Part, String> modifyProductPartNameColumn2;

    @FXML
    private TableColumn<Part, Integer> modifyProductInventoryColumn2;
    
    @FXML
    private TableColumn<Part, Double> modifyProductPricePerUnitColumn2;

    @FXML
    private Button modifyProductSearchTableView;

    @FXML
    private TextField modifyProductSearchTextField;

    @FXML
    private Button modifyProductSearchTableView2;

    @FXML
    private TextField modifyProductSearchTextField2;

    @FXML
    private Label modifyProductLabel;

    @FXML
    private Label modifyProductIDLabel;

    @FXML
    private Label modifyProductNameLabel;

    @FXML
    private Label modifyProductInvLabel;

    @FXML
    private Label modifyProductPriceLabel;

    @FXML
    private Label modifyProductMax;

    @FXML
    private Label modifyProductCompanyName;

    @FXML
    private TextField modifyProductIDTextfield;

    @FXML
    private TextField modifyProductNameTextField;

    @FXML
    private TextField modifyProductInvTextField;

    @FXML
    private TextField modifyProductPriceTextField;

    @FXML
    private TextField modifyProductPriceMaxField;

    @FXML
    private TextField modifyProductCompanyNameTextField;

    @FXML
    private Button modifyProductAddLabel;

    @FXML
    private Button modifyProductDeleteButton;

    @FXML
    private Button modifyProductSaveButton;

    @FXML
    private Button modifyProductCancelButton;

    @FXML
    private Label modifyProductMinLabel;

    @FXML
    private TextField modifyProductMinTextField;
    
    private Product product;
    private MainApp mainApp;
    private Stage dialogStage;
    private boolean saveClicked;
    private ObservableList<Product> tempModifiedProducts = FXCollections.observableArrayList();
    private ObservableList<Part> tempParts = FXCollections.observableArrayList();


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modifyProductPartIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        modifyProductPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        modifyProductInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        modifyProductPricePerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        modifyProductPartIDTableColumn2.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        modifyProductPartNameColumn2.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        modifyProductInventoryColumn2.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        modifyProductPricePerUnitColumn2.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        

        
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
     * Called when the user clicks save.
     */
    @FXML
    private void handleModifyProductSaveButtonEvent(ActionEvent event) {
        if (checkValid()) {
            tempModifiedProducts.clear();
            int productID = Integer.parseInt(modifyProductIDTextfield.getText());
            String productName = modifyProductNameTextField.getText();
            double productPrice = Double.parseDouble(modifyProductPriceTextField.getText());
            int max = Integer.parseInt(modifyProductPriceMaxField.getText());
            int min = Integer.parseInt(modifyProductMinTextField.getText());
            int inStock = Integer.parseInt(modifyProductInvTextField.getText());
            
            product = new Product(productID, productName, productPrice, max, min, inStock);
            
            updateAssociatedParts(product);
            tempModifiedProducts.add(product);
            tempParts.clear();
            
            

            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleModifyProductCancelButtonEvent(ActionEvent event) {
        dialogStage.close();
    }
    
    
    @FXML
    private void handleModifyProductDeleteButtonEvent(ActionEvent event){
        int selectedIndex = modifyProductTableView2.getSelectionModel().getSelectedIndex();
        Part selectedPart = modifyProductTableView2.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            modifyProductTableView2.getItems().remove(selectedIndex);
            tempParts.remove(selectedPart);
            modifyProductTableView2.setItems(getTempPart());
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
    
    //Adds parts from main tableview to associated parts tableview
    
    @FXML
    private void handleModifyProductAddButtonEvent(ActionEvent event){
        Part selectedPart = modifyProductTableView1.getSelectionModel().getSelectedItem();
        tempParts.add(selectedPart);
        modifyProductTableView2.setItems(getTempPart());
        
    }

    //Checks that input is valid based on the criteria that products price must be greater than parts price
    private boolean checkValid(){
        ObservableList<Part> partData =  modifyProductTableView2.getItems();
        double price = Double.parseDouble(modifyProductPriceTextField.getText());
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
    
    public void setProduct(Product product) {

        modifyProductIDTextfield.setText(Integer.toString(product.getProductID()));
        modifyProductNameTextField.setText(product.getName());
        modifyProductPriceTextField.setText(Double.toString(product.getPrice()));
        modifyProductPriceMaxField.setText(Integer.toString(product.getMax()));
        modifyProductMinTextField.setText(Integer.toString(product.getMin()));
        modifyProductInvTextField.setText(Integer.toString(product.getInStock()));
        modifyProductTableView2.setItems(product.getPart());

        
    }
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        modifyProductTableView1.setItems(mainApp.getPartData());

    }
    
    public Product getModifiedProduct(){
        return tempModifiedProducts.get(0);
    }
    
    public void addProduct(Product product){
        tempModifiedProducts.add(product);
    }
    
    public ObservableList<Part> getTempPart(){
        return tempParts;
    }

    private void updateAssociatedParts(Product product) {
        product.clearList();
        for (Part part : tempParts){
            product.addPart(part);
        }
    }
    
    public void handleModifyProductSearchTableView1(){
        ObservableList<Part> partData =  modifyProductTableView1.getItems();
        ObservableList<Part> searchResultData = FXCollections.observableArrayList();
        
        
        int textFieldSearch = 0;
            try{
            textFieldSearch = Integer.parseInt(modifyProductSearchTextField.getText());
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
                    searchResultData.clear();
                    searchResultData.add(part);
                    modifyProductTableView1.setItems((searchResultData));
                
                }
                else {
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
    
    public void handleModifyProductSearchTableView2(){
        ObservableList<Part> partData =  modifyProductTableView2.getItems();
        ObservableList<Part> searchResultData = FXCollections.observableArrayList();
        
        int textFieldSearch = 0;
            try{
            textFieldSearch = Integer.parseInt(modifyProductSearchTextField.getText());
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
                    searchResultData.clear();
                    searchResultData.add(part);
                    modifyProductTableView2.setItems((searchResultData));
                
                }
                else {
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
    
    public void handleAddProductSearchTableView2(){
        ObservableList<Part> partData =  modifyProductTableView2.getItems();
        
        try{
            int textFieldSearch = Integer.parseInt(modifyProductSearchTextField2.getText());
            for (Part part : partData){
                if(part.getPartID() == textFieldSearch){
                    partData.clear();
                    partData.add(part);
                    modifyProductTableView2.setItems((partData));
                
                }
                else {
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
        catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Match");
            alert.setHeaderText("No Part Found");
            alert.setContentText("The ID entered was not valid");

            alert.showAndWait();  
        }
    }
    
    
    

   
}

        
    



   
    



