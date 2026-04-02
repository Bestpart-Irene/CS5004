package problem2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

  // ===== Factory Pattern Tests =====

  @Test
  void testFactoryCreatesTank() {
    GameCharacter tank = CharacterFactory.createCharacter("tank");
    assertInstanceOf(Warrior.class, tank);
    assertEquals("Tank", tank.getName());
    assertEquals(200, tank.getHealth());
    assertEquals(15, tank.getAttack());
    assertEquals(30, tank.getDefense());
  }

  @Test
  void testFactoryCreatesDps() {
    GameCharacter dps = CharacterFactory.createCharacter("dps");
    assertInstanceOf(Archer.class, dps);
    assertEquals(100, dps.getHealth());
    assertEquals(35, dps.getAttack());
    assertEquals(10, dps.getDefense());
  }

  @Test
  void testFactoryCreatesSupport() {
    GameCharacter support = CharacterFactory.createCharacter("support");
    assertInstanceOf(Mage.class, support);
    assertEquals(120, support.getHealth());
    assertEquals(20, support.getAttack());
    assertEquals(20, support.getDefense());
  }

  @Test
  void testFactoryThrowsOnUnknownTemplate() {
    assertThrows(IllegalArgumentException.class, () -> CharacterFactory.createCharacter("healer"));
  }

  // ===== Builder Pattern Tests =====

  @Test
  void testBuilderDefaultValues() {
    GameCharacter character = new CharacterBuilder().build();
    assertEquals("Unknown", character.getName());
    assertEquals(100, character.getHealth());
    assertEquals(10, character.getAttack());
    assertEquals(10, character.getDefense());
    assertInstanceOf(Warrior.class, character);
  }

  @Test
  void testBuilderCustomWarrior() {
    GameCharacter warrior = new CharacterBuilder()
        .setName("Thorin")
        .setCharacterClass("warrior")
        .setHealth(180)
        .setAttack(25)
        .setDefense(20)
        .build();
    assertEquals("Thorin", warrior.getName());
    assertEquals(180, warrior.getHealth());
    assertEquals(25, warrior.getAttack());
    assertEquals(20, warrior.getDefense());
    assertInstanceOf(Warrior.class, warrior);
  }

  @Test
  void testBuilderMage() {
    GameCharacter mage = new CharacterBuilder()
        .setName("Gandalf")
        .setCharacterClass("mage")
        .build();
    assertInstanceOf(Mage.class, mage);
    assertEquals("Gandalf", mage.getName());
  }

  @Test
  void testBuilderArcher() {
    GameCharacter archer = new CharacterBuilder()
        .setName("Legolas")
        .setCharacterClass("archer")
        .build();
    assertInstanceOf(Archer.class, archer);
  }

  @Test
  void testBuilderThrowsOnUnknownClass() {
    CharacterBuilder builder = new CharacterBuilder().setCharacterClass("rogue");
    assertThrows(IllegalArgumentException.class, builder::build);
  }

  // ===== Prototype (Clone) Pattern Tests =====

  @Test
  void testCloneWarrior() {
    Warrior original = new Warrior("Tank", 200, 15, 30);
    GameCharacter clone = original.clone();

    assertNotSame(original, clone);
    assertEquals(original.getName(), clone.getName());
    assertEquals(original.getHealth(), clone.getHealth());
    assertEquals(original.getAttack(), clone.getAttack());
    assertEquals(original.getDefense(), clone.getDefense());
  }

  @Test
  void testCloneIsIndependent() {
    Warrior original = new Warrior("Tank", 200, 15, 30);
    GameCharacter clone = original.clone();
    clone.setName("Tank v2");
    clone.setAttack(50);

    assertEquals("Tank", original.getName());
    assertEquals(15, original.getAttack());
    assertEquals("Tank v2", clone.getName());
    assertEquals(50, clone.getAttack());
  }

  @Test
  void testCloneMage() {
    Mage mage = new Mage("Merlin", 100, 30, 10);
    GameCharacter clone = mage.clone();
    assertInstanceOf(Mage.class, clone);
    assertNotSame(mage, clone);
  }

  @Test
  void testCloneArcher() {
    Archer archer = new Archer("Robin", 110, 25, 15);
    GameCharacter clone = archer.clone();
    assertInstanceOf(Archer.class, clone);
    assertNotSame(archer, clone);
  }

  // ===== Decorator Pattern Tests =====

  @Test
  void testWeaponDecoratorAddsAttack() {
    GameCharacter warrior = new Warrior("Hero", 100, 20, 10);
    GameCharacter armed = new WeaponDecorator(warrior, "Sword", 15);

    assertEquals(35, armed.getAttack());
    assertEquals(10, armed.getDefense());
    assertTrue(armed.getDescription().contains("Weapon[Sword]"));
  }

  @Test
  void testArmorDecoratorAddsDefense() {
    GameCharacter warrior = new Warrior("Hero", 100, 20, 10);
    GameCharacter armored = new ArmorDecorator(warrior, "Shield", 12);

    assertEquals(20, armored.getAttack());
    assertEquals(22, armored.getDefense());
    assertTrue(armored.getDescription().contains("Armor[Shield]"));
  }

  @Test
  void testStackedDecorators() {
    GameCharacter warrior = new Warrior("Hero", 100, 20, 10);
    GameCharacter armed = new WeaponDecorator(warrior, "Excalibur", 15);
    GameCharacter fullyEquipped = new ArmorDecorator(armed, "Dragon Shield", 12);

    assertEquals(35, fullyEquipped.getAttack());
    assertEquals(22, fullyEquipped.getDefense());
    assertTrue(fullyEquipped.getDescription().contains("Weapon[Excalibur]"));
    assertTrue(fullyEquipped.getDescription().contains("Armor[Dragon Shield]"));
  }

  @Test
  void testDecoratorClone() {
    GameCharacter warrior = new Warrior("Hero", 100, 20, 10);
    GameCharacter armed = new WeaponDecorator(warrior, "Sword", 15);
    GameCharacter clone = armed.clone();

    assertNotSame(armed, clone);
    assertEquals(armed.getAttack(), clone.getAttack());
    assertEquals(armed.getDescription(), clone.getDescription());
  }

  // ===== toString / getDescription Tests =====

  @Test
  void testWarriorDescription() {
    Warrior w = new Warrior("Conan", 150, 25, 20);
    assertEquals("Warrior 'Conan'", w.getDescription());
    assertEquals("Warrior 'Conan' [HP:150 ATK:25 DEF:20]", w.toString());
  }

  @Test
  void testMageDescription() {
    Mage m = new Mage("Gandalf", 100, 30, 10);
    assertEquals("Mage 'Gandalf'", m.getDescription());
  }

  @Test
  void testArcherDescription() {
    Archer a = new Archer("Legolas", 110, 28, 12);
    assertEquals("Archer 'Legolas'", a.getDescription());
  }
}
