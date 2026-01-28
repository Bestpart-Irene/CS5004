import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Triangle-specific methods.
 */
class TriangleTest {

  @Test
  void testConstructorValid() {
    // Test that a valid triangle can be constructed
    Triangle t = new Triangle(0, 0, 3, 0, 0, 4);
    assertNotNull(t);
  }

  @Test
  void testConstructorTwoIdenticalPoints() {
    // Test that identical first and second points throw exception
    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(0, 0, 0, 0, 3, 4);
    });
  }

  @Test
  void testConstructorSecondAndThirdIdentical() {
    // Test that identical second and third points throw exception
    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(0, 0, 3, 4, 3, 4);
    });
  }

  @Test
  void testConstructorFirstAndThirdIdentical() {
    // Test that identical first and third points throw exception
    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(0, 0, 3, 4, 0, 0);
    });
  }

  @Test
  void testConstructorAllIdentical() {
    // Test that all three identical points throw exception
    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(1, 1, 1, 1, 1, 1);
    });
  }

  @Test
  void testAreaRightTriangle() {
    // 3-4-5 right triangle with legs of 3 and 4
    Triangle t = new Triangle(0, 0, 3, 0, 0, 4);
    assertEquals(6.0, t.area(), 0.001);
  }

  @Test
  void testAreaEquilateralTriangle() {
    // Equilateral triangle with side length 2
    Triangle t = new Triangle(0, 0, 2, 0, 1, Math.sqrt(3));
    assertEquals(Math.sqrt(3), t.area(), 0.001);
  }

  @Test
  void testAreaCollinearPoints() {
    // Three points on the same line should have area 0
    Triangle t = new Triangle(0, 0, 1, 1, 2, 2);
    assertEquals(0.0, t.area(), 0.001);
  }

  @Test
  void testAreaGeneralTriangle() {
    // General triangle
    Triangle t = new Triangle(1, 1, 4, 1, 2.5, 4);
    double expected = 4.5; // base=3, height=3, area=4.5
    assertEquals(expected, t.area(), 0.001);
  }

  @Test
  void testPerimeterRightTriangle() {
    // 3-4-5 right triangle
    Triangle t = new Triangle(0, 0, 3, 0, 0, 4);
    assertEquals(12.0, t.perimeter(), 0.001);
  }

  @Test
  void testPerimeterEquilateralTriangle() {
    // Equilateral triangle with side length 2
    Triangle t = new Triangle(0, 0, 2, 0, 1, Math.sqrt(3));
    assertEquals(6.0, t.perimeter(), 0.001);
  }

  @Test
  void testPerimeterCollinearPoints() {
    // Collinear points still have a valid perimeter
    Triangle t = new Triangle(0, 0, 1, 0, 3, 0);
    assertEquals(6.0, t.perimeter(), 0.001); // 1 + 2 + 3
  }

  @Test
  void testToString() {
    Triangle t = new Triangle(0, 0, 3, 0, 0, 4);
    String result = t.toString();
    assertTrue(result.contains("Triangle"));
    assertTrue(result.contains("0.00"));
    assertTrue(result.contains("3.00"));
    assertTrue(result.contains("4.00"));
  }

  @Test
  void testToStringFormatting() {
    Triangle t = new Triangle(1.5, 2.5, 3.7, 4.8, 5.9, 6.1);
    String result = t.toString();
    assertTrue(result.contains("1.50"));
    assertTrue(result.contains("2.50"));
    assertTrue(result.contains("3.70"));
  }
}