package View_Controller;

import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddProductController {

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
    private Product product;

    //@Override
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleProductSaveEvent(ActionEvent event) {
        if (isInputValid()) {
            product.setProductID(Integer.parseInt(addProductIDTextfield.getText()));
            product.setProductName(addProductNameTextField.getText());
            product.setProductPrice(Double.parseDouble(addProductPriceTextField.getText()));
            product.setMax(Integer.parseInt(addProductPriceMaxField.getText()));
            product.setMin(Integer.parseInt(addProductMinTextField.getText()));
            product.setInStock(Integer.parseInt(addProductInvTextField.getText()));
            

            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleAddProductCancelEvent(ActionEvent event) {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (addProductIDTextfield.getText() == null || addProductIDTextfield.getText().length() == 0) {
            errorMessage += "Not a valid ID\n"; 
        }
        if (addProductNameTextField.getText() == null || addProductNameTextField.getText().length() == 0) {
            errorMessage += "Not a valid Name\n"; 
        }
        if (addProductPriceTextField.getText() == null || addProductPriceTextField.getText().length() == 0) {
            errorMessage += "Not a valid Price\n"; 
        }

        if (addProductPriceMaxField.getText() == null || addProductPriceMaxField.getText().length() == 0) {
            errorMessage += "Not a valid Max value\n"; 
//        } else {
//            // try to parse the postal code into an int.
//            try {
//                Integer.parseInt(addProductPriceMaxField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n"; 
//            }
        }

        if (addProductMinTextField.getText() == null || addProductMinTextField.getText().length() == 0 || 
                Integer.parseInt(addProductMinTextField.getText()) > Integer.parseInt(addProductPriceMaxField.getText())) {
            errorMessage += "Not a valid Min value \n"; 
        }

        if (addProductInvTextField.getText() == null || addProductInvTextField.getText().length() == 0) {
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
}


   
    



