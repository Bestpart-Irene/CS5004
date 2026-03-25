package problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link CommandLineParser}.
 * Tests cover valid argument combinations, argument order independence,
 * and all expected error cases.
 */
class CommandLineParserTest {

  private CommandLineParser parser;

  /**
   * Creates a fresh CommandLineParser before each test.
   */
  @BeforeEach
  void setUp() {
    parser = new CommandLineParser();
  }

  /**
   * Valid email-only args: verifies all fields are correctly parsed.
   */
  @Test
  void testValidEmailArgs() {
    parser.parse(new String[]{
        "--email", "--email-template", "email.txt",
        "--output-dir", "out/", "--csv-file", "data.csv"
    });
    assertTrue(parser.isGenerateEmail());
    assertFalse(parser.isGenerateLetter());
    assertEquals("email.txt", parser.getEmailTemplate());
    assertEquals("out/", parser.getOutputDir());
    assertEquals("data.csv", parser.getCsvFile());
  }

  /**
   * Valid letter-only args: verifies letter fields are correctly parsed.
   */
  @Test
  void testValidLetterArgs() {
    parser.parse(new String[]{
        "--letter", "--letter-template", "letter.txt",
        "--output-dir", "out/", "--csv-file", "data.csv"
    });
    assertFalse(parser.isGenerateEmail());
    assertTrue(parser.isGenerateLetter());
    assertEquals("letter.txt", parser.getLetterTemplate());
  }

  /**
   * Both --email and --letter provided: both flags should be true.
   */
  @Test
  void testValidBothEmailAndLetter() {
    parser.parse(new String[]{
        "--email", "--email-template", "email.txt",
        "--letter", "--letter-template", "letter.txt",
        "--output-dir", "out/", "--csv-file", "data.csv"
    });
    assertTrue(parser.isGenerateEmail());
    assertTrue(parser.isGenerateLetter());
  }

  /**
   * Arguments provided in non-standard order: parser should handle any order.
   */
  @Test
  void testArgsInAnyOrder() {
    parser.parse(new String[]{
        "--csv-file", "data.csv",
        "--output-dir", "out/",
        "--email-template", "email.txt",
        "--email"
    });
    assertTrue(parser.isGenerateEmail());
    assertEquals("data.csv", parser.getCsvFile());
  }

  /**
   * Missing --csv-file: should throw with message mentioning --csv-file.
   */
  @Test
  void testMissingCsvFileThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--email", "--email-template", "email.txt", "--output-dir", "out/"
        })
    );
    assertTrue(ex.getMessage().contains("--csv-file"));
  }

  /**
   * Missing --output-dir: should throw with message mentioning --output-dir.
   */
  @Test
  void testMissingOutputDirThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--email", "--email-template", "email.txt", "--csv-file", "data.csv"
        })
    );
    assertTrue(ex.getMessage().contains("--output-dir"));
  }

  /**
   * --email provided without --email-template: should throw mentioning --email-template.
   */
  @Test
  void testEmailWithoutTemplateThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--email", "--output-dir", "out/", "--csv-file", "data.csv"
        })
    );
    assertTrue(ex.getMessage().contains("--email-template"));
  }

  /**
   * --letter provided without --letter-template: should throw mentioning --letter-template.
   */
  @Test
  void testLetterWithoutTemplateThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--letter", "--output-dir", "out/", "--csv-file", "data.csv"
        })
    );
    assertTrue(ex.getMessage().contains("--letter-template"));
  }

  /**
   * Neither --email nor --letter provided: should throw mentioning at least one is required.
   */
  @Test
  void testNoEmailOrLetterThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--output-dir", "out/", "--csv-file", "data.csv"
        })
    );
    assertTrue(ex.getMessage().contains("--email") || ex.getMessage().contains("--letter"));
  }

  /**
   * Unrecognized flag: should throw with message mentioning "Unknown argument".
   */
  @Test
  void testUnknownArgumentThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{"--unknown"})
    );
    assertTrue(ex.getMessage().contains("Unknown argument"));
  }

  /**
   * --email-template followed immediately by another flag instead of a path: should throw.
   */
  @Test
  void testEmailTemplateFollowedByFlagThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--email", "--email-template", "--output-dir", "out/", "--csv-file", "data.csv"
        })
    );
    assertTrue(ex.getMessage().contains("--email-template"));
  }

  /**
   * --csv-file followed immediately by another flag instead of a path: should throw.
   */
  @Test
  void testCsvFileFollowedByFlagThrows() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
        parser.parse(new String[]{
            "--email", "--email-template", "email.txt", "--output-dir", "out/", "--csv-file", "--letter"
        })
    );
    assertTrue(ex.getMessage().contains("--csv-file"));
  }
}
