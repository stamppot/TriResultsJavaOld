import TriResultsJava.Column;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StandardizeResultsCsv {

    private List<Column> _csvColumnConfig;

    private String _csvColumnConfixXml;

    public StandardizeResultsCsv(String columnConfigXml) {
        this._csvColumnConfixXml = columnConfigXml;
    }

    public StandardizeResultsCsv(List<Column> columnsConfig) {
        this._csvColumnConfig = columnsConfig;
    }

    public final List<HashMap<String,String>> ReadResults(String[] csvLines /* TODO: pass list of members */) {
        String[] standardizedLines = Read(csvLines);
        String separator = ",";
        String headerLine = standardizedLines[0];
        separator = new MyCsvHelper().DetermineSeparator(headerLine);
        List<String> headers = Arrays.asList(headerLine.split(separator));
        /* find index of Name header */
        int nameIndex = Arrays.binarySearch(headers.toArray(), "Naam");

        List<HashMap<String,String>> results = new ArrayList<>();

        for (int i=1; i<standardizedLines.length; i++) {
            HashMap<String,String> currentResultMap = new HashMap<>();
            String[] currentResult = standardizedLines[i].split(separator);

            for (int j=0; j<headers.size(); j++) {
                String column = headers.get(j);
                String name = currentResult[nameIndex];
                System.out.println("Name: " + name);

                String key = headers.get(j);
                String value = currentResult[j];
                System.out.println("" + column + ": " + value);

                /* TODO: skip to next if name is not in list */

                currentResultMap.put(key, value);
            }

            results.add(currentResultMap);

        }

        return results;
    }

    ///  <summary>
    ///  Reads csv file and standardizes columnnames
    ///  </summary>
    ///  <param name="csv"></param>
    public final String[] Read(String[] csvLines) {
        ICsvHelper csvHelper = new MyCsvHelper();
        List<Column> config = GetColumnConfiguration();
        ColumnStandardizer columnStandardizer = new ColumnStandardizer(config);
        String[] standardizedCsv = csvHelper.ReplaceHeaders(csvLines, columnStandardizer);
        return standardizedCsv;
    }

    private List<Column> GetColumnConfiguration() {
        if (null == _csvColumnConfig) {
            ColumnsConfigParser configReader = new ColumnsConfigParser();
            try {
                _csvColumnConfig = configReader.parse(_csvColumnConfixXml);
            } catch(BadConfigurationException | IOException e) {
                System.out.printf("Configuration error: " + e.getMessage());
            }
        }
        return _csvColumnConfig;
    }
}