import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SwimmersIteratorTest {

  private static final List<Double> FIVE_BUTTERFLY = Arrays.asList(28.0, 29.0, 27.5, 28.5, 30.0);
  private static final List<Double> FOUR_BUTTERFLY  = Arrays.asList(28.0, 29.0, 27.5, 28.5);

  private Swimmer qualifying() {
    // 5 butterfly times + one freestyle <= 26.17
    return new Swimmer("OlympicStar", FIVE_BUTTERFLY, new ArrayList<>(),
        new ArrayList<>(), Arrays.asList(26.00, 27.00));
  }

  private Swimmer noOlympicTime() {
    // 5 butterfly times but no freestyle <= 26.17
    return new Swimmer("SlowFreestyler", FIVE_BUTTERFLY, new ArrayList<>(),
        new ArrayList<>(), Arrays.asList(27.00, 28.00));
  }

  private Swimmer fewButterflyTimes() {
    // only 4 butterfly times, but qualifying freestyle
    return new Swimmer("FewButterfly", FOUR_BUTTERFLY, new ArrayList<>(),
        new ArrayList<>(), Arrays.asList(26.00));
  }

  @Test
  public void testOnlyQualifyingSwimmersReturned() {
    List<Swimmer> list = Arrays.asList(qualifying(), noOlympicTime(), fewButterflyTimes(), qualifying());
    SwimmersIterator it = new SwimmersIterator(list);

    assertTrue(it.hasNext());
    assertEquals("OlympicStar", it.next().getName());
    assertTrue(it.hasNext());
    assertEquals("OlympicStar", it.next().getName());
    assertFalse(it.hasNext());
  }

  @Test
  public void testEmptyList() {
    SwimmersIterator it = new SwimmersIterator(new ArrayList<>());
    assertFalse(it.hasNext());
  }

  @Test
  public void testNoQualifyingSwimmers() {
    List<Swimmer> list = Arrays.asList(noOlympicTime(), fewButterflyTimes());
    SwimmersIterator it = new SwimmersIterator(list);
    assertFalse(it.hasNext());
  }

  @Test
  public void testNextThrowsWhenExhausted() {
    SwimmersIterator it = new SwimmersIterator(new ArrayList<>());
    assertThrows(NoSuchElementException.class, it::next);
  }

  @Test
  public void testExactlyOlympicQualifyingTime() {
    // Exactly 26.17 should qualify (<= 26.17)
    Swimmer s = new Swimmer("Borderline", FIVE_BUTTERFLY, new ArrayList<>(),
        new ArrayList<>(), Arrays.asList(26.17));
    SwimmersIterator it = new SwimmersIterator(Arrays.asList(s));
    assertTrue(it.hasNext());
  }
}
