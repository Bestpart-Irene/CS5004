package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileGeneratorTest {

  private FileGenerator generator;

  @BeforeEach
  void setUp() {
    generator = new FileGenerator(new CsvParser(), new TemplateProcessor());
  }

  @Test
  void testGeneratesOneFilePerRow(@TempDir Path tempDir) throws IOException {
    File csv = new File(tempDir.toFile(), "test.csv");
    try (FileWriter fw = new FileWriter(csv)) {
      fw.write("\"first_name\",\"last_name\"\n");
      fw.write("\"Art\",\"Venere\"\n");
      fw.write("\"James\",\"Butt\"\n");
    }
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("Dear [[first_name]] [[last_name]],");
    }
    File outputDir = new File(tempDir.toFile(), "output");
    generator.generate(csv.getAbsolutePath(), template.getAbsolutePath(),
        outputDir.getAbsolutePath(), "email");

    assertTrue(new File(outputDir, "email_1.txt").exists());
    assertTrue(new File(outputDir, "email_2.txt").exists());
    assertFalse(new File(outputDir, "email_3.txt").exists());
  }

  @Test
  void testOutputFileContainsReplacedContent(@TempDir Path tempDir) throws IOException {
    File csv = new File(tempDir.toFile(), "test.csv");
    try (FileWriter fw = new FileWriter(csv)) {
      fw.write("\"first_name\",\"email\"\n");
      fw.write("\"Art\",\"art@venere.org\"\n");
    }
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("To:[[email]]\nDear [[first_name]],");
    }
    File outputDir = new File(tempDir.toFile(), "output");
    generator.generate(csv.getAbsolutePath(), template.getAbsolutePath(),
        outputDir.getAbsolutePath(), "email");

    String content = new String(Files.readAllBytes(new File(outputDir, "email_1.txt").toPath()));
    assertEquals("To:art@venere.org\nDear Art,", content);
  }

  @Test
  void testCreatesOutputDirectoryIfNotExists(@TempDir Path tempDir) throws IOException {
    File csv = new File(tempDir.toFile(), "test.csv");
    try (FileWriter fw = new FileWriter(csv)) {
      fw.write("\"first_name\"\n");
      fw.write("\"Art\"\n");
    }
    File template = new File(tempDir.toFile(), "template.txt");
    try (FileWriter fw = new FileWriter(template)) {
      fw.write("Hi [[first_name]]");
    }
    File outputDir = new File(tempDir.toFile(), "new_dir/nested");
    generator.generate(csv.getAbsolutePath(), template.getAbsolutePath(),
        outputDir.getAbsolutePath(), "letter");

    assertTrue(outputDir.exists());
    assertTrue(new File(outputDir, "letter_1.txt").exists());
  }
}
