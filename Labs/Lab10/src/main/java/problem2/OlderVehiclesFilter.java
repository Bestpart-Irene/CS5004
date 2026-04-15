package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OlderVehiclesFilter {

  private List<Vehicle> vehicles = new ArrayList<>();

  public OlderVehiclesFilter(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public OlderVehiclesFilter(Vehicle vehicle1, Vehicle vehicle2, Vehicle vehicle3) {
    this.vehicles.add(vehicle1);
    this.vehicles.add(vehicle2);
    this.vehicles.add(vehicle3);
  }

  /**
   * Filters and returns the make, model, and year of all vehicles manufactured before 1999.
   * Uses Java streams and lambda expressions to filter and map vehicles.
   *
   * @return a list of strings, each containing the make, model, and year of a vehicle
   *         manufactured before 1999
   */
  public List<String> filterOlderVehicles() {
    return vehicles.stream()
        .filter(vehicle -> vehicle.getYear() < 1999)
        .map(vehicle -> vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getYear())
        .collect(Collectors.toList());
  }
}
