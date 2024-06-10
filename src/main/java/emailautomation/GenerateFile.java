package emailautomation;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents class for generating file*/
public class GenerateFile {

    HashMap<Integer, Map<String,String>> csvData =new HashMap<>();
    String outputDir;
    String fileTemplate;
    String csvFileName;
    Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
    String fileType;

    final String defaultPath="src/main/resources/";

    /** Generate file contructor
     @param fileType represents type of file
      @param csvFileName represents csv file
       @param outputDir represents output directory
        @param fileTemplate represents file template* */
    public GenerateFile(String fileTemplate, String csvFileName, String outputDir,String fileType) {
        this.outputDir = outputDir;
        this.fileTemplate = fileTemplate;
        this.csvFileName = csvFileName;
        this.fileType =fileType;
    }
    /** Generates required file for the employees*/
    public void generateFileForEmployees(){
        csvData = new CsvData().getCsvData(csvFileName);
        String path = defaultPath + fileTemplate;
        if (Files.exists(Paths.get(path))) {
        try {
            String data = "";


            data = new String(
                    Files.readAllBytes(Paths.get(path)));

            Matcher matcher = pattern.matcher(data);
            for (int i = 0; i < csvData.size(); i++) {
                Map<String, String> values = new HashMap<>();
                values = csvData.get(i);
                while (matcher.find()) {
                    String placeholder = matcher.group(1);
                    if (values.containsKey(placeholder)) {
                        String replacement = values.get(placeholder);
                        data = data.replaceAll("\\[\\[" + placeholder + "\\]\\]", replacement);
                    }
                }
                writeToFile(data, values, i);
            }
        }
            catch(Exception e){
                System.out.println("Error occured while printing Letter");
            }
        }else{
            System.err.println(fileTemplate +" doesn't not exist");
        }
    }

    /** Writes data to the file
     @param data represents file data
     @param values represents file headers
      @param i represents user data number* */
    public void writeToFile(String data,Map<String,String> values,int i){
        String path = defaultPath+outputDir;
        if(Files.exists(Paths.get(path))){
            String fileName = fileType+i+".txt";

            try {
                File outputFile = new File(path, fileName);
                FileWriter writer = new FileWriter(outputFile);
                writer.write(data);
                writer.close();
                if(fileType.equals("email")){
                    SendEmail sendEmail = new SendEmail(values);
                } else if(fileType.equals("letter")) {
                    PrintLetter printLetter = new PrintLetter(values);
                }
            } catch (Exception e) {
                System.err.println("Error writing to file: " + e.getMessage());

            }
        }else{
            System.err.println(outputDir+" Output directory doesn't doesn't exist");

        }
    }

}
