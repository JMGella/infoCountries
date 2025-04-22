package org.example.infocountries.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.example.infocountries.controller.TabController;
import org.example.infocountries.model.country.CountryLine;


import java.io.IOException;
import java.util.List;

public class AppController {

    @FXML
    private TextField tfApiSearch;
    @FXML
    private Button btSearchByName;
    @FXML
    private Button btSearchByRegion;
    @FXML
    private TabPane tpResults;

    private List<CountryLine> countryLines;

    private int endpoint;

    @FXML
    protected void searchByName() throws IOException {
        endpoint = 1;
        String countryName = tfApiSearch.getText().trim();
        if (countryName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No hay país para buscar");
            alert.setHeaderText("Introduce el nombre de un país");
            alert.setContentText("Introduce el nombre de un pais o parte de él.");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/infocountries/tab_view.fxml"));
        TabController tabController = new TabController(countryName, endpoint);
        fxmlLoader.setController(tabController);
        Tab tab = new Tab(countryName);
        tab.setContent(fxmlLoader.load());
        tpResults.getTabs().add(tab);
        tpResults.getSelectionModel().select(tab);
        tab.setClosable(true);
        tabController.initialize();


    }

    @FXML
    protected void searchByRegion() throws IOException {
    endpoint = 2;
    String regionName = tfApiSearch.getText().trim();
        if (regionName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No hay región para buscar");
            alert.setHeaderText("Introduce el nombre de una región");
            alert.setContentText("Introduce el nombre de una región o parte de él.");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/infocountries/tab_view.fxml"));
        TabController tabController = new TabController(regionName, endpoint);
        fxmlLoader.setController(tabController);
        Tab tab = new Tab(regionName);
        tab.setContent(fxmlLoader.load());
        tpResults.getTabs().add(tab);
        tpResults.getSelectionModel().select(tab);
        tab.setClosable(true);
        tabController.initialize();
    }



}