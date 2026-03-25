package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TemplateProcessorTest {

  private TemplateProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new TemplateProcessor();
  }

  @Test
  void testReplaceSinglePlaceholder(@TempDir Path tempDir) throws IOException {
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("Dear [[first_name]],");
    }
    Map<String, String> row = new LinkedHashMap<>();
    row.put("first_name", "Art");
    String result = processor.process(template.getAbsolutePath(), row);
    assertEquals("Dear Art,", result);
  }

  @Test
  void testReplaceMultiplePlaceholders(@TempDir Path tempDir) throws IOException {
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("To:[[email]]\nDear [[first_name]] [[last_name]],");
    }
    Map<String, String> row = new LinkedHashMap<>();
    row.put("email", "art@venere.org");
    row.put("first_name", "Art");
    row.put("last_name", "Venere");
    String result = processor.process(template.getAbsolutePath(), row);
    assertEquals("To:art@venere.org\nDear Art Venere,", result);
  }

  @Test
  void testNoPlaceholdersUnchanged(@TempDir Path tempDir) throws IOException {
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("Hello World");
    }
    Map<String, String> row = new LinkedHashMap<>();
    row.put("first_name", "Art");
    String result = processor.process(template.getAbsolutePath(), row);
    assertEquals("Hello World", result);
  }

  @Test
  void testSamePlaceholderMultipleTimes(@TempDir Path tempDir) throws IOException {
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("Dear [[first_name]], Hello [[first_name]].");
    }
    Map<String, String> row = new LinkedHashMap<>();
    row.put("first_name", "Art");
    String result = processor.process(template.getAbsolutePath(), row);
    assertEquals("Dear Art, Hello Art.", result);
  }
}
