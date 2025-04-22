package org.example.infocountries.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    private String searchString;

    private final ObservableList<CountryLine> countryLines =FXCollections.observableArrayList();

    public TabController(String searchString, int endpoint) {
        this.endpoint = endpoint;
        this.searchString = searchString;
    }


    @FXML
    private void searchInResults(){
    String searchText = tfSearchResults.getText();
    List<CountryLine> filteredList = countryLines.stream()
            .filter(countryLine -> countryLine.getName().toLowerCase().contains(searchText.toLowerCase())
            || countryLine.getCapital().toLowerCase().contains(searchText.toLowerCase())
            || countryLine.getRegion().toLowerCase().contains(searchText.toLowerCase())
            || String.valueOf(countryLine.getPopulation()).contains(searchText)
            || String.valueOf(countryLine.getArea()).contains(searchText))
            .toList();

    tvResults.setItems(FXCollections.observableArrayList(filteredList));


    }

    @FXML
    private void filterResults() {
      boolean filterPopulation = cbPopulation.isSelected();
      boolean filterArea = cbArea.isSelected();
      boolean filterVowel = cbVowel.isSelected();

      List<CountryLine> filteredList = countryLines.stream()
              .filter(countryLine -> {
                  boolean selectedFilter = true;
                  if(filterPopulation) {
                       selectedFilter = selectedFilter && countryLine.getPopulation() > 100000;
                  }
                  if(filterArea) {
                       selectedFilter = selectedFilter && countryLine.getArea() > 100000;
                    }
                  if (filterVowel) {
                       selectedFilter = selectedFilter &&  countryLine.getName().trim().toLowerCase().matches("^[aeiou].*");
                    }

                  return selectedFilter;
              })
              .toList();

        tvResults.setItems(FXCollections.observableArrayList(filteredList));
    }







    void initialize() {

        tcName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        tcCapital.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCapital()));
        tcRegion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRegion()));
        tcPopulation.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPopulation()));
        tcArea.setCellValueFactory(cell -> new  ReadOnlyObjectWrapper<>(cell.getValue().getArea()));
        tcFlag.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getFlag()));

        tvResults.setItems(this.countryLines);


        TaskManager taskManager = new TaskManager(searchString, endpoint, this.countryLines);

        new Thread(taskManager).start();


    }

}
