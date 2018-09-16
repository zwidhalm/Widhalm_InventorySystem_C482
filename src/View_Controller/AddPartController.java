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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import java.lang.String;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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
    private Stage dialogStage;
    private boolean saveClicked;
   


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
    private void handlePartSaveEvent(ActionEvent event) {
        if (isInputValid()) {
            part.setPartID(Integer.parseInt(addPartIDTextField.getText()));
            part.setPartName(addPartNameTextField.getText());
            part.setPartPrice(Double.parseDouble(addPartPriceTextField.getText()));
            part.setMax(Integer.parseInt(addPartMaxTextField.getText()));
            part.setMin(Integer.parseInt(addPartMinTextField.getText()));
            part.setInStock(Integer.parseInt(addPartInvTextField.getText()));
            

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

        if (addPartIDTextField.getText() == null || addPartIDTextField.getText().length() == 0) {
            errorMessage += "Not a valid ID\n"; 
        }
        if (addPartNameTextField.getText() == null || addPartNameTextField.getText().length() == 0) {
            errorMessage += "Not a valid name\n"; 
        }
        if (addPartPriceTextField.getText() == null || addPartPriceTextField.getText().length() == 0) {
            errorMessage += "Not a valid price\n"; 
        }

        if (addPartMaxTextField.getText() == null || addPartMaxTextField.getText().length() == 0) {
            errorMessage += "Not a valid max value\n"; 
//        } else {
//            // try to parse the postal code into an int.
//            try {
//                Integer.parseInt(addPartMaxTextField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n"; 
//            }
        }

        if (addPartMinTextField.getText() == null || addPartMinTextField.getText().length() == 0 || addPartMinTextField.getText()) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}


   
    



