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
    @FXML
    private TextField agreementNameField;

    private Player currentPlayer;
    private Player player2;
    private City city1;
    private City city2;
    private ComboBox<String> cityBox1;
    private ComboBox<String> cityBox2;
    private TextField moneyField1;
    private TextField moneyField2;
    private TextField percentageField1;
    private TextField percentageField2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("aggrement popup initialized");

        currentPlayer = GameManager.getInstance().getCurrentPlayer();

        ArrayList<Player> playerList = GameManager.getInstance().getPlayers();
        // convert from player to string list
        List<String> playerNames = playerList
                .stream().map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        // remove current player from list
        playerNames.remove(currentPlayer.getName());


        agreementNameField.setPromptText("Enter agreement name...");
        agreementNameField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background,-30%);");

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

            for (Player p : playerList) {
                if (p.getName().equals(playerName)) player2 = p;
            }

            switch (offer) {
                case "Sell Region", "Pay Rent or Not" -> {
                    ArrayList<City> cityList = player2.getCities();
                    List<String> cities = cityList.stream()
                            .map(object -> Objects.toString(object, null))
                            .collect(Collectors.toList());

                    cityBox2 = new ComboBox<>();
                    cityBox2.setPromptText("Select city");
                    cityBox2.setItems(FXCollections.observableArrayList(cities));

                    cityBox2.setOnAction(actionEvent1 -> {
                        String cityName = cityBox2.getSelectionModel().getSelectedItem();

                        for (City c : cityList) {
                            if (c.getName().equals(cityName)) city2 = c;
                        }
                    });

                    // add ui elements
                    chooseOfferVBox.getChildren().clear();
                    chooseOfferVBox.getChildren().addAll(chooseOfferBox, cityBox2);
                }
                case "Give Money" -> {
                    moneyField2 = new TextField();
                    moneyField2.setPromptText("Enter money...");
                    chooseOfferVBox.getChildren().clear();
                    chooseOfferVBox.getChildren().addAll(chooseOfferBox, moneyField2);
                }
                case "Take Percentage" -> {
                    // convert from city to string list
                    ArrayList<City> cityList = player2.getCities();
                    List<String> cities = cityList.stream()
                            .map(object -> Objects.toString(object, null))
                            .collect(Collectors.toList());

                    // create citybox
                    cityBox2 = new ComboBox<>();
                    cityBox2.setPromptText("Select city");
                    cityBox2.setItems(FXCollections.observableArrayList(cities));

                    cityBox2.setOnAction(actionEvent1 -> {
                        String cityName = cityBox2.getSelectionModel().getSelectedItem();

                        for (City c : cityList) {
                            if (c.getName().equals(cityName)) city2 = c;
                        }
                    });

                    percentageField2 = new TextField();
                    percentageField2.setPromptText("Enter percentage...");

                    chooseOfferVBox.getChildren().clear();
                    chooseOfferVBox.getChildren().addAll(chooseOfferBox, cityBox2, percentageField2);
                }
            }
        });

        chooseOppositeOfferBox.setOnAction(actionEvent -> {
            String offer = chooseOppositeOfferBox.getValue();
            switch (offer) {
                case "Sell Region", "Pay Rent or Not" -> {
                    ArrayList<City> cityList = currentPlayer.getCities();
                    List<String> cities = cityList.stream()
                            .map(object -> Objects.toString(object, null))
                            .collect(Collectors.toList());

                    cityBox1 = new ComboBox<>();
                    cityBox1.setPromptText("Select city");
                    cityBox1.setItems(FXCollections.observableArrayList(cities));

                    cityBox1.setOnAction(actionEvent1 -> {
                        String cityName = cityBox1.getSelectionModel().getSelectedItem();

                        for (City c : cityList) {
                            if (c.getName().equals(cityName)) city1 = c;
                        }
                    });

                    // add ui elements
                    chooseOppositeOfferVBox.getChildren().clear();
                    chooseOppositeOfferVBox.getChildren().addAll(chooseOppositeOfferBox, cityBox1);
                }
                case "Give Money" -> {
                    moneyField1 = new TextField();
                    moneyField1.setPromptText("Enter money...");
                    chooseOppositeOfferVBox.getChildren().clear();
                    chooseOppositeOfferVBox.getChildren().addAll(chooseOppositeOfferBox, moneyField1);
                }
                case "Take Percentage" -> {
                    // convert from city to string list
                    ArrayList<City> cityList = currentPlayer.getCities();
                    List<String> cities = cityList.stream()
                            .map(object -> Objects.toString(object, null))
                            .collect(Collectors.toList());

                    // create citybox
                    cityBox1 = new ComboBox<>();
                    cityBox1.setPromptText("Select city");
                    cityBox1.setItems(FXCollections.observableArrayList(cities));

                    cityBox1.setOnAction(actionEvent1 -> {
                        String cityName = cityBox1.getSelectionModel().getSelectedItem();

                        for (City c : cityList) {
                            if (c.getName().equals(cityName)) city1 = c;
                        }
                    });

                    percentageField1 = new TextField();
                    percentageField1.setPromptText("Enter percentage...");

                    chooseOppositeOfferVBox.getChildren().clear();
                    chooseOppositeOfferVBox.getChildren().addAll(chooseOppositeOfferBox, cityBox1, percentageField1);
                }
            }
        });
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    void submitButtonClicked(ActionEvent event) {
        String oppositeOfferType = chooseOfferBox.getSelectionModel().getSelectedItem();
        String offerType = chooseOppositeOfferBox.getSelectionModel().getSelectedItem();
        String agreementName = agreementNameField.getText();

        Offer offer = getOffer(offerType, city1, moneyField1, percentageField1, currentPlayer);
        Offer oppositeOffer = getOffer(oppositeOfferType, city2, moneyField2, percentageField2, player2);

        // create agreement
        GameManager.getInstance().newAgreement(offer, oppositeOffer, currentPlayer, player2, agreementName);

        closeStage(event);
    }

    private Offer getOffer(String oppositeOfferType, City city, TextField moneyField, TextField percentageField, Player giver) {
        Offer offer = switch (oppositeOfferType) {
            case "Sell Region" -> new SellRegion(city);
            case "Give Money" -> new GiveMoney(Integer.parseInt(moneyField.getText()),giver);
            case "Pay Rent or Not" -> new PayRentOrNot(city);
            case "Take Percentage" -> new TakePercentage(city, Integer.parseInt(percentageField.getText()));
            default -> throw new IllegalStateException("Unexpected value: " + oppositeOfferType);
        };
        return offer;
    }
}
