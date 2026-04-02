package problem1;

public class SmartSpeaker implements SmartDevice {
  private boolean isOn;

  public SmartSpeaker() {
    this.isOn = false;
  }

  @Override
  public void turnOn() {
    this.isOn = true;
    System.out.println("Smart Speaker turned ON.");
  }

  @Override
  public void turnOff() {
    this.isOn = false;
    System.out.println("Smart Speaker turned OFF.");
  }

  @Override
  public String getStatus() {
    return "Smart Speaker is " + (isOn ? "ON" : "OFF");
  }
}
