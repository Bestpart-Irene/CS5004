import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FrequencyCountTest {

  @Test
  public void testExampleFromPdf() {
    List<Integer> input = Arrays.asList(1, 1, 1, 2, 2, 3, 3, 4, 4, 4);
    Map<Integer, Double> result = FrequencyCount.frequencyCount(input);

    assertEquals(0.3, result.get(1), 1e-9);
    assertEquals(0.2, result.get(2), 1e-9);
    assertEquals(0.2, result.get(3), 1e-9);
    assertEquals(0.3, result.get(4), 1e-9);
  }

  @Test
  public void testSingleElement() {
    List<Integer> input = Arrays.asList(5);
    Map<Integer, Double> result = FrequencyCount.frequencyCount(input);
    assertEquals(1.0, result.get(5), 1e-9);
  }

  @Test
  public void testAllSame() {
    List<Integer> input = Arrays.asList(7, 7, 7, 7);
    Map<Integer, Double> result = FrequencyCount.frequencyCount(input);
    assertEquals(1.0, result.get(7), 1e-9);
    assertEquals(1, result.size());
  }

  @Test
  public void testEmptyList() {
    List<Integer> input = Arrays.asList();
    Map<Integer, Double> result = FrequencyCount.frequencyCount(input);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testAllDistinct() {
    List<Integer> input = Arrays.asList(1, 2, 3, 4);
    Map<Integer, Double> result = FrequencyCount.frequencyCount(input);
    for (int i = 1; i <= 4; i++) {
      assertEquals(0.25, result.get(i), 1e-9);
    }
  }
}
