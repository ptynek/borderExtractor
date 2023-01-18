package com.piomar.borders.csvSettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class ReadyCsvFile {

    private String country;
    private String entryDate;
    private String entryTime;
    private String exitDate;
    private String exitTime;
    private int delegation;


}
