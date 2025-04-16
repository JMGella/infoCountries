package org.example.infocountries.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.infocountries.model.country.Country;
import org.example.infocountries.model.country.CountryLine;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {

    private String BASE_URL = "https://restcountries.com/v3.1/";
    private CountriesAPI countriesAPI;


    public ApiService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.countriesAPI = retrofit.create(CountriesAPI.class);
    }

    public @NonNull Observable<CountryLine> getCountryByName(String name) {
        return countriesAPI.getCountryByName(name)
                .flatMapIterable(country -> country)
                .map(country -> new CountryLine(
                        country.getName().getCommon(),
                        country.getCapital().get(0),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getArea(),
                        country.getFlags().getPng()
                ));
    }

    public Observable<Country> getCountryByRegion(String region) {
        return countriesAPI.getCountryByRegion(region)
                .flatMapIterable(country -> country);
    }

}
