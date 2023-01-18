package com.piomar.borders.Service;

import com.opencsv.CSVParserWriter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.piomar.borders.csvSettings.ReadyCsvFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FinalFileCreator {

    private File file = new File(System.getProperty("user.home") + "\\Desktop\\borders.csv");

    public void saveToFile(Map<String, List<ReadyCsvFile>> resultMap) throws IOException {

        FileWriter myWriter = new FileWriter(file);
        createFile(myWriter);
        ReadyCsvFile previous = new ReadyCsvFile();

        for (Map.Entry<String, List<ReadyCsvFile>> entry :resultMap.entrySet()) {

            Iterator<ReadyCsvFile> iterator = entry.getValue().iterator();
            while (iterator.hasNext()) {
                ReadyCsvFile record = iterator.next();
                if(previous.getCountry() != null) {
                    previous.setExitDate(record.getEntryDate());
                    previous.setExitTime(record.getEntryTime());
                    writeFile(myWriter, entry.getKey(), previous);
                }
                previous = record;


            }
            previous = new ReadyCsvFile();
        }
        myWriter.close();
    }

    private void writeFile(FileWriter myWriter, String driverId, ReadyCsvFile record) throws IOException {
        myWriter.write(
                driverId + ";" +
                    record.getCountry() + ";" +
                        record.getEntryDate() + ";" +
                        record.getEntryTime() + ";" +
                        record.getExitDate() + ";" +
                        record.getExitTime() + ";" +
                        record.getDelegation() + "\n"

        );
    }

    private void createFile(FileWriter myWriter) throws IOException {
        myWriter.write("Numer służbowy;Państwo odcinka zagranicznego;" +
                "Data wjazdu ;Godzina wjazdu;Data wyjazdu;Godzina wyjazdu;Uwzględnij jako delegowanie" + "\n");
    }
}
