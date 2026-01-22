import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Shoe class
 * @author Aida
 */
class ShoeTest {

  /**
   * Tests the constructor with valid parameters
   */
  @Test
  void testConstructorValid() {
    Shoe shoe = new Shoe(Kind.RUNNING, Color.BLACK, Brand.NIKE, 10.5);
    assertNotNull(shoe, "Shoe object should be created");
    assertEquals(Kind.RUNNING, shoe.getKind(), "Kind should be RUNNING");
  }

  /**
   * Tests that constructor throws exception for Nike dress shoes
   */
  @Test
  void testConstructorInvalidNikeDress() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Shoe(Kind.DRESS, Color.BLACK, Brand.NIKE, 10.5);
    }, "Should throw IllegalArgumentException for Nike dress shoes");
  }

  /**
   * Tests the getKind method
   */
  @Test
  void testGetKind() {
    Shoe shoe1 = new Shoe(Kind.CASUAL, Color.WHITE, Brand.ADIDAS, 9.0);
    Shoe shoe2 = new Shoe(Kind.ATHLETIC, Color.RED, Brand.PUMA, 11.0);

    assertEquals(Kind.CASUAL, shoe1.getKind(), "Kind should be CASUAL");
    assertEquals(Kind.ATHLETIC, shoe2.getKind(), "Kind should be ATHLETIC");
  }

  /**
   * Tests the getColor method
   */
  @Test
  void testGetColor() {
    Shoe shoe1 = new Shoe(Kind.RUNNING, Color.BLUE, Brand.REEBOK, 8.5);
    Shoe shoe2 = new Shoe(Kind.CASUAL, Color.WHITE, Brand.ADIDAS, 10.0);

    assertEquals(Color.BLUE, shoe1.getColor(), "Color should be BLUE");
    assertEquals(Color.WHITE, shoe2.getColor(), "Color should be WHITE");
  }

  /**
   * Tests the getBrand method
   */
  @Test
  void testGetBrand() {
    Shoe shoe1 = new Shoe(Kind.RUNNING, Color.BLACK, Brand.NIKE, 10.5);
    Shoe shoe2 = new Shoe(Kind.DRESS, Color.BLACK, Brand.ADIDAS, 9.5);

    assertEquals(Brand.NIKE, shoe1.getBrand(), "Brand should be NIKE");
    assertEquals(Brand.ADIDAS, shoe2.getBrand(), "Brand should be ADIDAS");
  }

  /**
   * Tests the getSize method
   */
  @Test
  void testGetSize() {
    Shoe shoe1 = new Shoe(Kind.CASUAL, Color.RED, Brand.PUMA, 7.5);
    Shoe shoe2 = new Shoe(Kind.ATHLETIC, Color.BLUE, Brand.REEBOK, 12.0);

    assertEquals(7.5, shoe1.getSize(), 0.01, "Size should be 7.5");
    assertEquals(12.0, shoe2.getSize(), 0.01, "Size should be 12.0");
  }

  /**
   * Tests the toString method formatting
   */
  @Test
  void testToString() {
    Shoe shoe = new Shoe(Kind.RUNNING, Color.BLACK, Brand.NIKE, 10.5);
    String result = shoe.toString();

    assertTrue(result.contains("Running"), "toString should contain formatted kind");
    assertTrue(result.contains("Black"), "toString should contain formatted color");
    assertTrue(result.contains("Nike"), "toString should contain formatted brand");
    assertTrue(result.contains("10.5"), "toString should contain size");
  }

  /**
   * Tests toString with different enum values
   */
  @Test
  void testToStringDifferentValues() {
    Shoe shoe = new Shoe(Kind.DRESS, Color.WHITE, Brand.REEBOK, 9.0);
    String result = shoe.toString();

    assertTrue(result.contains("Dress"), "toString should format DRESS kind");
    assertTrue(result.contains("White"), "toString should format WHITE color");
  }
}