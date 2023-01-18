package com.piomar.borders;

import com.piomar.borders.Service.*;
import com.piomar.borders.csvSettings.BasicCsvFile;
import com.piomar.borders.csvSettings.CountryListFile;
import com.piomar.borders.csvSettings.DriversWorkingNumber;
import com.piomar.borders.csvSettings.ReadyCsvFile;

import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static int noCountries = 0;

    public static void main(String[] args) throws IOException, InterruptedException {

        CSVListCreator csvListCreator = new CSVListCreator();
        GetWorkingId getWorkingId = new GetWorkingId();
        CountryListFileCreator countryListFileCreator = new CountryListFileCreator();
        Processor processor = new Processor();
        FinalFileCreator fileCreator = new FinalFileCreator();


        List<DriversWorkingNumber> driversWorkingNumberList =
                getWorkingId.driversWorkingNumberList();

        List<BasicCsvFile> list = csvListCreator.basicCsvFileList().stream()
                .sorted(Comparator.comparing(BasicCsvFile::getTime))
                .collect(Collectors.toList());

        List<CountryListFile> countryList = countryListFileCreator.countryList();

        while(processor.validateFile(list)) {
            if (!processor.validateFile(list)) {
                processor.fixListIfValidateFail(list);
            } else {
                System.out.println("Wszystkie dane są poprawne.");
                break;
            }
        }

        list = list.stream()
                .filter(e -> e.getEvent().equals("border crossing"))
                .collect(Collectors.toList());

        Map<String, List<ReadyCsvFile>> resultMap =
                processor.createHashMap(driversWorkingNumberList,
                                        list,
                                        countryList);

        fileCreator.saveToFile(resultMap);

        System.out.println();
        System.out.println("Brak państwa: " + noCountries);

        System.out.println();
        System.out.println(ANSI_GREEN + "Created by Piotr Tynek");
        System.out.println("tynek.piotr@gmail.com" + ANSI_RESET);
        Thread.sleep(20000);

    }
}