package TriResultsJava.tests;//package TriResultsJava.tests;

import TriResultsJava.Column;
import TriResultsJava.ColumnsConfigParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ColumnsConfigParserTest {

    private ColumnsConfigParser parser;
    private String filename;

    @BeforeEach
    void setUp() {
        String cwd = new File(ColumnsConfigParserTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toString();  // System.getProperty("user.dir");
        Path p = Paths.get(cwd, "TriResultsJava", "tests", "column_config.xml");
        filename =  p.toString();

        parser = new ColumnsConfigParser();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parseTest() {

        List<Column> columns = new ArrayList<Column>();

        try {
            columns = parser.parse(filename);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(columns.size() > 0);


    }
}
