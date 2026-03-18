import java.util.List;

/**
 * Problem 2: Interface Comparable
 * Class Swimmer contains information about a swimmer, and the list of their
 * swimming times when swimming 50m. Four swimming styles are considered:
 * butterfly, backstroke, breaststroke, and freestyle.
 */
public class Swimmer implements Comparable<Swimmer> {

  private String name;
  private List<Double> butterfly50mTimes;
  private List<Double> backstroke50mTimes;
  private List<Double> breaststroke50mTimes;
  private List<Double> freestyle50mTimes;

  public Swimmer(String name, List<Double> butterfly50mTimes, List<Double> backstroke50mTimes,
      List<Double> breaststroke50mTimes, List<Double> freestyle50mTimes) {
    this.name = name;
    this.butterfly50mTimes = butterfly50mTimes;
    this.backstroke50mTimes = backstroke50mTimes;
    this.breaststroke50mTimes = breaststroke50mTimes;
    this.freestyle50mTimes = freestyle50mTimes;
  }

  public String getName() {
    return name;
  }

  public List<Double> getButterfly50mTimes() {
    return butterfly50mTimes;
  }

  public List<Double> getBackstroke50mTimes() {
    return backstroke50mTimes;
  }

  public List<Double> getBreaststroke50mTimes() {
    return breaststroke50mTimes;
  }

  public List<Double> getFreestyle50mTimes() {
    return freestyle50mTimes;
  }

  /** Returns the average of a list of times. */
  private double average(List<Double> times) {
    double sum = 0;
    for (double t : times) {
      sum += t;
    }
    return sum / times.size();
  }

  /**
   * Compares two Swimmer objects based on their average freestyle swimming times.
   * A lower average time means a faster swimmer (comes first).
   */
  @Override
  public int compareTo(Swimmer otherSwimmer) {
    double thisAvg = this.average(this.freestyle50mTimes);
    double otherAvg = this.average(otherSwimmer.freestyle50mTimes);
    return Double.compare(thisAvg, otherAvg);
  }
}
