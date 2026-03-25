package problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses a CSV file where fields are enclosed in double quotes and separated by commas.
 * Handles the case where field values themselves contain commas.
 */
public class CsvParser {

  /**
   * Constructs a new CsvParser.
   */
  public CsvParser() {}

  /**
   * Parses the CSV file at the given path.
   * The first line is treated as the header row.
   * Each subsequent line is returned as a map from header name to field value.
   *
   * @param filePath path to the CSV file
   * @return a list of maps, one per data row, mapping column headers to values
   * @throws IOException if the file cannot be read
   */
  public List<Map<String, String>> parse(String filePath) throws IOException {
    List<Map<String, String>> rows = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      // First line is the header row; empty file returns empty list
      String headerLine = reader.readLine();
      if (headerLine == null) {
        return rows;
      }
      // setting headers as the first line of the CSV file, and parsing it into a list of header names
      List<String> headers = parseLine(headerLine);

      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank()) {
          continue; // skip empty lines
        }
        List<String> fields = parseLine(line);

        // Zip headers and fields into a map; missing fields default to ""
        Map<String, String> row = new LinkedHashMap<>();
        for (int i = 0; i < headers.size(); i++) {
          row.put(headers.get(i), i < fields.size() ? fields.get(i) : "");
        }
        rows.add(row);
      }
    }
    return rows;
  }

  /**
   * Parses a single CSV line into a list of field values.
   * Fields are expected to be enclosed in double quotes.
   * Commas inside double quotes are treated as part of the field value.
   *
   * @param line a single line from the CSV file
   * @return list of field values with surrounding quotes removed
   */
  List<String> parseLine(String line) {
    List<String> fields = new ArrayList<>();
    // inQuotes is a two-state machine: inside or outside quotes.
    // A boolean suffices because CSV quoting is flat (no nesting),
    // unlike bracket matching which would require a stack.
    boolean inQuotes = false;
    StringBuilder current = new StringBuilder(); // buffer accumulating characters of the current field
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (c == '"') {
        inQuotes = !inQuotes;                  // toggle state on every quote character
      } else if (c == ',' && !inQuotes) {
        fields.add(current.toString().trim()); // comma outside quotes → field boundary
        current.setLength(0);                  // reset buffer for next field
      } else {
        current.append(c);                     // any other char (including comma inside quotes)
      }
    }
    // Last field has no trailing comma, so it is not captured inside the loop
    fields.add(current.toString().trim());
    return fields;
  }
}
