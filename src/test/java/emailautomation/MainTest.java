package emailautomation;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Array;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"--email","--email-template", "email-template.txt", "--output-dir","emails","--csv-file", "customer.csv"};
        Main.main(args);

    }
    @Test
    void mainForLetter() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"--letter","--letter-template", "letter-template.txt", "--output-dir","letters","--csv-file", "customer.csv"};
        Main.main(args);


    }

    @Test
    void mainWithZeroArguments(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {};
        Main.main(args);


    }

}