package org.example.infocountries.controller;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.infocountries.model.country.CountryLine;
import org.example.infocountries.task.TaskManager;

import java.util.List;

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
    @FXML TableColumn<CountryLine, ImageView> tcFlag;

    private int endpoint;

    private String countryName;

    private final ObservableList<CountryLine> countryLines =FXCollections.observableArrayList();

    public TabController(String countryName, int endpoint) {
        this.endpoint = endpoint;
        this.countryName = countryName;
    }


    @FXML
    private void searchInResults(){
//TODO
    }

    @FXML
    private void filterResults(){
//TODO
    }







    void initialize() {

        tcName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        tcCapital.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCapital()));
        tcRegion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRegion()));
        tcPopulation.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPopulation()));
        tcArea.setCellValueFactory(cell -> new  ReadOnlyObjectWrapper<>(cell.getValue().getArea()));
        tcFlag.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getFlag()));

        tvResults.setItems(this.countryLines);


        TaskManager taskManager = new TaskManager(countryName, endpoint, this.countryLines);

        new Thread(taskManager).start();


    }

}
