package emailautomation;

/** Main class to take arguments* */
public class Main {
    /**
     * main constructor take 1 argument
     *
     * @param args represents command line argument*
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please give valid input");
            AnalyzeArguments.printMessage();
        } else {
            AnalyzeArguments arguments = new AnalyzeArguments(args);
            GenerateFile generateFile = new GenerateFile(arguments.getFileTemplate(), arguments.getCsvFileNm(), arguments.getOutputDir(), arguments.getFileType());
            generateFile.generateFileForEmployees();

        }

    }
}
