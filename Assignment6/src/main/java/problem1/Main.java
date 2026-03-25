package problem1;

import java.io.IOException;

/**
 * Entry point for the insurance company communication automation program.
 * Reads a CSV file and one or more templates, and generates personalized
 * email and/or letter files for each customer.
 */
public class Main {

  /**
   * Main method. Parses command line arguments and runs the file generation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    CommandLineParser parser = new CommandLineParser();
    try {
      parser.parse(args);
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }

    CsvParser csvParser = new CsvParser();
    TemplateProcessor templateProcessor = new TemplateProcessor();
    FileGenerator generator = new FileGenerator(csvParser, templateProcessor);

    try {
      if (parser.isGenerateEmail()) {
        generator.generate(parser.getCsvFile(), parser.getEmailTemplate(),
            parser.getOutputDir(), "email");
        System.out.println("Email files generated in: " + parser.getOutputDir());
      }
      if (parser.isGenerateLetter()) {
        generator.generate(parser.getCsvFile(), parser.getLetterTemplate(),
            parser.getOutputDir(), "letter");
        System.out.println("Letter files generated in: " + parser.getOutputDir());
      }
    } catch (IOException e) {
      System.err.println("Error generating files: " + e.getMessage());
      System.exit(1);
    }
  }
}
