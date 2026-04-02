package problem1;

public class SmartHomeFacade {
  private final SmartHomeController controller;

  public SmartHomeFacade() {
    this.controller = SmartHomeController.getInstance();
  }

  public void activateNightMode() {
    System.out.println("\n=== Activating Night Mode ===");
    for (SmartDevice device : controller.getDevices()) {
      if (device instanceof SmartLight) {
        device.turnOff();
      } else if (device instanceof SmartSpeaker) {
        device.turnOff();
      } else {
        // Keep thermostat on at night
        device.turnOn();
      }
    }
    System.out.println("Night mode activated.\n");
  }

  public void leaveHome() {
    System.out.println("\n=== Leaving Home ===");
    controller.turnAllOff();
    System.out.println("All devices turned off. Goodbye!\n");
  }

  public void arriveHome() {
    System.out.println("\n=== Arriving Home ===");
    controller.turnAllOn();
    System.out.println("Welcome home! All devices turned on.\n");
  }
}
