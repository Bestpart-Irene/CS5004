/**
 * Represents a Shoe with its kind, color, brand, and size
 * @author Aida
 */
public class Shoe {
  private Kind kind;
  private Color color;
  private Brand brand;
  private double size;

  /**
   * Creates a new shoe with the specified attributes
   * @param kind the type of shoe (running, casual, dress, athletic)
   * @param color the color of the shoe
   * @param brand the brand of the shoe
   * @param size the size of the shoe
   * @throws IllegalArgumentException if brand is NIKE and kind is DRESS
   */
  public Shoe(Kind kind, Color color, Brand brand, double size) {
    if (brand == Brand.NIKE && kind == Kind.DRESS) {
      throw new IllegalArgumentException("Nike does not make dress shoes");
    }
    this.kind = kind;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  /**
   * Gets the kind of shoe
   * @return the kind of shoe
   */
  public Kind getKind() {
    return this.kind;
  }

  /**
   * Gets the color of the shoe
   * @return the color of the shoe
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Gets the brand of the shoe
   * @return the brand of the shoe
   */
  public Brand getBrand() {
    return this.brand;
  }

  /**
   * Gets the size of the shoe
   * @return the size of the shoe
   */
  public double getSize() {
    return this.size;
  }

  /**
   * Returns a string representation of the shoe with custom formatting
   * @return a formatted string containing all shoe attributes
   */
  @Override
  public String toString() {
    String kindStr = formatKind(this.kind);
    String colorStr = formatColor(this.color);
    String brandStr = formatBrand(this.brand);

    return String.format("Shoe [Kind: %s, Color: %s, Brand: %s, Size: %.1f]",
        kindStr, colorStr, brandStr, this.size);
  }

  /**
   * Formats the kind enum value with custom capitalization
   * @param kind the kind to format
   * @return formatted kind string
   */
  private String formatKind(Kind kind) {
    switch (kind) {
      case RUNNING:
        return "Running";
      case CASUAL:
        return "Casual";
      case DRESS:
        return "Dress";
      case ATHLETIC:
        return "Athletic";
      default:
        return "Unknown";
    }
  }

  /**
   * Formats the color enum value with custom capitalization
   * @param color the color to format
   * @return formatted color string
   */
  private String formatColor(Color color) {
    switch (color) {
      case BLACK:
        return "Black";
      case WHITE:
        return "White";
      case BLUE:
        return "Blue";
      case RED:
        return "Red";
      default:
        return "Neutral";
    }
  }

  /**
   * Formats the brand enum value with custom capitalization
   * @param brand the brand to format
   * @return formatted brand string
   */
  private String formatBrand(Brand brand) {
    switch (brand) {
      case NIKE:
        return "Nike";
      case ADIDAS:
        return "Adidas";
      case PUMA:
        return "Puma";
      case REEBOK:
        return "Reebok";
      default:
        return "Unknown";
    }
  }
}