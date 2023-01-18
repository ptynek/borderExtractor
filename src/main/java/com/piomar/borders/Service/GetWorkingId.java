package com.piomar.borders.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.piomar.borders.csvSettings.BasicCsvFile;
import com.piomar.borders.csvSettings.DriversWorkingNumber;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetWorkingId {

    public List<DriversWorkingNumber> driversWorkingNumberList() throws FileNotFoundException {
        String path = System.getProperty("user.home") + "\\Desktop\\Granice\\Kierowcy.csv";
        return  new CsvToBeanBuilder(new FileReader(path))
                .withSkipLines(3)
                .withIgnoreEmptyLine(true)
                .withSeparator(';')
                .withType(DriversWorkingNumber.class).build().parse();
    }

}
