package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.*;
import utils.ColorUtil;
import utils.OfferUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AgreementPopupController extends PopupController implements Initializable {
    @FXML
    public ComboBox<String> choosePlayerBox;
    @FXML
    public ComboBox<String> chooseOfferBox;
    @FXML
    public ComboBox<String> chooseOppositeOfferBox;
    @FXML
    public VBox chooseOppositeOfferVBox;
    @FXML
    public VBox chooseOfferVBox;

    private Agreement agreement;
    private Player player;

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    void submitButtonClicked(ActionEvent event) throws IllegalAccessException, InstantiationException {
        String offerType = chooseOfferBox.getSelectionModel().getSelectedItem();
        System.out.println(offerType);

        City city = new City(123, new double[]{1, 2, 3, 4, 5}, "asd", 3);
        Offer offer;

        if (offerType.equals("Sell Region")) {
            offer = new SellRegion(city);
        } else if (offerType.equals("Give Money")) {
            offer = new GiveMoney(123);
        } else if (offerType.equals("Pay Rent or Not")) {
            offer = new PayRentOrNot(city);
        } else if (offerType.equals("Take Percentage")) {
            offer = new TakePercentage(city, 12);
        }
        closeStage(event);
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("aggrement popup initialized");

        // convert from player to string list
        List<String> players = GameManager.getInstance().getPlayers()
                .stream().map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        // remove current player from list
        players.remove(GameManager.getInstance().getCurrentPlayer().getName());

        choosePlayerBox.setItems(FXCollections.observableArrayList(players));
        chooseOfferBox.setItems(FXCollections.observableArrayList(OfferUtil.offers));
        chooseOppositeOfferBox.setItems(FXCollections.observableArrayList(OfferUtil.offers));

        chooseOfferBox.setDisable(true);

        choosePlayerBox.setOnAction(actionEvent -> {
            chooseOfferBox.setDisable(false);
        });

        chooseOfferBox.setOnAction(actionEvent -> {
            String offer = chooseOfferBox.getValue();
            if (offer.equals("Sell Region") || offer.equals("Pay Rent or Not")) {

                //player = choosePlayerBox.getSelectionModel().getSelectedItem();
                player = GameManager.getInstance().getCurrentPlayer();

                // convert from city to string list
                List<String> cities = player.getCities().stream()
                        .map(object -> Objects.toString(object, null))
                        .collect(Collectors.toList());

                // create citybox
                ComboBox<String> cityBox = new ComboBox<>();
                cityBox.setPromptText("Select city");
                cityBox.setItems(FXCollections.observableArrayList(cities));

                // add ui elements
                chooseOfferVBox.getChildren().clear();
                chooseOfferVBox.getChildren().addAll(chooseOfferBox, cityBox);
            } else if (offer.equals("Give Money")) {
                TextField tf = new TextField();
                tf.setPromptText("Enter money...");
                chooseOfferVBox.getChildren().clear();
                chooseOfferVBox.getChildren().addAll(chooseOfferBox, tf);
            } else if (offer.equals("Take Percentage")) {

                player = GameManager.getInstance().getCurrentPlayer();

                // convert from city to string list
                List<String> cities = player.getCities().stream()
                        .map(object -> Objects.toString(object, null))
                        .collect(Collectors.toList());

                // create citybox
                ComboBox<String> cityBox = new ComboBox<>();
                cityBox.setPromptText("Select city");
                cityBox.setItems(FXCollections.observableArrayList(cities));


                TextField percentageField = new TextField();
                percentageField.setPromptText("Enter percentage...");

                chooseOfferVBox.getChildren().clear();
                chooseOfferVBox.getChildren().addAll(chooseOfferBox, cityBox, percentageField);
            }
        });

        chooseOppositeOfferBox.setOnAction(actionEvent -> {
            String offer = chooseOppositeOfferBox.getValue();
            if (offer.equals("Sell Region") || offer.equals("Pay Rent or Not")) {

                // assign current player
                player = GameManager.getInstance().getCurrentPlayer();

                // convert from city to string list
                List<String> cities = player.getCities().stream()
                        .map(object -> Objects.toString(object, null))
                        .collect(Collectors.toList());

                // create citybox
                ComboBox<String> cityBox = new ComboBox<>();
                cityBox.setPromptText("Select city");
                cityBox.setItems(FXCollections.observableArrayList(cities));

                // add ui elements
                chooseOppositeOfferVBox.getChildren().clear();
                chooseOppositeOfferVBox.getChildren().addAll(chooseOppositeOfferBox, cityBox);
            } else if (offer.equals("Give Money")) {
                TextField tf = new TextField();
                tf.setPromptText("Enter money...");
                chooseOppositeOfferVBox.getChildren().clear();
                chooseOppositeOfferVBox.getChildren().addAll(chooseOppositeOfferBox, tf);
            } else if (offer.equals("Take Percentage")) {

                player = GameManager.getInstance().getCurrentPlayer();

                // convert from city to string list
                List<String> cities = player.getCities().stream()
                        .map(object -> Objects.toString(object, null))
                        .collect(Collectors.toList());

                // create citybox
                ComboBox<String> cityBox = new ComboBox<>();
                cityBox.setPromptText("Select city");
                cityBox.setItems(FXCollections.observableArrayList(cities));


                TextField percentageField = new TextField();
                percentageField.setPromptText("Enter percentage...");

                chooseOppositeOfferVBox.getChildren().clear();
                chooseOppositeOfferVBox.getChildren().addAll(chooseOppositeOfferBox, cityBox, percentageField);
            }
        });
    }
}
