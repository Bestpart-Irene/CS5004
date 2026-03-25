package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

  private CsvParser parser;

  @BeforeEach
  void setUp() {
    parser = new CsvParser();
  }

  @Test
  void testParseLineBasic() {
    List<String> fields = parser.parseLine("\"first_name\",\"last_name\",\"email\"");
    assertEquals(3, fields.size());
    assertEquals("first_name", fields.get(0));
    assertEquals("last_name", fields.get(1));
    assertEquals("email", fields.get(2));
  }

  @Test
  void testParseLineWithCommaInsideField() {
    List<String> fields = parser.parseLine("\"Art\",\"Chemel, James L Cpa\",\"art@venere.org\"");
    assertEquals(3, fields.size());
    assertEquals("Art", fields.get(0));
    assertEquals("Chemel, James L Cpa", fields.get(1));
    assertEquals("art@venere.org", fields.get(2));
  }

  @Test
  void testParseReturnsMapsWithHeaders(@TempDir Path tempDir) throws IOException {
    File csv = new File(tempDir.toFile(), "test.csv");
    try (FileWriter fw = new FileWriter(csv)) {
      fw.write("\"first_name\",\"last_name\",\"email\"\n");
      fw.write("\"Art\",\"Venere\",\"art@venere.org\"\n");
      fw.write("\"James\",\"Butt\",\"jbutt@gmail.com\"\n");
    }
    List<Map<String, String>> rows = parser.parse(csv.getAbsolutePath());
    assertEquals(2, rows.size());
    assertEquals("Art", rows.get(0).get("first_name"));
    assertEquals("Venere", rows.get(0).get("last_name"));
    assertEquals("art@venere.org", rows.get(0).get("email"));
    assertEquals("James", rows.get(1).get("first_name"));
    assertEquals("jbutt@gmail.com", rows.get(1).get("email"));
  }

  @Test
  void testParseEmptyFileReturnsEmptyList(@TempDir Path tempDir) throws IOException {
    File csv = new File(tempDir.toFile(), "empty.csv");
    csv.createNewFile();
    List<Map<String, String>> rows = parser.parse(csv.getAbsolutePath());
    assertTrue(rows.isEmpty());
  }

  @Test
  void testParseSkipsBlankLines(@TempDir Path tempDir) throws IOException {
    File csv = new File(tempDir.toFile(), "blanks.csv");
    try (FileWriter fw = new FileWriter(csv)) {
      fw.write("\"first_name\",\"email\"\n");
      fw.write("\n");
      fw.write("\"Art\",\"art@venere.org\"\n");
      fw.write("\n");
    }
    List<Map<String, String>> rows = parser.parse(csv.getAbsolutePath());
    assertEquals(1, rows.size());
  }
}
