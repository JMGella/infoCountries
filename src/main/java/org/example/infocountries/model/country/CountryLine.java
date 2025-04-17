package org.example.infocountries.model.country;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryLine {
    private String name;
    private String capital;
    private String region;
    private long population;
    private double area;
    private Image flag;
}
