package emailautomation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeArgumentsTest {
    AnalyzeArguments aa;
    AnalyzeArguments aa1;
    String[] args = {"--email","--email-template", "email-template.txt", "--output-dir","emails","--csv-file", "customer.csv"};

    @BeforeEach
    void setUp() {

        String[] args1 = {"--letter","--letter-template", "letter-template.txt", "--output-dir","letters","--csv-file", "customer.csv"};
        this.aa= new AnalyzeArguments(args);
        AnalyzeArguments aa1 = new AnalyzeArguments(args1);
    }

    @Test
    void getFileTemplate() {
    }

    @Test
    void getOutputDir() {
    }

    @Test
    void getCsvFileNm() {
        Assertions.assertEquals("customer.csv",aa.getCsvFileNm());
    }

    @Test
    void getFileType() {
        Assertions.assertEquals("email",aa.getFileType());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(aa,aa);
        Assertions.assertFalse(aa.equals(null));
        Assertions.assertTrue(aa.equals(aa));
        Assertions.assertFalse(aa.equals(new PrintLetter(new HashMap<>())));
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(aa.hashCode(),aa.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "AnalyzeArguments{" +
                "cmdArguments=" + Arrays.toString(args) + '}';
        Assertions.assertTrue(aa.toString().equals(expectedString));
    }


}