package View_Controller;

import Model.MainApp;
import Model.InHouse;
import Model.Part;
import Model.Inventory;
//import Model.MainApp;
import Model.OutSourced;
import View_Controller.MainScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import java.lang.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class AddPartController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addPartLabel;

    @FXML
    private RadioButton addPartInHouseButtonLabel;

    @FXML
    private RadioButton addPartOutSourcedButtonLabel;

    @FXML
    private Label addPartIDLabel;

    @FXML
    private Label addPartNameLabel;

    @FXML
    private Label addPartInvLabel;

    @FXML
    private Label addPartPriceLabel;

    @FXML
    private Label addPartMaxLabel;

    @FXML
    private Label addPartDecisionLabel;

    @FXML
    private TextField addPartIDTextField;

    @FXML
    private TextField addPartNameTextField;

    @FXML
    private TextField addPartInvTextField;

    @FXML
    private TextField addPartPriceTextField;

    @FXML
    private TextField addPartMaxTextField;

    @FXML
    private TextField addPartDecisionTextField;

    @FXML
    private TextField addPartMinTextField;

    @FXML
    private Label addPartMinLabel;

    @FXML
    private Button addPartSaveLabel;

    @FXML
    private Button addPartCancelLabel;
    
    private ToggleGroup radioButtonToggleGroup;
    private Part part;
    private InHouse inHousePart;
    private OutSourced outSourcedPart;
    private Stage dialogStage;
    private boolean saveClicked = false;
    private MainApp mainApp;
    private  ObservableList<Part> tempParts = FXCollections.observableArrayList();


    @FXML
    void handleAddPartInHouseButtonEvent(ActionEvent event) {
        addPartDecisionLabel.setText("Machine ID");
        addPartDecisionTextField.clear();

    }

    @FXML
    void handleAddPartOutSourcedButtonEvent(ActionEvent event) {
        addPartDecisionLabel.setText("Company Name");
        addPartDecisionTextField.clear();
    }
    
    public void addPart(Part part){
        tempParts.add(part);
    }


    

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Makes it so that only one radio button is selected at a time
        radioButtonToggleGroup = new ToggleGroup();
        this.addPartInHouseButtonLabel.setToggleGroup(radioButtonToggleGroup);
        this.addPartOutSourcedButtonLabel.setToggleGroup(radioButtonToggleGroup);
        this.addPartInHouseButtonLabel.setSelected(true);
        addPartDecisionLabel.setText("Machine ID");
        
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
    private void handleAddPartSaveEvent(ActionEvent event) {
        int partID = Integer.parseInt(addPartIDTextField.getText());
        String partName = addPartNameTextField.getText();
        Double price = Double.parseDouble(addPartPriceTextField.getText());
        int max = Integer.parseInt(addPartMaxTextField.getText());
        int min = Integer.parseInt(addPartMinTextField.getText());
        int inStock = Integer.parseInt(addPartInvTextField.getText());
        if ( addPartInHouseButtonLabel.isSelected()) {
            int machineID = Integer.parseInt(addPartDecisionTextField.getText());
            part = new InHouse(partID,partName, price, max, min, inStock, machineID);
            
            tempParts.add(part);
            saveClicked = true;
            dialogStage.close();
        }
        else if(addPartOutSourcedButtonLabel.isSelected()){
            String companyName = addPartDecisionTextField.getText();
            part = new OutSourced(partID,partName, price, max, min, inStock, companyName);
            
            tempParts.add(part);
            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleAddPartCancelEvent(ActionEvent event) {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (addPartIDTextField.getText() == null || addPartIDTextField.getText().length() == 0) {
            errorMessage += "Not a valid ID\n"; 
        }
        if (addPartNameTextField.getText() == null || addPartNameTextField.getText().length() == 0) {
            errorMessage += "Not a valid Name\n"; 
        }
        if (addPartPriceTextField.getText() == null || addPartPriceTextField.getText().length() == 0) {
            errorMessage += "Not a valid Price\n"; 
        }

        if (addPartMaxTextField.getText() == null || addPartMaxTextField.getText().length() == 0) {
            errorMessage += "Not a valid Max value\n"; 
//        } else {
//            // try to parse the postal code into an int.
//            try {
//                Integer.parseInt(addPartMaxTextField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n"; 
//            }
        }

        if (addPartMinTextField.getText() == null || addPartMinTextField.getText().length() == 0 || 
                Integer.parseInt(addPartMinTextField.getText()) > Integer.parseInt(addPartMaxTextField.getText())) {
            errorMessage += "Not a valid Min value \n"; 
        }

        if (addPartInvTextField.getText() == null || addPartInvTextField.getText().length() == 0) {
            errorMessage += "Not valid Inventory Value\n";
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
    
    public Part getPart(){
        return tempParts.get(0);
        
    }
    

}


   
    



