package org.example.infocountries.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.infocountries.model.country.CountryLine;

public class TabController {
    @FXML
    private Button btSearch;
    @FXML
    private TextField tfSearchResults;
    @FXML
    private CheckBox cbPopulation;
    @FXML
    private CheckBox cbArea;
    @FXML
    private CheckBox cbVowel;
    @FXML
    private Button btFilter;

    @FXML
    private TableView<CountryLine> tvResults;

    @FXML
    private TableColumn<CountryLine, String> tcName;
    @FXML
    private TableColumn<CountryLine, String> tcCapital;
    @FXML
    private TableColumn<CountryLine, String> tcRegion;
    @FXML
    private TableColumn<CountryLine, Long> tcPopulation;
    @FXML TableColumn<CountryLine, Double> tcArea;
    @FXML TableColumn<CountryLine, Image> tcFlag;


    @FXML
    private void searchInResults(){

    }

    @FXML
    private void filterResults(){

    }







    private void initialize() {
        tcName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        tcCapital.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCapital()));
        tcRegion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRegion()));
        tcPopulation.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPopulation()));
        tcArea.setCellValueFactory(cell -> new  ReadOnlyObjectWrapper<>(cell.getValue().getArea()));
        tcFlag.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getFlag()));
        tcFlag.setCellFactory(col -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(32);
                imageView.setFitHeight(20);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });
    }

}
