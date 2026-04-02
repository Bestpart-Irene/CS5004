package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

  @BeforeEach
  void resetController() {
    // Clear singleton state between tests
    SmartHomeController controller = SmartHomeController.getInstance();
    controller.getDevices().clear();
  }

  // ===== Factory Pattern Tests =====

  @Test
  void testFactoryCreatesSmartLight() {
    SmartDevice device = DeviceFactory.createDevice("light");
    assertNotNull(device);
    assertInstanceOf(SmartLight.class, device);
  }

  @Test
  void testFactoryCreatesSmartSpeaker() {
    SmartDevice device = DeviceFactory.createDevice("speaker");
    assertNotNull(device);
    assertInstanceOf(SmartSpeaker.class, device);
  }

  @Test
  void testFactoryCaseInsensitive() {
    SmartDevice device = DeviceFactory.createDevice("LIGHT");
    assertInstanceOf(SmartLight.class, device);
  }

  @Test
  void testFactoryThrowsOnUnknownType() {
    assertThrows(IllegalArgumentException.class, () -> DeviceFactory.createDevice("fan"));
  }

  // ===== SmartLight Tests =====

  @Test
  void testSmartLightDefaultOff() {
    SmartLight light = new SmartLight();
    assertEquals("Smart Light is OFF", light.getStatus());
  }

  @Test
  void testSmartLightTurnOnOff() {
    SmartLight light = new SmartLight();
    light.turnOn();
    assertEquals("Smart Light is ON", light.getStatus());
    light.turnOff();
    assertEquals("Smart Light is OFF", light.getStatus());
  }

  // ===== SmartSpeaker Tests =====

  @Test
  void testSmartSpeakerDefaultOff() {
    SmartSpeaker speaker = new SmartSpeaker();
    assertEquals("Smart Speaker is OFF", speaker.getStatus());
  }

  @Test
  void testSmartSpeakerTurnOnOff() {
    SmartSpeaker speaker = new SmartSpeaker();
    speaker.turnOn();
    assertEquals("Smart Speaker is ON", speaker.getStatus());
    speaker.turnOff();
    assertEquals("Smart Speaker is OFF", speaker.getStatus());
  }

  // ===== Adapter Pattern Tests =====

  @Test
  void testLegacyThermostatAdapter() {
    LegacyThermostat legacy = new LegacyThermostat();
    SmartDevice adapter = new LegacyThermostatAdapter(legacy);
    assertEquals("Legacy Thermostat - Current Temperature: 68°F", adapter.getStatus());
  }

  @Test
  void testLegacyThermostatAdapterTurnOnOff() {
    LegacyThermostat legacy = new LegacyThermostat();
    SmartDevice adapter = new LegacyThermostatAdapter(legacy);
    // Should not throw
    adapter.turnOn();
    adapter.turnOff();
  }

  // ===== Singleton Pattern Tests =====

  @Test
  void testSingletonReturnsSameInstance() {
    SmartHomeController c1 = SmartHomeController.getInstance();
    SmartHomeController c2 = SmartHomeController.getInstance();
    assertSame(c1, c2);
  }

  @Test
  void testControllerAddDevice() {
    SmartHomeController controller = SmartHomeController.getInstance();
    SmartDevice light = new SmartLight();
    controller.addDevice(light);
    assertEquals(1, controller.getDevices().size());
  }

  @Test
  void testControllerTurnAllOnOff() {
    SmartHomeController controller = SmartHomeController.getInstance();
    SmartLight light = new SmartLight();
    SmartSpeaker speaker = new SmartSpeaker();
    controller.addDevice(light);
    controller.addDevice(speaker);

    controller.turnAllOn();
    assertEquals("Smart Light is ON", light.getStatus());
    assertEquals("Smart Speaker is ON", speaker.getStatus());

    controller.turnAllOff();
    assertEquals("Smart Light is OFF", light.getStatus());
    assertEquals("Smart Speaker is OFF", speaker.getStatus());
  }

  // ===== Facade Pattern Tests =====

  @Test
  void testFacadeNightMode() {
    SmartHomeController controller = SmartHomeController.getInstance();
    SmartLight light = new SmartLight();
    SmartSpeaker speaker = new SmartSpeaker();
    LegacyThermostat legacy = new LegacyThermostat();
    LegacyThermostatAdapter thermostat = new LegacyThermostatAdapter(legacy);

    light.turnOn();
    speaker.turnOn();

    controller.addDevice(light);
    controller.addDevice(speaker);
    controller.addDevice(thermostat);

    SmartHomeFacade facade = new SmartHomeFacade();
    facade.activateNightMode();

    assertEquals("Smart Light is OFF", light.getStatus());
    assertEquals("Smart Speaker is OFF", speaker.getStatus());
  }

  @Test
  void testFacadeLeaveHome() {
    SmartHomeController controller = SmartHomeController.getInstance();
    SmartLight light = new SmartLight();
    light.turnOn();
    controller.addDevice(light);

    SmartHomeFacade facade = new SmartHomeFacade();
    facade.leaveHome();

    assertEquals("Smart Light is OFF", light.getStatus());
  }

  @Test
  void testFacadeArriveHome() {
    SmartHomeController controller = SmartHomeController.getInstance();
    SmartLight light = new SmartLight();
    controller.addDevice(light);

    SmartHomeFacade facade = new SmartHomeFacade();
    facade.arriveHome();

    assertEquals("Smart Light is ON", light.getStatus());
  }
}
