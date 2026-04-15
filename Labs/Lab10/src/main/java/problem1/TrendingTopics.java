package problem1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for identifying trending topics on a social media platform.
 */
public class TrendingTopics {

  /**
   * Counts the number of occurrences of every String in the input list.
   *
   * @param topics a list of topic strings
   * @return a Map where each key is a distinct topic and the value is its occurrence count
   */
  public Map<String, Long> countTopics(List<String> topics) {
    return topics.stream()
        .collect(Collectors.groupingBy(topic -> topic, Collectors.counting()));
  }
}
