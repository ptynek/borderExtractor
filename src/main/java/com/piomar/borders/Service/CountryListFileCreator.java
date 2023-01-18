package com.piomar.borders.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.piomar.borders.csvSettings.CountryListFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CountryListFileCreator {

    public List<CountryListFile> countryList() throws FileNotFoundException {
        String path = System.getProperty("user.home") + "\\Desktop\\Granice\\country.csv";
        return new CsvToBeanBuilder(new FileReader(path))
                .withSeparator(';')
                .withType(CountryListFile.class).build().parse();
    }
}
