import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Circle-specific methods.
 */
class CircleTest {

  @Test
  void testConstructorValid() {
    Circle c = new Circle(0, 0, 5);
    assertNotNull(c);
  }

  @Test
  void testConstructorNegativeRadius() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Circle(0, 0, -5);
    });
  }

  @Test
  void testConstructorZeroRadius() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Circle(0, 0, 0);
    });
  }

  @Test
  void testAreaUnitCircle() {
    Circle c = new Circle(0, 0, 1);
    assertEquals(Math.PI, c.area(), 0.001);
  }

  @Test
  void testAreaRadiusFive() {
    Circle c = new Circle(0, 0, 5);
    assertEquals(25 * Math.PI, c.area(), 0.001);
  }

  @Test
  void testAreaWithDecimals() {
    Circle c = new Circle(1.5, 2.5, 3.5);
    assertEquals(3.5 * 3.5 * Math.PI, c.area(), 0.001);
  }

  @Test
  void testPerimeterUnitCircle() {
    Circle c = new Circle(0, 0, 1);
    assertEquals(2 * Math.PI, c.perimeter(), 0.001);
  }

  @Test
  void testPerimeterRadiusFive() {
    Circle c = new Circle(0, 0, 5);
    assertEquals(10 * Math.PI, c.perimeter(), 0.001);
  }

  @Test
  void testPerimeterWithDecimals() {
    Circle c = new Circle(1.5, 2.5, 2.5);
    assertEquals(5 * Math.PI, c.perimeter(), 0.001);
  }

  @Test
  void testToString() {
    Circle c = new Circle(1, 2, 5);
    String result = c.toString();
    assertTrue(result.contains("Circle"));
    assertTrue(result.contains("1.00"));
    assertTrue(result.contains("2.00"));
    assertTrue(result.contains("5.00"));
  }

  @Test
  void testToStringFormatting() {
    Circle c = new Circle(1.5, 2.5, 3.7);
    String result = c.toString();
    assertTrue(result.contains("1.50"));
    assertTrue(result.contains("2.50"));
    assertTrue(result.contains("3.70"));
  }
}