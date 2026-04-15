package problem2;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OlderVehiclesFilterTest {

  @Test
  void testFilterOlderVehiclesMixed() {
    List<Vehicle> vehicles = Arrays.asList(
        new Vehicle("Toyota", "Camry", 1995, Color.RED),
        new Vehicle("Honda", "Civic", 2005, Color.BLUE),
        new Vehicle("Ford", "Mustang", 1998, Color.BLACK),
        new Vehicle("Tesla", "Model3", 2020, Color.WHITE)
    );
    OlderVehiclesFilter filter = new OlderVehiclesFilter(vehicles);
    List<String> result = filter.filterOlderVehicles();

    assertEquals(2, result.size());
    assertEquals("Toyota Camry 1995", result.get(0));
    assertEquals("Ford Mustang 1998", result.get(1));
  }

  @Test
  void testFilterOlderVehiclesNoneOld() {
    List<Vehicle> vehicles = Arrays.asList(
        new Vehicle("Honda", "Civic", 2005, Color.BLUE),
        new Vehicle("Tesla", "Model3", 2020, Color.WHITE)
    );
    OlderVehiclesFilter filter = new OlderVehiclesFilter(vehicles);
    List<String> result = filter.filterOlderVehicles();

    assertTrue(result.isEmpty());
  }

  @Test
  void testFilterOlderVehiclesAllOld() {
    List<Vehicle> vehicles = Arrays.asList(
        new Vehicle("Toyota", "Camry", 1995, Color.RED),
        new Vehicle("Ford", "Mustang", 1970, Color.BLACK)
    );
    OlderVehiclesFilter filter = new OlderVehiclesFilter(vehicles);
    List<String> result = filter.filterOlderVehicles();

    assertEquals(2, result.size());
  }

  @Test
  void testFilterOlderVehiclesEmptyList() {
    OlderVehiclesFilter filter = new OlderVehiclesFilter(Collections.emptyList());
    List<String> result = filter.filterOlderVehicles();

    assertTrue(result.isEmpty());
  }

  @Test
  void testFilterOlderVehiclesBoundary1999() {
    List<Vehicle> vehicles = Arrays.asList(
        new Vehicle("BMW", "M3", 1999, Color.GRAY),
        new Vehicle("Audi", "A4", 1998, Color.WHITE)
    );
    OlderVehiclesFilter filter = new OlderVehiclesFilter(vehicles);
    List<String> result = filter.filterOlderVehicles();

    // 1999 is NOT before 1999, so only 1998 should be included
    assertEquals(1, result.size());
    assertEquals("Audi A4 1998", result.get(0));
  }

  @Test
  void testThreeArgConstructor() {
    Vehicle v1 = new Vehicle("Toyota", "Camry", 1995, Color.RED);
    Vehicle v2 = new Vehicle("Honda", "Civic", 2005, Color.BLUE);
    Vehicle v3 = new Vehicle("Ford", "Mustang", 1998, Color.BLACK);

    OlderVehiclesFilter filter = new OlderVehiclesFilter(v1, v2, v3);
    List<String> result = filter.filterOlderVehicles();

    assertEquals(2, result.size());
    assertEquals("Toyota Camry 1995", result.get(0));
    assertEquals("Ford Mustang 1998", result.get(1));
  }
}
