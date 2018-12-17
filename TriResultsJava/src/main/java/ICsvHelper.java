public interface ICsvHelper
{
    String[] ReplaceHeaders(String[] csvLines, ColumnStandardizer columnStandardizer);
    String DetermineSeparator(String csvLine);
}