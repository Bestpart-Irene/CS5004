package problem2;

public class CharacterFactory {

  public static GameCharacter createCharacter(String template) {
    switch (template.toLowerCase()) {
      case "tank":
        return new Warrior("Tank", 200, 15, 30);
      case "dps":
        return new Archer("DPS", 100, 35, 10);
      case "support":
        return new Mage("Support", 120, 20, 20);
      default:
        throw new IllegalArgumentException("Unknown template: " + template);
    }
  }
}
