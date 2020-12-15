package controllers.popupControllers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import models.City;

public class cityPopupController {


    private City city;

    @FXML
    private Label cityNameLabel;
    @FXML
    private Label cityPriceLabel;
    @FXML
    public ListView<BorderPane> rentView;

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    public void setCity(City city) {
        this.city = city;
        cityNameLabel.setText(city.getName());
        cityPriceLabel.setText("Price: " + city.getPrice());
        BorderPane[] rentBoxList = new BorderPane[6];
        double[] rents = city.getBuildings().getRents();
        Label label0 = new Label("Site only ");
        Label label1 = new Label("1 House ");
        Label label2 = new Label("2 Houses ");
        Label label3 = new Label("3 Houses ");
        Label label4 = new Label("4 Houses ");
        Label label5 = new Label("Hotel ");

        for (int i = 0; i < 6; i++) {
            rentBoxList[i] = new BorderPane();
            rentBoxList[i].setRight(new Label("" + rents[i]));
        }

        rentBoxList[0].setLeft(label0);
        rentBoxList[1].setLeft(label1);
        rentBoxList[2].setLeft(label2);
        rentBoxList[3].setLeft(label3);
        rentBoxList[4].setLeft(label4);
        rentBoxList[5].setLeft(label5);

        ObservableList<BorderPane> rentList = FXCollections.observableArrayList(rentBoxList);
        rentView.setItems(rentList);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
