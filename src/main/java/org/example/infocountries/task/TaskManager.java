package org.example.infocountries.task;


import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.example.infocountries.model.country.CountryLine;
import org.example.infocountries.service.ApiService;
import io.reactivex.rxjava3.functions.Consumer;



public class TaskManager extends Task<Integer> {
    private String countryName;
    private ObservableList<CountryLine> countryList;


    int endpoint;

    public TaskManager(String countryName, int endpoint, ObservableList<CountryLine> countryList) {
        this.countryName = countryName;
        this.endpoint = endpoint;
        this.countryList = countryList;
    }


    @Override
    protected Integer call() throws Exception {
        System.out.println("Ejecutando TaskManager con país: " + countryName + " y endpoint: " + endpoint);

        ApiService apiService = new ApiService();
        Consumer<CountryLine> user = countryLine -> {

            System.out.println("Recibido: " + countryLine.getName());

//            Thread.sleep(200);
            Platform.runLater(() -> countryList.add(countryLine));
        };


        if (endpoint == 1) {
            apiService.getCountryByName(countryName)
                    .subscribe(
                            user,
                            error -> {
                                System.out.println("Error en la llamada: " + error.getMessage());
                                error.printStackTrace();
                            }
                    );


        } else if (endpoint == 2) {
            apiService.getCountryByRegion(countryName)
                    .subscribe(
                            user,
                            error -> {
                                System.out.println("Error en la llamada: " + error.getMessage());
                                error.printStackTrace();
                            }
                    );
        } else {
            System.out.println("Invalid endpoint");
        }


        return 0;
    }
}
