import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwimmerTest {

  private Swimmer makeSwimmer(String name, List<Double> freestyleTimes) {
    List<Double> empty = Arrays.asList();
    return new Swimmer(name, empty, empty, empty, freestyleTimes);
  }

  @Test
  public void testFasterSwimmerComesFirst() {
    Swimmer alice = makeSwimmer("Alice", Arrays.asList(25.0, 26.0)); // avg = 25.5
    Swimmer bob   = makeSwimmer("Bob",   Arrays.asList(27.0, 28.0)); // avg = 27.5
    assertTrue(alice.compareTo(bob) < 0);
    assertTrue(bob.compareTo(alice) > 0);
  }

  @Test
  public void testEqualAverageTimes() {
    Swimmer alice = makeSwimmer("Alice", Arrays.asList(26.0, 26.0));
    Swimmer bob   = makeSwimmer("Bob",   Arrays.asList(24.0, 28.0));
    assertEquals(0, alice.compareTo(bob));
  }

  @Test
  public void testCompareToSelf() {
    Swimmer alice = makeSwimmer("Alice", Arrays.asList(25.5, 26.5));
    assertEquals(0, alice.compareTo(alice));
  }
}
