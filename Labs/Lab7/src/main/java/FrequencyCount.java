import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem 1: Data Collections
 * Computes the relative frequency of each integer in the input list.
 */
public class FrequencyCount {

  /**
   * Returns a Map where each key is a number from the list and the value is
   * its relative frequency (occurrences / total size).
   */
  public static Map<Integer, Double> frequencyCount(List<Integer> numbers) {
    if (numbers == null || numbers.isEmpty()) {
      return new HashMap<>();
    }

    Map<Integer, Integer> counts = new HashMap<>();
    for (int n : numbers) {
      counts.put(n, counts.getOrDefault(n, 0) + 1);
    }

    double total = numbers.size();
    Map<Integer, Double> result = new HashMap<>();
    for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
      result.put(entry.getKey(), entry.getValue() / total);
    }
    return result;
  }
}
