package problem1;

public class LegacyThermostat {
  private boolean active;
  private int temperature;

  public LegacyThermostat() {
    this.active = false;
    this.temperature = 68;
  }

  public void activate() {
    this.active = true;
    System.out.println("Legacy Thermostat activated.");
  }

  public void deactivate() {
    this.active = false;
    System.out.println("Legacy Thermostat deactivated.");
  }

  public int getCurrentTemp() {
    return this.temperature;
  }
}
