/**
 * Interface representing a geometric shape.
 * All shapes must be able to calculate their area and perimeter,
 * and must be comparable based on area.
 */
public interface Shape extends Comparable<Shape> {
  /**
   * Calculates the area of the shape.
   * @return the area of the shape
   */
  double area();

  /**
   * Calculates the perimeter of the shape.
   * @return the perimeter of the shape
   */
  double perimeter();

  /**
   * Calculates the distance from the origin to the shape's reference point.
   * @return the distance from the origin
   */
  double distanceFromOrigin();
}