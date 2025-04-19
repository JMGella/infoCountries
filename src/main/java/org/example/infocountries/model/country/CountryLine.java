package org.example.infocountries.model.country;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryLine {
    private String name;
    private String capital;
    private String region;
    private long population;
    private double area;
    private ImageView flag;

    public CountryLine(String name, String capital, String region, long population, double area, Image flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.area = area;
        this.flag = new ImageView(flag.getUrl());

        this.flag.setFitWidth(32);
        this.flag.setFitHeight(20);
        this.flag.setPreserveRatio(true);
    }
}
