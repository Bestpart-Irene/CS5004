package problem1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Generates output files by combining CSV data rows with a template.
 * One output file is created per CSV row, with placeholders replaced by row values.
 */
public class FileGenerator {

  private final CsvParser csvParser;
  private final TemplateProcessor templateProcessor;

  /**
   * Constructs a FileGenerator with the given CSV parser and template processor.
   *
   * @param csvParser         the parser used to read CSV data
   * @param templateProcessor the processor used to fill in templates
   */
  public FileGenerator(CsvParser csvParser, TemplateProcessor templateProcessor) {
    this.csvParser = csvParser;
    this.templateProcessor = templateProcessor;
  }

  /**
   * Generates one output file per CSV row using the given template.
   * Output files are named sequentially: output_1.txt, output_2.txt, etc.
   * The output directory is created if it does not exist.
   *
   * @param csvFilePath    path to the CSV file
   * @param templatePath   path to the template file
   * @param outputDir      path to the directory where output files will be written
   * @param filePrefix     prefix for the output file names (e.g. "email" or "letter")
   * @throws IOException if reading or writing files fails
   */
  public void generate(String csvFilePath, String templatePath, String outputDir, String filePrefix)
      throws IOException {
    List<Map<String, String>> rows = csvParser.parse(csvFilePath);
    File dir = new File(outputDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    for (int i = 0; i < rows.size(); i++) {
      String content = templateProcessor.process(templatePath, rows.get(i));
      File outputFile = new File(dir, filePrefix + "_" + (i + 1) + ".txt");
      // try-with-resources: equivalent to Python's "with open(...) as writer"
      // ensures writer.close() is called automatically even if an exception occurs
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        writer.write(content);
      }
    }
  }
}
