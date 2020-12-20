package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.*;
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

        ArrayList<Player> playerList = GameManager.getInstance().getPlayers();
        // convert from player to string list
        List<String> playerNames = playerList
                .stream().map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        // remove current player from list
        playerNames.remove(GameManager.getInstance().getCurrentPlayer().getName());

        choosePlayerBox.setItems(FXCollections.observableArrayList(playerNames));
        chooseOfferBox.setItems(FXCollections.observableArrayList(OfferUtil.offers));
        chooseOppositeOfferBox.setItems(FXCollections.observableArrayList(OfferUtil.offers));

        chooseOfferBox.setDisable(true);

        choosePlayerBox.setOnAction(actionEvent -> {
            chooseOfferBox.setDisable(false);
        });

        chooseOfferBox.setOnAction(actionEvent -> {
            String offer = chooseOfferBox.getValue();

            String playerName = choosePlayerBox.getSelectionModel().getSelectedItem();
            Player player = null;

            for (Player p : playerList){
                if (p.getName().equals(playerName)) player = p;
            }

            assignBoxes(offer, player, chooseOfferVBox, chooseOfferBox);
        });

        chooseOppositeOfferBox.setOnAction(actionEvent -> {
            String offer = chooseOppositeOfferBox.getValue();
            Player player = GameManager.getInstance().getCurrentPlayer();
            assignBoxes(offer, player, chooseOppositeOfferVBox, chooseOppositeOfferBox);
        });
    }

    private void assignBoxes(String offer, Player player, VBox chooseOfferVBox, ComboBox<String> chooseOfferBox) {
        switch (offer) {
            case "Sell Region", "Pay Rent or Not" -> {

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
            }
            case "Give Money" -> {
                TextField tf = new TextField();
                tf.setPromptText("Enter money...");
                chooseOfferVBox.getChildren().clear();
                chooseOfferVBox.getChildren().addAll(chooseOfferBox, tf);
            }
            case "Take Percentage" -> {
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
        }
    }
}
