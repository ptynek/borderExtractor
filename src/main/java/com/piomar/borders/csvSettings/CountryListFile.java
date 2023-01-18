package com.piomar.borders.csvSettings;

import com.opencsv.bean.CsvBindByPosition;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CountryListFile {

    @CsvBindByPosition(position = 0)
    private String shortCountry;
    @CsvBindByPosition(position = 1)
    private String fullCountry;

}
