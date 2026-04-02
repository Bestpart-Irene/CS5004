package problem2;

public class CharacterManager {

  public static void main(String[] args) {
    // 1. Creating characters through factory methods
    System.out.println("=== Creating Characters via Factory ===");
    GameCharacter tank = CharacterFactory.createCharacter("tank");
    GameCharacter dps = CharacterFactory.createCharacter("dps");
    GameCharacter support = CharacterFactory.createCharacter("support");
    System.out.println(tank);
    System.out.println(dps);
    System.out.println(support);

    // 2. Using the Builder pattern
    System.out.println("\n=== Creating Character via Builder ===");
    GameCharacter customWarrior = new CharacterBuilder()
        .setName("Thorin")
        .setCharacterClass("warrior")
        .setHealth(180)
        .setAttack(25)
        .setDefense(20)
        .build();
    System.out.println(customWarrior);

    // 3. Cloning characters (Prototype pattern) and modifying
    System.out.println("\n=== Cloning Characters (Prototype) ===");
    GameCharacter tankClone = tank.clone();
    tankClone.setName("Tank v2");
    tankClone.setAttack(tank.getAttack() + 5);
    System.out.println("Original: " + tank);
    System.out.println("Clone:    " + tankClone);

    // 4. Decorating characters with equipment
    System.out.println("\n=== Decorating Characters with Equipment ===");
    GameCharacter armedWarrior = new WeaponDecorator(customWarrior, "Excalibur", 15);
    System.out.println("After weapon: " + armedWarrior);

    GameCharacter fullyEquipped = new ArmorDecorator(armedWarrior, "Dragon Shield", 12);
    System.out.println("After armor:  " + fullyEquipped);

    // 5. Displaying all character stats
    System.out.println("\n=== Final Character Summary ===");
    System.out.println(tank);
    System.out.println(dps);
    System.out.println(support);
    System.out.println(customWarrior);
    System.out.println(fullyEquipped);
  }
}
