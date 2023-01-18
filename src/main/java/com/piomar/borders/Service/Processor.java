package com.piomar.borders.Service;

import com.piomar.borders.Main;
import com.piomar.borders.csvSettings.BasicCsvFile;
import com.piomar.borders.csvSettings.CountryListFile;
import com.piomar.borders.csvSettings.DriversWorkingNumber;
import com.piomar.borders.csvSettings.ReadyCsvFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Processor {

    private static String exitDate;
    private static String exitTime;

        public boolean validateFile(final List<BasicCsvFile> list){

            for(BasicCsvFile record:list){
                if(record.getCountry() == null || record.getCountry().equals("")
                        && record.getEvent().equals("border crossing")){
                    System.out.println(record);
                    return false;
                }
            }
            return true;
        }

        public void fixListIfValidateFail(List<BasicCsvFile> list){
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getCountry() == null || list.get(i).getCountry().equals("")
                && list.get(i).getEvent().equals("border crossing")){
                    String country = findCountry(list, list.get(i).getDriver(), i);
                    list.get(i).setCountry(country);
                }
            }
        }

        private String findCountry(List<BasicCsvFile> list, String driver, int i){
                int x = 0;
                for(BasicCsvFile record:list){
                    if(record.getDriver().equals(driver) && x > i){
                        return record.getCountry();
                    }
                    x ++;
                }
            return "";
        }

        public Map<String, List<ReadyCsvFile>> createHashMap(
                List<DriversWorkingNumber> driversWorkingNumbers,
                List<BasicCsvFile> basicCsvFileList,
                List<CountryListFile> countryList){

            List<ReadyCsvFile> readyCsvFiles = new ArrayList<>();
            Map<String, List<ReadyCsvFile>> resultList = new HashMap<>();

            driversWorkingNumbers
                    .forEach(driver -> {
                        for (BasicCsvFile record : basicCsvFileList) {
                            if(record.getDriver().equals(driver.getName())) {
                                readyCsvFiles.add(new ReadyCsvFile(
                                        findFullNameCountry(countryList, record.getCountry()),
                                        getDay(record.getTime()),
                                        getTime(record.getTime()),
                                        "",
                                        "",
                                        1
                                ));
                            }
                        }
                        if(!readyCsvFiles.isEmpty() && readyCsvFiles.size() > 1){
                            resultList.put(driver.getWorkingId(), new ArrayList<>(readyCsvFiles));
                        } else if (readyCsvFiles.size() == 1){
                            System.out.println("Kierowca " + driver.getName() + " miał jeden wpis i został pominięty!");
                        }
                        readyCsvFiles.clear();
                    });

            return resultList;
        }

        private LocalDateTime convertToLDT(String date){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            return LocalDateTime.parse(date,formatter);
        }

        private Date convertToDate(LocalDateTime dateToConvert){
            return Timestamp.valueOf(dateToConvert);
        }
        private String getDay(String date){
            return date.substring(0, date.indexOf(" "));
        }

        private String getTime(String date){
            return date
                    .substring(date.indexOf(" "))
                    .replace(" ", "");
        }

        private String findFullNameCountry(final List<CountryListFile> countryList, final String shortCountry){
                for(CountryListFile country: countryList){
                    if(shortCountry.equals(country.getShortCountry())){
                        return country.getFullCountry();
                    }
                }
            Main.noCountries += 1;
            return shortCountry;
        }


}
