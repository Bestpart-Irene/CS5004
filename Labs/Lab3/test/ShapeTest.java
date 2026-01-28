import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for inherited Shape methods (compareTo, distanceFromOrigin).
 * Tests the methods that all shapes inherit from AbstractShape.
 */
class ShapeTest {

  @Test
  void testCompareToEqual() {
    // Two shapes with same area should return 0
    Circle c1 = new Circle(0, 0, 5);
    Circle c2 = new Circle(10, 10, 5);
    assertEquals(0, c1.compareTo(c2));
  }

  @Test
  void testCompareToLess() {
    // Shape with smaller area should return negative
    Circle c1 = new Circle(0, 0, 3);
    Circle c2 = new Circle(0, 0, 5);
    assertTrue(c1.compareTo(c2) < 0);
  }

  @Test
  void testCompareToGreater() {
    // Shape with larger area should return positive
    Circle c1 = new Circle(0, 0, 5);
    Circle c2 = new Circle(0, 0, 3);
    assertTrue(c1.compareTo(c2) > 0);
  }

  @Test
  void testCompareToCircleVsRectangle() {
    Circle c = new Circle(0, 0, 2); // area = 4π ≈ 12.566
    Rectangle r = new Rectangle(0, 0, 3, 4); // area = 12
    assertTrue(c.compareTo(r) > 0);
  }

  @Test
  void testCompareToRectangleVsTriangle() {
    Rectangle r = new Rectangle(0, 0, 4, 3); // area = 12
    Triangle t = new Triangle(0, 0, 6, 0, 0, 8); // area = 24
    assertTrue(r.compareTo(t) < 0);
  }

  @Test
  void testCompareToTriangleVsCircle() {
    Triangle t = new Triangle(0, 0, 3, 0, 0, 4); // area = 6
    Circle c = new Circle(0, 0, 2); // area = 4π ≈ 12.566
    assertTrue(t.compareTo(c) < 0);
  }

  @Test
  void testDistanceFromOriginCircleAtOrigin() {
    Circle c = new Circle(0, 0, 5);
    assertEquals(0.0, c.distanceFromOrigin(), 0.001);
  }

  @Test
  void testDistanceFromOriginCircleNotAtOrigin() {
    Circle c = new Circle(3, 4, 5);
    assertEquals(5.0, c.distanceFromOrigin(), 0.001);
  }

  @Test
  void testDistanceFromOriginRectangle() {
    Rectangle r = new Rectangle(6, 8, 5, 3);
    assertEquals(10.0, r.distanceFromOrigin(), 0.001);
  }

  @Test
  void testDistanceFromOriginTriangle() {
    Triangle t = new Triangle(5, 12, 0, 0, 10, 10);
    assertEquals(13.0, t.distanceFromOrigin(), 0.001);
  }

  @Test
  void testDistanceFromOriginNegativeCoordinates() {
    Circle c = new Circle(-3, -4, 5);
    assertEquals(5.0, c.distanceFromOrigin(), 0.001);
  }

  @Test
  void testCompareToWithDecimalAreas() {
    Rectangle r = new Rectangle(0, 0, 2.5, 3.5); // area = 8.75
    Triangle t = new Triangle(0, 0, 3, 0, 0, 6); // area = 9
    assertTrue(r.compareTo(t) < 0);
  }

  @Test
  void testCompareToAllThreeShapeTypes() {
    Circle c = new Circle(0, 0, 1); // area = π ≈ 3.14
    Rectangle r = new Rectangle(0, 0, 2, 2); // area = 4
    Triangle t = new Triangle(0, 0, 2, 0, 0, 4); // area = 4

    assertTrue(c.compareTo(r) < 0);
    assertEquals(0, r.compareTo(t));
    assertTrue(t.compareTo(c) > 0);
  }
}