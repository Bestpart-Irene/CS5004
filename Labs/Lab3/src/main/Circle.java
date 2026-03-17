import java.awt.geom.Point2D;

/**
 * Represents a Circle shape defined by a center point and radius.
 */
public class Circle extends AbstractShape {
  private final double radius;

  /**
   * Constructs a Circle with a center point and radius.
   * @param x x-coordinate of center
   * @param y y-coordinate of center
   * @param radius radius of the circle
   * @throws IllegalArgumentException if radius is not positive
   */
  public Circle(double x, double y, double radius) {
    super(new Point2D.Double(x, y));

    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be positive");
    }

    this.radius = radius;
  }

  @Override
  public double area() {
    return Math.PI * radius * radius;
  }

  @Override
  public double perimeter() {
    return 2 * Math.PI * radius;
  }

  @Override
  public String toString() {
    return String.format("Circle: center (%.2f,%.2f) radius %.2f",
        reference.getX(), reference.getY(), radius);
  }
}