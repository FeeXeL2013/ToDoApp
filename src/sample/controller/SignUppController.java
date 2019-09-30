package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.database.DatabaseHandler;
import sample.model.User;

public class SignUppController {

    @FXML
    private Button signUpApplyButton;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpUserName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    void initialize(){

        signUpApplyButton.setOnAction(event -> {
            createUser();
        });
    }

    private void createUser(){

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUserName.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();

        String gender = "";
        if (signUpCheckBoxFemale.isSelected()){
            gender = "Female";
        }else gender ="Male";

        User user =new User(firstName, lastName, userName, password, location, gender);

        databaseHandler.signUpUsers(user);
    }
}
