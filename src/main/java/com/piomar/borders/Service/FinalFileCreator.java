package com.piomar.borders.Service;

import com.piomar.borders.csvSettings.ReadyCsvFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FinalFileCreator {

    private File file = new File(System.getProperty("user.home") + "\\Desktop\\borders.csv");

    public void saveToFile(Map<String, List<ReadyCsvFile>> resultMap) throws IOException {

        createFile();

        ReadyCsvFile previous = new ReadyCsvFile();

        for (Map.Entry<String, List<ReadyCsvFile>> entry :resultMap.entrySet()) {

            Iterator<ReadyCsvFile> iterator = entry.getValue().iterator();
            System.out.println("ID: " + entry.getKey());
            while (iterator.hasNext()) {
                ReadyCsvFile record = iterator.next();
                if(previous.getCountry() != null) {
                    previous.setExitDate(record.getEntryDate());
                    previous.setExitTime(record.getEntryTime());
                    System.out.println("    " + previous);
                }
                previous = record;


            }
            previous = new ReadyCsvFile();
        }
    }

    private void writeFile(final String id, final ReadyCsvFile record){

    }

    private File createFile() throws IOException {

        FileWriter myWriter = new FileWriter(file);
        myWriter.write("Numer służbowy;Państwo odcinka zagranicznego;" +
                "Data wjazdu ;Godzina wjazdu;Data wyjazdu;Godzina wyjazdu;Uwzględnij jako delegowanie" + "\n");
        myWriter.close();

        return file;
    }
}
