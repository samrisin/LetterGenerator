package emailautomation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataTest {
    CsvData csvData;
    CsvData csvData1;
    @BeforeEach
    void setUp() {
    this.csvData = new CsvData();
    this.csvData1 = new CsvData();
    }

    @Test
    void getCsvData() {

    }

    @Test
    void testEquals() {
        Assertions.assertTrue(csvData1.equals(csvData));
        Assertions.assertFalse(csvData.equals(null));
        Assertions.assertFalse(csvData.equals(new PrintLetter(new HashMap<>())));
        Assertions.assertTrue(csvData.equals(csvData));
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(csvData.hashCode(),csvData1.hashCode());
    }

    @Test
    void testToString() {
    }

}