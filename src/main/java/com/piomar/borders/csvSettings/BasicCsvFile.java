package com.piomar.borders.csvSettings;


import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class BasicCsvFile {

    @CsvBindByPosition(position = 0)
    private String event;
    @CsvBindByPosition(position = 1)
    private String time;
    @CsvBindByPosition(position = 2)
    private String driver;
    @CsvBindByPosition(position = 8)
    private String country;

}
