package org.example.infocountries.model.country;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private Name name;
    private List<String> capital = List.of("Sin capital");  // Capital por defecto ya que hay paises antárticos sin capital.
    private String region;
    private double area;
    private long population;
    private Flags flags;
}
