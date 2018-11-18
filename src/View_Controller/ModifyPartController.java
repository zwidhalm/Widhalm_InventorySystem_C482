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
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

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
    private ObservableList<Part> tempModifiedParts = FXCollections.observableArrayList();

    
    @FXML
    void handleModifyPartInHouseButtonEvent(ActionEvent event) {
        modifyPartDecisionLabel.setText("Machine ID");
        modifyPartDecisionTextField.clear();

    }

    @FXML
    void handleModifyPartOutSourcedButtonEvent(ActionEvent event) {
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
//        this.modifyPartInHouseButtonLabel.setSelected(true);
//        this.modifyPartDecisionLabel.setText("Machine ID");
        
    }
    
    public Part getModifiedPart(){
        return tempModifiedParts.get(0);
        
    }
    
    public void addPart(Part part){
        tempModifiedParts.add(part);
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
    private void handleModifyPartSaveEvent(ActionEvent event) {
        tempModifiedParts.clear();
        int partID = Integer.parseInt(modifyPartIDTextField.getText());
        String partName = modifyPartNameTextField.getText();
        Double price = Double.parseDouble(modifyPartPriceTextField.getText());
        int max = Integer.parseInt(modifyPartMaxTextField.getText());
        int min = Integer.parseInt(modifyPartMinTextField.getText());
        int inStock = Integer.parseInt(modifyPartInvTextField.getText());

        if ( isInputValid() && modifyPartInHouseButtonLabel.isSelected()) {
            int machineID = Integer.parseInt(modifyPartDecisionTextField.getText());
            part = new InHouse(partID,partName, price, inStock, min, max, machineID);
            

            tempModifiedParts.add(part);
            saveClicked = true;
            dialogStage.close();
        }
        else if(isInputValid() && modifyPartOutSourcedButtonLabel.isSelected()){
            String companyName = modifyPartDecisionTextField.getText();
            part = new OutSourced(partID, partName, price, inStock, min, max, companyName);
            
            tempModifiedParts.add(part);
            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleModifyPartCancelEvent(ActionEvent event) {
        tempModifiedParts.clear();
        dialogStage.close();
    }
    
    public void setPart(Part part) {
        this.part = part;
        
        
        modifyPartIDTextField.setText(Integer.toString(part.getPartID()));
        modifyPartNameTextField.setText(part.getPartName());
        modifyPartInvTextField.setText(Integer.toString(part.getInStock()));
        modifyPartPriceTextField.setText(Double.toString(part.getPartPrice()));
        modifyPartMaxTextField.setText(Integer.toString(part.getMax()));
        modifyPartMinTextField.setText(Integer.toString(part.getMin()));
        if ( part instanceof InHouse){
            this.modifyPartInHouseButtonLabel.setSelected(true);
            this.modifyPartDecisionLabel.setText("Machine ID");
            modifyPartDecisionTextField.setText(Integer.toString(((InHouse) part).getMachineID()));     
        } else if (part instanceof OutSourced){
            this.modifyPartOutSourcedButtonLabel.setSelected(true);
            this.modifyPartDecisionLabel.setText("Company Name");
            modifyPartDecisionTextField.setText(((OutSourced) part).getCompanyName());
            
        }
        
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        ArrayList<String> errorList = new ArrayList<String>();

        if (modifyPartIDTextField.getText() == null || modifyPartIDTextField.getText().length() == 0) {
            errorMessage += "Not a valid ID\n";
            errorList.add(errorMessage);
        }
        if (modifyPartNameTextField.getText() == null || modifyPartNameTextField.getText().length() == 0) {
            errorMessage += "Not a valid Name\n";
            errorList.add(errorMessage);
        }
        if (modifyPartPriceTextField.getText() == null || modifyPartPriceTextField.getText().length() == 0) {
            errorMessage += "Not a valid Price\n";
            errorList.add(errorMessage);
        }

        if (modifyPartMaxTextField.getText() == null || modifyPartMaxTextField.getText().length() == 0) {
            errorMessage += "Not a valid Max value\n";
            errorList.add(errorMessage);
//        } else {
//            // try to parse the postal code into an int.
//            try {
//                Integer.parseInt(addPartMaxTextField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n"; 
//            }
        }

        if (modifyPartMinTextField.getText() == null || modifyPartMinTextField.getText().length() == 0 || 
                Integer.parseInt(modifyPartMinTextField.getText()) > Integer.parseInt(modifyPartMaxTextField.getText())) {
            errorMessage += "Not a valid Min value \n";
            errorList.add(errorMessage);
        }

        if (modifyPartInvTextField.getText() == null || modifyPartInvTextField.getText().length() == 0) {
            errorMessage += "Not valid Inventory Value\n";
            errorList.add(errorMessage);
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid TextFields");
            alert.setHeaderText("Please Fix TextField Errors");
            for (String error : errorList){
                alert.setContentText(errorMessage);
            }
            
            alert.showAndWait();
            
            return false;
        }
    }
}


   
    



