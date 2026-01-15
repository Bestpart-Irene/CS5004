import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class PersonTest {
  private Person alex;
  @BeforeEach
  void setUp() {
    this.alex = new Person("Alex White", "alex.white@gmail.com", "San Jose, CA, USA");
  }
  @Test
  void getName() {
    Assertions.assertEquals("Alex White", this.alex.getName());
  }
  @Test
  void getEmail() {
    Assertions.fail("Not yet implemented");
  }
  @Test
  void getAddress() {
    Assertions.fail("Not yet implemented");
  }
}