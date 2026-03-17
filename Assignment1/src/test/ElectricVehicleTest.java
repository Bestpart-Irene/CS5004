import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for ElectricVehicle.
 * Tests all methods including constructor clamping, efficiency updates, and range calculations.
 */
public class ElectricVehicleTest {

  private static final double DELTA = 0.001; // Tolerance for double comparisons

  /**
   * Tests that the constructor properly initializes all fields with valid values.
   */
  @Test
  public void testConstructorValidValues() {
    ElectricVehicle ev = new ElectricVehicle("Tesla Model 3", 75.0, 0.8, 3.5);
    assertEquals("Tesla Model 3", ev.getName());
    assertEquals(75.0, ev.getBatterySize(), DELTA);
    assertEquals(0.8, ev.getStateOfCharge(), DELTA);
    assertEquals(3.5, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that battery capacity is clamped to minimum value of 10.0 kWh.
   */
  @Test
  public void testConstructorClampsBatteryCapacityMinimum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 5.0, 0.5, 2.0);
    assertEquals(10.0, ev.getBatterySize(), DELTA);
  }

  /**
   * Tests that battery capacity is clamped to maximum value of 150.0 kWh.
   */
  @Test
  public void testConstructorClampsBatteryCapacityMaximum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 160.5, 0.5, 2.0);
    assertEquals(150.0, ev.getBatterySize(), DELTA);
  }

  /**
   * Tests that default efficiency is clamped to minimum value of 0.5.
   */
  @Test
  public void testConstructorClampsDefaultEfficiencyMinimum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 0.3);
    assertEquals(0.5, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that default efficiency is clamped to maximum value of 4.5.
   */
  @Test
  public void testConstructorClampsDefaultEfficiencyMaximum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 5.0);
    assertEquals(4.5, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that state of charge is clamped to minimum value of 0.15 (15%).
   */
  @Test
  public void testConstructorClampsStateOfChargeMinimum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.05, 2.0);
    assertEquals(0.15, ev.getStateOfCharge(), DELTA);
  }

  /**
   * Tests that state of charge is clamped to maximum value of 1.0 (100%).
   */
  @Test
  public void testConstructorClampsStateOfChargeMaximum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 1.5, 2.0);
    assertEquals(1.0, ev.getStateOfCharge(), DELTA);
  }

  /**
   * Tests that null name is replaced with "Unknown EV".
   */
  @Test
  public void testConstructorHandlesNullName() {
    ElectricVehicle ev = new ElectricVehicle(null, 50.0, 0.5, 2.0);
    assertEquals("Unknown EV", ev.getName());
  }

  /**
   * Tests that empty string name is replaced with "Unknown EV".
   */
  @Test
  public void testConstructorHandlesEmptyName() {
    ElectricVehicle ev = new ElectricVehicle("", 50.0, 0.5, 2.0);
    assertEquals("Unknown EV", ev.getName());
  }

  /**
   * Tests that range is correctly calculated as efficiency × SOC × battery capacity.
   */
  @Test
  public void testRangeCalculation() {
    ElectricVehicle ev = new ElectricVehicle("Ford MachE", 88.0, 0.5, 3.0);
    // Range = 3.0 * 0.5 * 88.0 = 132.0
    assertEquals(132.0, ev.range(), DELTA);
  }

  /**
   * Tests that efficiency remains 100% of default in optimal temperature range (65-77°F).
   */
  @Test
  public void testUpdateEfficiencyOptimalTemperature() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(70.0);
    assertEquals(3.0, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency is at lower boundary of optimal range (65°F).
   */
  @Test
  public void testUpdateEfficiencyAt65Degrees() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(65.0);
    assertEquals(3.0, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency is at upper boundary of optimal range (77°F).
   */
  @Test
  public void testUpdateEfficiencyAt77Degrees() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(77.0);
    assertEquals(3.0, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency drops to 85% when temperature is above 77°F.
   */
  @Test
  public void testUpdateEfficiencyHotTemperature() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 4.0);
    ev.updateEfficiency(90.0);
    // 85% of 4.0 = 3.4
    assertEquals(3.4, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency decreases 1% per degree below 65°F.
   */
  @Test
  public void testUpdateEfficiencyColdTemperature() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(64.0);
    // 1 degree below 65°F = 99% efficiency
    // 99% of 3.0 = 2.97
    assertEquals(2.97, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency decreases correctly with fractional degrees.
   */
  @Test
  public void testUpdateEfficiencyColdTemperatureFractional() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(64.1);
    // 0.9 degrees below 65°F = 99.1% efficiency
    // 99.1% of 3.0 = 2.973
    assertEquals(2.973, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency bottoms out at 50% of default for very cold temperatures.
   */
  @Test
  public void testUpdateEfficiencyVeryColdTemperature() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(15.0);
    // 50 degrees below 65°F, but clamped to 50% efficiency
    // 50% of 3.0 = 1.5
    assertEquals(1.5, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that efficiency stays at 50% minimum even for extreme cold.
   */
  @Test
  public void testUpdateEfficiencyExtremeCold() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 3.0);
    ev.updateEfficiency(0.0);
    // Far below 65°F, but clamped to 50% efficiency
    // 50% of 3.0 = 1.5
    assertEquals(1.5, ev.getEfficiency(), DELTA);
  }

  /**
   * Tests that setStateOfCharge properly updates the state of charge.
   */
  @Test
  public void testSetStateOfChargeValidValue() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 2.0);
    ev.setStateOfCharge(0.75);
    assertEquals(0.75, ev.getStateOfCharge(), DELTA);
  }

  /**
   * Tests that setStateOfCharge clamps values below minimum to 0.15.
   */
  @Test
  public void testSetStateOfChargeClampsMinimum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 2.0);
    ev.setStateOfCharge(0.05);
    assertEquals(0.15, ev.getStateOfCharge(), DELTA);
  }

  /**
   * Tests that setStateOfCharge clamps values above maximum to 1.0.
   */
  @Test
  public void testSetStateOfChargeClampsMaximum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.5, 2.0);
    ev.setStateOfCharge(1.2);
    assertEquals(1.0, ev.getStateOfCharge(), DELTA);
  }

  /**
   * Tests that toString produces the correct format with percentage and range.
   */
  @Test
  public void testToString() {
    ElectricVehicle ev = new ElectricVehicle("Ford MachE", 88.0, 0.5, 3.0);
    String expected = "Ford MachE SOC: 50.0% Range (miles): 132.0";
    assertEquals(expected, ev.toString());
  }

  /**
   * Tests toString with different values to verify formatting.
   */
  @Test
  public void testToStringDifferentValues() {
    ElectricVehicle ev = new ElectricVehicle("Tesla Model S", 100.0, 0.8, 4.0);
    String expected = "Tesla Model S SOC: 80.0% Range (miles): 320.0";
    assertEquals(expected, ev.toString());
  }

  /**
   * Tests that efficiency changes affect range calculation.
   */
  @Test
  public void testRangeAfterEfficiencyUpdate() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 100.0, 1.0, 4.0);
    // Initial range: 4.0 * 1.0 * 100.0 = 400.0
    assertEquals(400.0, ev.range(), DELTA);

    // Update efficiency for hot weather (85%)
    ev.updateEfficiency(90.0);
    // New range: 3.4 * 1.0 * 100.0 = 340.0
    assertEquals(340.0, ev.range(), DELTA);
  }

  /**
   * Tests boundary value at exactly the state of charge minimum.
   */
  @Test
  public void testStateOfChargeBoundaryMinimum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 0.15, 2.0);
    assertEquals(0.15, ev.getStateOfCharge(), DELTA);
  }

  /**
   * Tests boundary value at exactly the state of charge maximum.
   */
  @Test
  public void testStateOfChargeBoundaryMaximum() {
    ElectricVehicle ev = new ElectricVehicle("Test EV", 50.0, 1.0, 2.0);
    assertEquals(1.0, ev.getStateOfCharge(), DELTA);
  }
}