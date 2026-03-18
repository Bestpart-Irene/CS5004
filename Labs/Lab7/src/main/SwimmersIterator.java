import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Problem 3: Interface Iterator
 * Iterates over a list of Swimmer objects, returning only those who:
 *   1. Have at least 5 butterfly swim times recorded.
 *   2. Have achieved at least one 50m freestyle time <= 26.17s (Olympic qualifying time).
 */
public class SwimmersIterator implements Iterator<Swimmer> {

  private static final double OLYMPIC_QUALIFYING_TIME = 26.17;

  private final List<Swimmer> swimmers;
  private int currentIndex;
  private Swimmer nextSwimmer;

  public SwimmersIterator(List<Swimmer> swimmers) {
    this.swimmers = swimmers;
    this.currentIndex = 0;
    advance();
  }

  /** Advances internal pointer to the next qualifying swimmer. */
  private void advance() {
    nextSwimmer = null;
    while (currentIndex < swimmers.size()) {
      Swimmer candidate = swimmers.get(currentIndex);
      currentIndex++;
      if (qualifies(candidate)) {
        nextSwimmer = candidate;
        break;
      }
    }
  }

  /** Returns true if the swimmer meets both qualifying criteria. */
  private boolean qualifies(Swimmer s) {
    if (s.getButterfly50mTimes().size() < 5) {
      return false;
    }
    for (double time : s.getFreestyle50mTimes()) {
      if (time <= OLYMPIC_QUALIFYING_TIME) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasNext() {
    return nextSwimmer != null;
  }

  @Override
  public Swimmer next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Swimmer result = nextSwimmer;
    advance();
    return result;
  }
}
