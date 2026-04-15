package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TrendingTopicsTest {

  private TrendingTopics trendingTopics;

  @BeforeEach
  void setUp() {
    trendingTopics = new TrendingTopics();
  }

  @Test
  void testCountTopicsExample() {
    List<String> topics = Arrays.asList(
        "Seattle", "wildfires", "DEFCON26", "NEU", "NEU",
        "Seattle", "Seattle", "NEU", "DEFCON26", "wildfires"
    );
    Map<String, Long> result = trendingTopics.countTopics(topics);

    assertEquals(4, result.size());
    assertEquals(3L, result.get("Seattle"));
    assertEquals(2L, result.get("wildfires"));
    assertEquals(2L, result.get("DEFCON26"));
    assertEquals(3L, result.get("NEU"));
  }

  @Test
  void testCountTopicsEmptyList() {
    Map<String, Long> result = trendingTopics.countTopics(Collections.emptyList());
    assertTrue(result.isEmpty());
  }

  @Test
  void testCountTopicsSingleElement() {
    List<String> topics = Collections.singletonList("Java");
    Map<String, Long> result = trendingTopics.countTopics(topics);

    assertEquals(1, result.size());
    assertEquals(1L, result.get("Java"));
  }

  @Test
  void testCountTopicsAllSame() {
    List<String> topics = Arrays.asList("AI", "AI", "AI", "AI");
    Map<String, Long> result = trendingTopics.countTopics(topics);

    assertEquals(1, result.size());
    assertEquals(4L, result.get("AI"));
  }

  @Test
  void testCountTopicsAllDistinct() {
    List<String> topics = Arrays.asList("A", "B", "C", "D");
    Map<String, Long> result = trendingTopics.countTopics(topics);

    assertEquals(4, result.size());
    topics.forEach(t -> assertEquals(1L, result.get(t)));
  }
}
