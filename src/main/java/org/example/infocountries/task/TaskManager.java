package org.example.infocountries.task;


import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.example.infocountries.model.country.CountryLine;
import org.example.infocountries.service.ApiService;
import io.reactivex.rxjava3.functions.Consumer;



public class TaskManager extends Task<Integer> {
    private String searchString;
    private ObservableList<CountryLine> countryList;


    int endpoint;

    public TaskManager(String searchString, int endpoint, ObservableList<CountryLine> countryList) {
        this.searchString = searchString;
        this.endpoint = endpoint;
        this.countryList = countryList;
    }


    @Override
    protected Integer call() throws Exception {
        System.out.println("Ejecutando TaskManager con país: " + searchString + " y endpoint: " + endpoint);

        ApiService apiService = new ApiService();
        Consumer<CountryLine> user = countryLine -> {
            //Cuando se recibe el resultado, se añade a la lista de países
           Thread.sleep(200);
            Platform.runLater(() -> countryList.add(countryLine));

        };


        if (endpoint == 1) {
            //Llamada a la API para buscar por nombre, subscribiendo al observable
            apiService.getCountryByName(searchString)
                    .subscribe(
                            user,
                            error -> {
                                System.out.println("Error en la llamada: " + error.getMessage());
                                error.printStackTrace();
                            }
                    );


        } else if (endpoint == 2) {
            //Llamada a la API para buscar por región, subscribiendo al observable
            apiService.getCountryByRegion(searchString)
                    .subscribe(
                            user,
                            error -> {
                                System.out.println("Error en la llamada: " + error.getMessage());
                                error.printStackTrace();
                            }
                    );
        }

        return 0;
    }
}
