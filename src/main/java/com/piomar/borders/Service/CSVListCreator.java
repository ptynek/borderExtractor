package com.piomar.borders.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.piomar.borders.csvSettings.BasicCsvFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CSVListCreator {
    public List<BasicCsvFile> basicCsvFileList() throws FileNotFoundException {
        String path = System.getProperty("user.home") + "\\Desktop\\alarm.csv";
        return  new CsvToBeanBuilder(new FileReader(path))
                .withSkipLines(1)
                .withSeparator(';')
                .withType(BasicCsvFile.class).build().parse();
    }
}
