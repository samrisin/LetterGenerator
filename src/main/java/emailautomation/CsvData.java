package emailautomation;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/** CsvData class converts csv file into readable format */
public class CsvData {
    HashMap<Integer,Map<String,String>> csvData =new HashMap<>();
    final String defaultPath="src/main/resources/";

    /** csv sata contructor
     @param csvFile represents csv file name
      @return  csvdata returns csv data in readable format* */
    public HashMap<Integer, Map<String, String>> getCsvData(String csvFile) {
        List<String[]> list = new ArrayList<>();
        Path path = FileSystems.getDefault().getPath(defaultPath+csvFile);
        if (Files.exists(path)) {
            try {
                CSVReader csvReader = new CSVReaderBuilder(Files.newBufferedReader(path)).build();
                String[] headers = csvReader.readNext();
                String[] data;
                int counter = 0;
                while ((data = csvReader.readNext()) != null) {

                    Map<String, String> values = new HashMap<>();
                    for (int i = 0; i < data.length; i++) {
                        values.put(headers[i].trim(), data[i].trim());
                    }
                    csvData.put(counter, values);
                    counter++;
                }
                csvReader.close();


            } catch (Exception e) {
                System.err.println("Error occured while reading CSV file");
            }

        }else{
            System.err.println(csvFile +"doesn't exist");
            System.exit(0);
        }
        return csvData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvData csvData1 = (CsvData) o;
        return Objects.equals(csvData, csvData1.csvData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csvData);
    }

    @Override
    public String toString() {
        return "CsvData{" +
                "csvData=" + csvData +
                '}';
    }
}
