package org.example.infocountries.model.country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flags {
    private String png;
    private String svg;
    private String alt;
}
