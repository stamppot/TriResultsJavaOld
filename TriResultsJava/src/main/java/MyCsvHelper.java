import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCsvHelper implements ICsvHelper {

    public final List<String> GetHeaders(List<String> csvLines) throws InvalidCsvException {
        String headerLine = csvLines.get(0);
        if (Strings.isNullOrEmpty(headerLine)) {
            throw new InvalidCsvException("Csv has no headers");
        }

        String separator = this.DetermineSeparator(headerLine);
        return new ArrayList<String>();
    }

    public final String[] ReplaceHeaders(String[] csvLines, ColumnStandardizer columnStandardizer) {
        String separator = ",";
        String[] lines = csvLines;
        if (lines.length > 0) {
            separator = this.DetermineSeparator(csvLines[0]);
            String headers = lines[0];

            List<String> standardizedHeaders = columnStandardizer.GetStandardColumnNames(Arrays.asList(headers.split(separator)));

            lines[0] = String.join(separator, standardizedHeaders);
        }

        return lines;
    }

    public final String DetermineSeparator(String csvHeader) {
        String result = ";";

        if (!Strings.isNullOrEmpty(csvHeader)) {
            int commaCount = csvHeader.split(",").length;
            int semiColonCount = csvHeader.split(";").length;

            if(commaCount > semiColonCount) {
                result = ",";
            }
        }

        return result;
    }
}