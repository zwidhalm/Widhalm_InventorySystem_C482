package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private TextField modifyProductIDTextField;

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
    private Stage dialogStage;
    private boolean saveClicked;
   




    

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
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isSaveClicked(){
        return saveClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleProductSaveEvent(ActionEvent event) {
        if (isInputValid()) {
            product.setProductID(Integer.parseInt(modifyProductIDTextField.getText()));
            product.setProductName(modifyProductNameTextField.getText());
            product.setProductPrice(Double.parseDouble(modifyProductPriceTextField.getText()));
            product.setMax(Integer.parseInt(modifyProductPriceMaxField.getText()));
            product.setMin(Integer.parseInt(modifyProductMinTextField.getText()));
            product.setInStock(Integer.parseInt(modifyProductInvTextField.getText()));
            

            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleAppPartCancelEvent(ActionEvent event) {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (modifyProductIDTextField.getText() == null || modifyProductIDTextField.getText().length() == 0) {
            errorMessage += "Not a valid ID\n"; 
        }
        if (modifyProductNameTextField.getText() == null || modifyProductNameTextField.getText().length() == 0) {
            errorMessage += "Not a valid Name\n"; 
        }
        if (modifyProductPriceTextField.getText() == null || modifyProductPriceTextField.getText().length() == 0) {
            errorMessage += "Not a valid Price\n"; 
        }

        if (modifyProductPriceMaxField.getText() == null || modifyProductPriceMaxField.getText().length() == 0) {
            errorMessage += "Not a valid Max value\n"; 
//        } else {
//            // try to parse the postal code into an int.
//            try {
//                Integer.parseInt(addProductPriceMaxField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n"; 
//            }
        }

        if (modifyProductMinTextField.getText() == null || modifyProductMinTextField.getText().length() == 0 || 
                Integer.parseInt(modifyProductMinTextField.getText()) > Integer.parseInt(modifyProductPriceMaxField.getText())) {
            errorMessage += "Not a valid Min value \n"; 
        }

        if (modifyProductInvTextField.getText() == null || modifyProductInvTextField.getText().length() == 0) {
            errorMessage += "Not a valid Inventory value\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid TextFields");
            alert.setHeaderText("Please Fix TextField Errors");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    public void setProduct(Product product) {
        this.product = product;
        
        
        modifyProductIDTextField.setText(Integer.toString(product.getProductID()));
        modifyProductNameTextField.setText(product.getName());
        modifyProductPriceTextField.setText(Double.toString(product.getPrice()));
        modifyProductPriceMaxField.setText(Integer.toString(product.getMax()));
        modifyProductMinTextField.setText(Integer.toString(product.getMin()));
        
    }
   
}

        
    



   
    



