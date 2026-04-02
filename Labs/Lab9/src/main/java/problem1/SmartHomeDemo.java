package problem1;

public class SmartHomeDemo {

  public static void main(String[] args) {
    // 1. Creating devices through the factory
    System.out.println("=== Creating Devices via Factory ===");
    SmartDevice light = DeviceFactory.createDevice("light");
    SmartDevice speaker = DeviceFactory.createDevice("speaker");

    // 2. Using the adapter for the legacy thermostat
    System.out.println("\n=== Adapting Legacy Thermostat ===");
    LegacyThermostat oldThermostat = new LegacyThermostat();
    SmartDevice thermostat = new LegacyThermostatAdapter(oldThermostat);

    // 3. Adding devices to the singleton controller
    System.out.println("\n=== Adding Devices to Singleton Controller ===");
    SmartHomeController controller = SmartHomeController.getInstance();
    controller.addDevice(light);
    controller.addDevice(speaker);
    controller.addDevice(thermostat);

    // Verify singleton - same instance
    SmartHomeController sameController = SmartHomeController.getInstance();
    System.out.println("Same controller instance? " + (controller == sameController));

    // Turn on all devices and show status
    System.out.println("\n=== Turning All Devices ON ===");
    controller.turnAllOn();
    controller.showAllStatuses();

    // 4. Using the facade for complex operations
    SmartHomeFacade facade = new SmartHomeFacade();

    facade.activateNightMode();
    controller.showAllStatuses();

    facade.arriveHome();
    controller.showAllStatuses();

    facade.leaveHome();
    controller.showAllStatuses();
  }
}
