import java.awt.geom.Point2D;

/**
 * Represents a Triangle shape defined by three points.
 * The first point serves as the reference point for distance from origin.
 */
public class Triangle extends AbstractShape {
  private final Point2D point2;
  private final Point2D point3;

  /**
   * Constructs a Triangle with three points.
   * @param x1 x-coordinate of first point (reference point)
   * @param y1 y-coordinate of first point (reference point)
   * @param x2 x-coordinate of second point
   * @param y2 y-coordinate of second point
   * @param x3 x-coordinate of third point
   * @param y3 y-coordinate of third point
   * @throws IllegalArgumentException if any two points are identical
   */
  public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
    super(new Point2D.Double(x1, y1));

    this.point2 = new Point2D.Double(x2, y2);
    this.point3 = new Point2D.Double(x3, y3);

    // Check that no two points are identical
    if (reference.equals(point2) || reference.equals(point3) || point2.equals(point3)) {
      throw new IllegalArgumentException("Triangle cannot have two or more identical points");
    }
  }

  @Override
  public double area() {
    // Calculate side lengths
    double a = reference.distance(point2);
    double b = point2.distance(point3);
    double c = point3.distance(reference);

    // Use Heron's formula: area = sqrt(s(s-a)(s-b)(s-c))
    // where s is the semi-perimeter
    double s = (a + b + c) / 2.0;
    double areaSquared = s * (s - a) * (s - b) * (s - c);

    // Handle collinear points (area would be 0 or negative due to rounding)
    if (areaSquared <= 0) {
      return 0.0;
    }

    return Math.sqrt(areaSquared);
  }

  @Override
  public double perimeter() {
    double side1 = reference.distance(point2);
    double side2 = point2.distance(point3);
    double side3 = point3.distance(reference);
    return side1 + side2 + side3;
  }

  @Override
  public String toString() {
    return String.format("Triangle: vertices (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f)",
        reference.getX(), reference.getY(),
        point2.getX(), point2.getY(),
        point3.getX(), point3.getY());
  }
}