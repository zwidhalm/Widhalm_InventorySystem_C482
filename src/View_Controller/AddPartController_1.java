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
    private MainApp mainApp;
    private Inventory mInventory;
    private boolean savedClick;
   

    @FXML
    void handleAddPartCancelEvent(ActionEvent event) throws IOException {


    }

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

    @FXML
    void handleAddPartSaveEvent(ActionEvent event) throws IOException {
        if (isValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }

    }
    
    public void createPart(){
  

    
    }
    
        
 
    public boolean isValid(){
        return addPartIDTextField != null && addPartNameTextField != null
                && addPartInvTextField != null && addPartPriceTextField != null && addPartMaxTextField != null && addPartMinTextField != null
                && addPartDecisionTextField != null;
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Makes it so that only one radio button is selected at a time
        radioButtonToggleGroup = new ToggleGroup();
        this.addPartInHouseButtonLabel.setToggleGroup(radioButtonToggleGroup);
        this.addPartOutSourcedButtonLabel.setToggleGroup(radioButtonToggleGroup);
        this.addPartInHouseButtonLabel.setSelected(true);
        addPartDecisionLabel.setText("Machine ID");
        
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
     public boolean isSaveClicked() {

        return saveClicked;
    }



   
    
}


