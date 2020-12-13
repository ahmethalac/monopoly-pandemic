package controllers.popupControllers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.City;

public class cityPopupController {

    private City city;

    @FXML
    private Label cityName;
    public Label cityText1;
    public Label cityText2;

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    public void setCity(City city) {
        this.city = city;
        cityName.setText(city.getName());
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
