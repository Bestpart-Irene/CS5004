package problem1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Processes a template file by replacing placeholders with values from a data map.
 * Placeholders are formatted as [[column_name]], where column_name corresponds
 * to a header in the CSV file.
 */
public class TemplateProcessor {

  /**
   * Constructs a new TemplateProcessor.
   */
  public TemplateProcessor() {}

  /**
   * Reads a template file and replaces all placeholders with values from the given row.
   * Placeholders have the format [[header_name]].
   *
   * @param templatePath path to the template file
   * @param row          a map of column headers to their values for one CSV row
   * @return the template content with all placeholders replaced
   * @throws IOException if the template file cannot be read
   */
  public String process(String templatePath, Map<String, String> row) throws IOException {
    // Read the entire template file into a single string
    String content = new String(Files.readAllBytes(Paths.get(templatePath)));
    // row is injected from outside (parsed by CsvParser) — this class only does replacement.
    // entrySet() converts the map into a set of key-value pairs so we can iterate over both
    // the field name (key) and its value at the same time
    for (Map.Entry<String, String> entry : row.entrySet()) {
      String placeholder = "[[" + entry.getKey() + "]]"; // build placeholder from field name
      content = content.replace(placeholder, entry.getValue()); // replace all occurrences in template
    }
    return content;
  }
}
