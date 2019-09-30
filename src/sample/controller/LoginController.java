package sample.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;
import sample.model.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginSignUpUsername;

    @FXML
    private TextField loginSignUpPassword;

    @FXML
    private Button loginSignUpLogin;

    @FXML
    private Button loginSignUpButton;

    private DatabaseHandler databaseHandler;


    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginSignUpLogin.setOnAction(event -> {//Використовюєм обекти юзернаме та пароля

            String loginUser = loginSignUpUsername.getText().trim();
            String loginPass = loginSignUpPassword.getText().trim();

            User user = new User();
            user.setUserName(loginUser);
            user.setPassword(loginPass);//Створили обекти Введення юзернаме і пароля

            ResultSet userRow =  databaseHandler.getUser(user);

            int counter = 0;

            try {
                while (userRow.next()){
                    counter++;

                    String name = userRow.getString("firstName");

                    System.out.println("Welcome! " + name);
                }
                if (counter == 1){
                    showAddItemScreen();

                }else{
                    Shaker userNameShaker = new Shaker(loginSignUpUsername); // струсити якщо логін не знайдено в БД
                    Shaker passwordShaker = new Shaker(loginSignUpPassword);
                    userNameShaker.shake();
                    passwordShaker.shake();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        loginSignUpButton.setOnAction(event -> {  //Перводим юзера на сторінку реестрації

            loginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root =  loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void showAddItemScreen(){
        loginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/additem.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root =  loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}