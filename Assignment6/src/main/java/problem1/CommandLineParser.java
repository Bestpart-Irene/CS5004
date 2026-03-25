package problem1;

/**
 * Parses and validates command line arguments for the insurance communication program.
 * Supported arguments:
 * <ul>
 *   <li>--email: generate email messages (requires --email-template)</li>
 *   <li>--email-template &lt;path&gt;: path to the email template file</li>
 *   <li>--letter: generate letters (requires --letter-template)</li>
 *   <li>--letter-template &lt;path&gt;: path to the letter template file</li>
 *   <li>--output-dir &lt;path&gt;: directory for output files (required)</li>
 *   <li>--csv-file &lt;path&gt;: path to the CSV file (required)</li>
 * </ul>
 */
public class CommandLineParser {

  private static final String USAGE =
      """
      Usage:
        --email                          Generate email messages. If this option is provided,
                                         then --email-template must also be provided.
        --email-template <path/to/file>  A filename for the email template.
        --letter                         Generate letters. If this option is provided,
                                         then --letter-template must also be provided.
        --letter-template <path/to/file> A filename for the letter template.
        --output-dir <path/to/folder>    The folder to store all generated files. Required.
        --csv-file <path/to/file>        The CSV file to process. Required.
      
      Examples:
        --email --email-template email-template.txt --output-dir emails --csv-file customer.csv
        --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv
      """;

  private boolean generateEmail;
  private boolean generateLetter;
  private String emailTemplate;
  private String letterTemplate;
  private String outputDir;
  private String csvFile;

  /**
   * Parses and validates the given command line arguments.
   * Checks both syntactic correctness (each flag that requires a value has one)
   * and semantic correctness (required flags are present, flag combinations are legal).
   *
   * @param args the command line arguments
   * @throws IllegalArgumentException if any argument is invalid, unknown, or required args are missing
   */
  public void parse(String[] args) {
    // Looping through args with a switch statement to handle each expected flag and its value
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "--email" -> generateEmail = true;                  // request email generation
        case "--letter" -> generateLetter = true;                // request letter generation
        case "--email-template" -> {                             // path to email template file
            if (i + 1 >= args.length || args[i + 1].startsWith("--")) {
                throw new IllegalArgumentException(
                        "Error: --email-template requires a file path argument.\n" + USAGE);
            }
            emailTemplate = args[++i];
            }
        case "--letter-template" -> {                            // path to letter template file
            if (i + 1 >= args.length || args[i + 1].startsWith("--")) {
                throw new IllegalArgumentException(
                        "Error: --letter-template requires a file path argument.\n" + USAGE);
            }
            letterTemplate = args[++i];
            }
        case "--output-dir" -> {                                 // path to output directory
            if (i + 1 >= args.length || args[i + 1].startsWith("--")) {
                throw new IllegalArgumentException(
                        "Error: --output-dir requires a folder path argument.\n" + USAGE);
            }
            outputDir = args[++i];
            }
        case "--csv-file" -> {                                   // path to CSV file
            if (i + 1 >= args.length || args[i + 1].startsWith("--")) {
                throw new IllegalArgumentException(
                        "Error: --csv-file requires a file path argument.\n" + USAGE);
            }
            csvFile = args[++i];
            }
        default -> throw new IllegalArgumentException(           // unrecognized argument
              "Error: Unknown argument: " + args[i] + "\n" + USAGE);
      }
    }

    // Validate that all required flags were provided and combinations are legal
    if (csvFile == null) {
      throw new IllegalArgumentException("Error: --csv-file is required.\n" + USAGE);
    }
    if (outputDir == null) {
      throw new IllegalArgumentException("Error: --output-dir is required.\n" + USAGE);
    }
    if (!generateEmail && !generateLetter) {
      throw new IllegalArgumentException(
          "Error: at least one of --email or --letter must be provided.\n" + USAGE);
    }
    if (generateEmail && emailTemplate == null) {
      throw new IllegalArgumentException(
          "Error: --email provided but no --email-template was given.\n" + USAGE);
    }
    if (generateLetter && letterTemplate == null) {
      throw new IllegalArgumentException(
          "Error: --letter provided but no --letter-template was given.\n" + USAGE);
    }
  }

  // -------------------------------------------------------------------------
  // NO SETTERS — INTENTIONALLY OMITTED.
  // Once parse() completes successfully, this object represents a validated,
  // immutable configuration. Setters would allow bypassing validation.
  // -------------------------------------------------------------------------

  // -------------------------------------------------------------------------
  // Getters
  // -------------------------------------------------------------------------

  /**
   * Returns whether email generation was requested.
   *
   * @return true if --email was provided
   */
  public boolean isGenerateEmail() {
    return generateEmail;
  }

  /**
   * Returns whether letter generation was requested.
   *
   * @return true if --letter was provided
   */
  public boolean isGenerateLetter() {
    return generateLetter;
  }

  /**
   * Returns the email template file path.
   *
   * @return the email template path, or null if not provided
   */
  public String getEmailTemplate() {
    return emailTemplate;
  }

  /**
   * Returns the letter template file path.
   *
   * @return the letter template path, or null if not provided
   */
  public String getLetterTemplate() {
    return letterTemplate;
  }

  /**
   * Returns the output directory path.
   *
   * @return the output directory path
   */
  public String getOutputDir() {
    return outputDir;
  }

  /**
   * Returns the CSV file path.
   *
   * @return the CSV file path
   */
  public String getCsvFile() {
    return csvFile;
  }

  // -------------------------------------------------------------------------
  // NO equals() / hashCode() — INTENTIONALLY OMITTED.
  // CommandLineParser represents a one-time parsing result, not a value object.
  // There is no meaningful use case for comparing two parsers or using one as
  // a map key, so implementing these methods would add noise without benefit.
  // -------------------------------------------------------------------------

  /**
   * Returns a string representation of the parsed command line configuration.
   *
   * @return a string showing all parsed argument values
   */
  @Override
  public String toString() {
    return "CommandLineParser{"
        + "generateEmail=" + generateEmail
        + ", generateLetter=" + generateLetter
        + ", emailTemplate='" + emailTemplate + '\''
        + ", letterTemplate='" + letterTemplate + '\''
        + ", outputDir='" + outputDir + '\''
        + ", csvFile='" + csvFile + '\''
        + '}';
  }
}
