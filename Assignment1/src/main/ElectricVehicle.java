/**
 * Represents an electric vehicle with battery capacity, charge state, and efficiency metrics.
 * The vehicle's range is calculated based on current efficiency, state of charge, and battery capacity.
 */
public class ElectricVehicle {
  private String name;
  private double batteryCapacity;
  private double stateOfCharge;
  private double currentEfficiency;
  private double defaultEfficiency;

  /**
   * Constructs an ElectricVehicle with the specified parameters.
   * Values are clamped to valid ranges as follows:
   * - Battery capacity: 10.0 to 150.0 kWh
   * - State of charge: 0.15 to 1.0 (15% to 100%)
   * - Default efficiency: 0.5 to 4.5
   * - Name: set to "Unknown EV" if null or empty
   *
   * @param name the name of the vehicle
   * @param batteryCapacity the battery capacity in kWh
   * @param stateOfCharge the state of charge as a decimal (0.15 to 1.0)
   * @param defaultEfficiency the default efficiency rating
   */
  public ElectricVehicle(String name, double batteryCapacity, double stateOfCharge, double defaultEfficiency) {
    // Handle name
    if (name == null || name.length() == 0) {
      this.name = "Unknown EV";
    } else {
      this.name = name;
    }

    // Clamp battery capacity between 10.0 and 150.0
    if (batteryCapacity < 10.0) {
      this.batteryCapacity = 10.0;
    } else if (batteryCapacity > 150.0) {
      this.batteryCapacity = 150.0;
    } else {
      this.batteryCapacity = batteryCapacity;
    }

    // Clamp state of charge between 0.15 and 1.0
    if (stateOfCharge < 0.15) {
      this.stateOfCharge = 0.15;
    } else if (stateOfCharge > 1.0) {
      this.stateOfCharge = 1.0;
    } else {
      this.stateOfCharge = stateOfCharge;
    }

    // Clamp default efficiency between 0.5 and 4.5
    if (defaultEfficiency < 0.5) {
      this.defaultEfficiency = 0.5;
    } else if (defaultEfficiency > 4.5) {
      this.defaultEfficiency = 4.5;
    } else {
      this.defaultEfficiency = defaultEfficiency;
    }

    // Initialize current efficiency to default efficiency
    this.currentEfficiency = this.defaultEfficiency;
  }

  /**
   * Calculates and returns the current range of the vehicle in miles.
   * Range is calculated as: current efficiency × state of charge × battery capacity
   *
   * @return the range in miles
   */
  public double range() {
    return currentEfficiency * stateOfCharge * batteryCapacity;
  }

  /**
   * Updates the current efficiency based on the current temperature in Fahrenheit.
   * Rules:
   * - If 65°F ≤ temp ≤ 77°F: efficiency is 100% of default
   * - If temp > 77°F: efficiency is 85% of default
   * - If temp < 65°F: efficiency decreases 1% per degree below 65°F, minimum 50% of default
   *
   * @param currentTemp the current temperature in Fahrenheit
   */
  public void updateEfficiency(double currentTemp) {
    if (currentTemp >= 65.0 && currentTemp <= 77.0) {
      // Optimal temperature range: 100% efficiency
      currentEfficiency = defaultEfficiency;
    } else if (currentTemp > 77.0) {
      // Hot temperature: 85% efficiency
      currentEfficiency = defaultEfficiency * 0.85;
    } else {
      // Cold temperature: decreases 1% per degree below 65°F, minimum 50%
      double degreesBelow = 65.0 - currentTemp;
      double percentageDecrease = Math.min(degreesBelow, 50.0);
      currentEfficiency = defaultEfficiency * (100.0 - percentageDecrease) / 100.0;
    }
  }

  /**
   * Returns the current efficiency of the vehicle.
   *
   * @return the current efficiency
   */
  public double getEfficiency() {
    return currentEfficiency;
  }

  /**
   * Returns the battery capacity in kWh.
   *
   * @return the battery capacity
   */
  public double getBatterySize() {
    return batteryCapacity;
  }

  /**
   * Returns the current state of charge as a decimal value (0.15 to 1.0).
   *
   * @return the state of charge
   */
  public double getStateOfCharge() {
    return stateOfCharge;
  }

  /**
   * Returns the name of the vehicle.
   *
   * @return the vehicle name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the state of charge to a new value.
   * The value is clamped between 0.15 and 1.0.
   *
   * @param stateOfCharge the new state of charge
   */
  public void setStateOfCharge(double stateOfCharge) {
    if (stateOfCharge < 0.15) {
      this.stateOfCharge = 0.15;
    } else if (stateOfCharge > 1.0) {
      this.stateOfCharge = 1.0;
    } else {
      this.stateOfCharge = stateOfCharge;
    }
  }

  /**
   * Returns a string representation of the electric vehicle.
   * Format: "Name SOC: XX.X% Range (miles): XXX.X"
   *
   * @return string representation of the vehicle
   */
  @Override
  public String toString() {
    double socPercentage = stateOfCharge * 100.0;
    double rangeValue = range();
    return String.format("%s SOC: %.1f%% Range (miles): %.1f", name, socPercentage, rangeValue);
  }
}