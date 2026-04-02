package problem1;

public class SmartLight implements SmartDevice {
  private boolean isOn;

  public SmartLight() {
    this.isOn = false;
  }

  @Override
  public void turnOn() {
    this.isOn = true;
    System.out.println("Smart Light turned ON.");
  }

  @Override
  public void turnOff() {
    this.isOn = false;
    System.out.println("Smart Light turned OFF.");
  }

  @Override
  public String getStatus() {
    return "Smart Light is " + (isOn ? "ON" : "OFF");
  }
}
