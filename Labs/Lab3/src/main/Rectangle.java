import java.awt.geom.Point2D;

/**
 * Represents a Rectangle shape defined by a reference point, width, and height.
 */
public class Rectangle extends AbstractShape {
  private final double width;
  private final double height;

  /**
   * Constructs a Rectangle with a reference point (lower-left corner), width, and height.
   * @param x x-coordinate of reference point
   * @param y y-coordinate of reference point
   * @param width width of the rectangle
   * @param height height of the rectangle
   * @throws IllegalArgumentException if width or height is not positive
   */
  public Rectangle(double x, double y, double width, double height) {
    super(new Point2D.Double(x, y));

    if (width <= 0) {
      throw new IllegalArgumentException("Width must be positive");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be positive");
    }

    this.width = width;
    this.height = height;
  }

  @Override
  public double area() {
    return width * height;
  }

  @Override
  public double perimeter() {
    return 2 * (width + height);
  }

  @Override
  public String toString() {
    return String.format("Rectangle: lower-left (%.2f,%.2f) width %.2f height %.2f",
        reference.getX(), reference.getY(), width, height);
  }
}