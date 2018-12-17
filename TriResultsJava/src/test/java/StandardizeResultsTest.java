import TriResultsJava.Column;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StandardizeResultsTest {

    private List<Column> config;
    private StandardizeResultsCsv standardizeResultsCsv;
    private String[] csvLines;

    @BeforeEach
    void setUp() {
        config = ConfigHelper.Load();
        standardizeResultsCsv = new StandardizeResultsCsv(config);

        csvLines = new String[]{"Name;Plts;Total", "Jens Rasmussen;4;4:52:34","T Test;5;5:00:01"};
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parseTest() {

        List<HashMap<String,String>> results = standardizeResultsCsv.ReadResults(csvLines);

        assertTrue(results.size() == 2);

    }
}