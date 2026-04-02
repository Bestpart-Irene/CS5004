package problem1;

/**
 * Interface for all smart home devices.
 */
public interface SmartDevice {
  void turnOn();
  void turnOff();
  String getStatus();
}
