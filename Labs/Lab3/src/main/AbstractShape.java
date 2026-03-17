import java.awt.geom.Point2D;

/**
 * Abstract base class for shapes that provides common functionality.
 * Delegates to Point2D for reference point management.
 */
public abstract class AbstractShape implements Shape {
  protected final Point2D reference;

  /**
   * Constructs an AbstractShape with a reference point.
   * @param reference the reference point for this shape
   */
  public AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  @Override
  public double distanceFromOrigin() {
    return reference.distance(0, 0);
  }

  @Override
  public int compareTo(Shape other) {
    return Double.compare(this.area(), other.area());
  }

  // area() and perimeter() are abstract - must be implemented by subclasses
  public abstract double area();
  public abstract double perimeter();
}