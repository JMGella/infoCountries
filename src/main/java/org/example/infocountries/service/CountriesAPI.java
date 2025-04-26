package org.example.infocountries.service;

import io.reactivex.rxjava3.core.Observable;
import org.example.infocountries.model.country.Country;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CountriesAPI {


    @GET("name/{name}")
    Observable<List<Country>> getCountryByName(@Path("name") String name);

    @GET("region/{region}")
    Observable<List<Country>> getCountryByRegion(@Path("region") String region);

}
