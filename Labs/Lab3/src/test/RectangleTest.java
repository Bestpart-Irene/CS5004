import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Rectangle-specific methods.
 */
class RectangleTest {

  @Test
  void testConstructorValid() {
    Rectangle r = new Rectangle(0, 0, 5, 3);
    assertNotNull(r);
  }

  @Test
  void testConstructorNegativeWidth() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(0, 0, -5, 3);
    });
  }

  @Test
  void testConstructorNegativeHeight() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(0, 0, 5, -3);
    });
  }

  @Test
  void testConstructorZeroWidth() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(0, 0, 0, 3);
    });
  }

  @Test
  void testConstructorZeroHeight() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(0, 0, 5, 0);
    });
  }

  @Test
  void testAreaSquare() {
    Rectangle r = new Rectangle(0, 0, 5, 5);
    assertEquals(25.0, r.area(), 0.001);
  }

  @Test
  void testAreaRectangle() {
    Rectangle r = new Rectangle(0, 0, 4, 7);
    assertEquals(28.0, r.area(), 0.001);
  }

  @Test
  void testAreaWithDecimals() {
    Rectangle r = new Rectangle(1.5, 2.5, 3.5, 4.5);
    assertEquals(15.75, r.area(), 0.001);
  }

  @Test
  void testPerimeterSquare() {
    Rectangle r = new Rectangle(0, 0, 5, 5);
    assertEquals(20.0, r.perimeter(), 0.001);
  }

  @Test
  void testPerimeterRectangle() {
    Rectangle r = new Rectangle(0, 0, 4, 7);
    assertEquals(22.0, r.perimeter(), 0.001);
  }

  @Test
  void testPerimeterWithDecimals() {
    Rectangle r = new Rectangle(1.5, 2.5, 2.0, 3.0);
    assertEquals(10.0, r.perimeter(), 0.001);
  }

  @Test
  void testToString() {
    Rectangle r = new Rectangle(1, 2, 5, 3);
    String result = r.toString();
    assertTrue(result.contains("Rectangle"));
    assertTrue(result.contains("1.00"));
    assertTrue(result.contains("2.00"));
    assertTrue(result.contains("5.00"));
    assertTrue(result.contains("3.00"));
  }

  @Test
  void testToStringFormatting() {
    Rectangle r = new Rectangle(1.5, 2.5, 3.7, 4.8);
    String result = r.toString();
    assertTrue(result.contains("1.50"));
    assertTrue(result.contains("2.50"));
  }
}