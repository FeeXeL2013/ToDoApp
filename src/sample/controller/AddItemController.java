package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Shaker;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;


public class AddItemController {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLabel;

    @FXML
    void initialize() {



        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();
            System.out.println("Added Clicked! ");


            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), notTaskLabel);
            //Сунемо і скриваємо кнопку додати завдання та лейбу
            addButton.relocate(0, 20);
            notTaskLabel.relocate(0, 75);


            addButton.setOpacity(0);
            notTaskLabel.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(2);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();


            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(2);
            labelTransition.setAutoReverse(false);
            labelTransition.play();


            try {

                AnchorPane formPane =
                        FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000),formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
