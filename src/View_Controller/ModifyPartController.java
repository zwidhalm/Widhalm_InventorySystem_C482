package View_Controller;

import Model.InHouse;
import Model.OutSourced;
import Model.Part;
import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyPartController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label modifyPartLabel;

    @FXML
    private RadioButton modifyPartInHouseButtonLabel;

    @FXML
    private RadioButton modifyPartOutSourcedButtonLabel;

    @FXML
    private Label modifyPartIDLabel;

    @FXML
    private Label modifyPartNameLabel;

    @FXML
    private Label modifyPartInvLabel;

    @FXML
    private Label modifyPartPriceLabel;

    @FXML
    private Label modifyPartMaxLabel;

    @FXML
    private Label modifyPartDecisionLabel;

    @FXML
    private TextField modifyPartIDTextField;

    @FXML
    private TextField modifyPartNameTextField;

    @FXML
    private TextField modifyPartInvTextField;

    @FXML
    private TextField modifyPartPriceTextField;

    @FXML
    private TextField modifyPartMaxTextField;

    @FXML
    private TextField modifyPartDecisionTextField;

    @FXML
    private TextField modifyPartMinTextField;

    @FXML
    private Label modifyPartMinLabel;

    @FXML
    private Button modifyPartSaveLabel;

    @FXML
    private Button modifyPartCancelLabel;
    
    private InHouse inHousePart;
    private OutSourced outSourcedPart;
    private ToggleGroup modifyPartRadioButtonToggleGroup;
    private Part part;
    private Stage dialogStage;
    private boolean saveClicked;

    
        @FXML
    void handleAddPartInHouseButtonEvent(ActionEvent event) {
        modifyPartDecisionLabel.setText("Machine ID");
        modifyPartDecisionTextField.clear();

    }

    @FXML
    void handleAddPartOutSourcedButtonEvent(ActionEvent event) {
        modifyPartDecisionLabel.setText("Company Name");
        modifyPartDecisionTextField.clear();
    }


    

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Makes it so that only one radio button is selected at a time
        ToggleGroup radioButtonToggleGroup = new ToggleGroup();
        this.modifyPartInHouseButtonLabel.setToggleGroup(radioButtonToggleGroup);
        this.modifyPartOutSourcedButtonLabel.setToggleGroup(radioButtonToggleGroup);
        this.modifyPartInHouseButtonLabel.setSelected(true);
        this.modifyPartDecisionLabel.setText("Machine ID");
        
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
            part.setPartID(Integer.parseInt(modifyPartIDTextField.getText()));
            part.setPartName(modifyPartNameTextField.getText());
            part.setPartPrice(Double.parseDouble(modifyPartPriceTextField.getText()));
            part.setMax(Integer.parseInt(modifyPartMaxTextField.getText()));
            part.setMin(Integer.parseInt(modifyPartMinTextField.getText()));
            part.setInStock(Integer.parseInt(modifyPartInvTextField.getText()));
            

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
    
    public void setPart(Part part) {
        this.part = part;

        modifyPartIDTextField.setText((part.getPartID()));
        modifyPartNameTextField.setText(part.getPartName());
        modifyPartPriceTextField.setText((part.getPartPrice()));
        modifyPartMaxTextField.setText((part.getMax()));
        modifyPartMinTextField.setText((part.getMin()));
        
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (modifyPartIDTextField.getText() == null || modifyPartIDTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (modifyPartNameTextField.getText() == null || modifyPartNameTextField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (modifyPartPriceTextField.getText() == null || modifyPartPriceTextField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (modifyPartMaxTextField.getText() == null || modifyPartMaxTextField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(modifyPartMaxTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
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


   
    



