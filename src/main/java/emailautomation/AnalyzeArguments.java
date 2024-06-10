package emailautomation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


/** Analyze Arguments class takes command line arguments and invokes action*/
public class AnalyzeArguments {
    String outputDir = null;
    String csvFileNm = null;
    boolean generateEmails = false;
    boolean generateLetters = false;
    String fileType=null;
    String fileTemplate;
    String[] cmdArguments;
    String emailTemplate;
    String letterTemplate;
    final String email = "--email";
    final String letter = "--letter";
    final String emailTemplateCommand = "--email-template";
    final String letterTemplateCommand = "--letter-template";
    final String outputDirectory = "--output-dir";
    final String csvFile = "--csv-file";


    static final String usageString= """
                Usage:
                --email                        only generate email messages
                --email-template <file> accepts a filename that holds the email template.
                Required if --email is used
                --letter                        only generate letters
                --letter-template <file> accept a filename that holds the email template.
                Required if --letter is used
                 --output dir <path> accept the name of the folder, all output is placed in this folder
                -.
                --csv-file <path> accepts the name of the csv file to process
                Examples:
                --email --email-template email-template.txt --output-dir emails --csv-file customer.csv
                --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv
                dir letters --csv-file customer.csv""";


    /** Analyze Arguments constructor class takes command line arguments and invokes action
     *@param args command line args
     * */
    public AnalyzeArguments(String[] args) {
        cmdArguments = args;
        parseArguments();
    }

    /** returns file template
     @return return file template */

    public String getFileTemplate() {
        return fileTemplate;
    }

    /** returns output directory
     *@return returns output directory */

    public String getOutputDir() {
        return outputDir;
    }

    /** returns csvFile
     @return returns csvFileName* */
    public String getCsvFileNm() {
        return csvFileNm;
    }

    /** returns File type
     *@return returns filetype */
    public String getFileType() {
        return fileType;
    }

    /** Invoke action function
     * */
    public void parseArguments() {

        for (int i = 0; i < cmdArguments.length; i++) {
            String arg = cmdArguments[i];
            switch (arg) {
                case outputDirectory:
                    outputDir = getValue(cmdArguments, i);
                    i++; // Skip the next argument (value)
                    break;
                case csvFile:
                    csvFileNm = getValue(cmdArguments, i);
                    i++;
                    break;
                case emailTemplateCommand:
                        emailTemplate = validateAndGetValue(cmdArguments, i);
                    i++;
                    break;
                case letterTemplateCommand:
                        letterTemplate = validateAndGetValue(cmdArguments, i);
                    i++;
                    break;
                case email:
                    generateEmails =true;
                    fileType = cmdArguments[i].trim().substring(2);
                    break;
                case letter:
                    generateLetters =true;
                    fileType = cmdArguments[i].trim().substring(2);
                    break;
                default:
                    System.err.println("Unrecognized option: " + arg);
                    System.exit(0);
                    printMessage();
            }
        }
        validateGeneration();

    }

    /** returns value of the command
     * @return String value*/
    private String getValue(String[] args, int index) {
        if (index + 1 < args.length) {
            return args[index + 1];
        } else {
            System.err.println("Missing argument for " + args[index]);
            printMessage();
            return null;
        }
    }

    /** @param args represents command line arguments
     *  index  of the argument
     * returns value of the command
     * @return String value*/
    private String validateAndGetValue(String[] args, int index) {
        String value = getValue(args, index);
        if (!isValidTemplateFile(value)) {
            System.err.println(args[index]+"Provided but no "+ value +"+was given");
            printMessage();
            System.exit(0);
        }
        return value;
    }

    /** returns value of the command
     * @return  true if the filename is valid*/
    private boolean isValidTemplateFile(String fileName) {
        return fileName != null && fileName.toLowerCase().endsWith(".txt");
    }

    /** checks if user has given both letter and email*/
    public void validateGeneration(){
        if(generateEmails&&generateLetters){
            System.err.println("Letter and Email both cannot be generated");
            System.exit(0);
        }
        if(outputDir==null || csvFileNm==null){
            System.err.println("Output directory and csvFile cannot be Empty");
            System.exit(0);
        }
        if(generateEmails&&emailTemplate==null){
            System.err.println("--email provided but no email-template given");
            System.exit(0);
        }else{
            fileTemplate = emailTemplate;
        }
        if(generateLetters&&letterTemplate==null){
            System.err.println("--letter provided but no letter-template given");
            System.exit(0);
        }else{
            fileTemplate = letterTemplate;
        }

    }

    /** Prints usage message for the user*/
    public static void printMessage(){
        System.out.println(usageString);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzeArguments arguments = (AnalyzeArguments) o;
        return Arrays.equals(cmdArguments, arguments.cmdArguments);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cmdArguments);
    }

    @Override
    public String toString() {
        return "AnalyzeArguments{" +
                "cmdArguments=" + Arrays.toString(cmdArguments) +
                '}';
    }
}















