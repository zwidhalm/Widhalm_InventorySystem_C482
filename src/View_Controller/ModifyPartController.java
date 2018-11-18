package View_Controller;

import Model.MainApp;
import Model.InHouse;
import Model.Part;
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

    //Sets the textfield based on what radio button is selected
    @FXML
    void handleModifyPartInHouseButtonEvent(ActionEvent event) {
        modifyPartDecisionLabel.setText("Machine ID");
        modifyPartDecisionTextField.clear();

    }
    
    
    //Sets the textfield based on what radio button is selected
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





    //Saves a modified part to temporary list, used to send data back to main app
    @FXML
    private void handleModifyPartSaveEvent(ActionEvent event) {
        tempModifiedParts.clear();
        int partID = Integer.parseInt(modifyPartIDTextField.getText());
        String partName = modifyPartNameTextField.getText();
        Double price = Double.parseDouble(modifyPartPriceTextField.getText());
        int max = Integer.parseInt(modifyPartMaxTextField.getText());
        int min = Integer.parseInt(modifyPartMinTextField.getText());
        int inStock = Integer.parseInt(modifyPartInvTextField.getText());

        if ( checkValid() && modifyPartInHouseButtonLabel.isSelected()) {
            int machineID = Integer.parseInt(modifyPartDecisionTextField.getText());
            part = new InHouse(partID,partName, price, inStock, min, max, machineID);
            

            tempModifiedParts.add(part);
            saveClicked = true;
            dialogStage.close();
        }
        else if(checkValid() && modifyPartOutSourcedButtonLabel.isSelected()){
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
    
    
    //displays the part that was selected from main app
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
     * Based on the criteria that min values must be less than max value
     * 
     */
    private boolean checkValid() {

        if (modifyPartMinTextField.getText() == null || modifyPartMinTextField.getText().length() == 0 || 
            Integer.parseInt(modifyPartMinTextField.getText()) > Integer.parseInt(modifyPartMaxTextField.getText())) {
            String errorMessage = "Not a valid Min/Max value: Min Value must be less than Max Value \n";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid TextFields");
            alert.setHeaderText("Please Fix TextField Errors");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;

        }
        return true;


    }
}


   
    



