package problem2;

import java.util.ArrayList;
import java.util.List;

public class CharacterBuilder {
  private String name = "Unknown";
  private int health = 100;
  private int attack = 10;
  private int defense = 10;
  private String characterClass = "warrior";
  private final List<Equipment> equipmentList = new ArrayList<>();

  public CharacterBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public CharacterBuilder setHealth(int health) {
    this.health = health;
    return this;
  }

  public CharacterBuilder setAttack(int attack) {
    this.attack = attack;
    return this;
  }

  public CharacterBuilder setDefense(int defense) {
    this.defense = defense;
    return this;
  }

  public CharacterBuilder setCharacterClass(String characterClass) {
    this.characterClass = characterClass;
    return this;
  }

  public CharacterBuilder addEquipment(Equipment equipment) {
    this.equipmentList.add(equipment);
    return this;
  }

  public GameCharacter build() {
    GameCharacter character;
    switch (characterClass.toLowerCase()) {
      case "warrior":
        character = new Warrior(name, health, attack, defense);
        break;
      case "mage":
        character = new Mage(name, health, attack, defense);
        break;
      case "archer":
        character = new Archer(name, health, attack, defense);
        break;
      default:
        throw new IllegalArgumentException("Unknown character class: " + characterClass);
    }

    // Apply equipment bonuses via decorators
    for (Equipment eq : equipmentList) {
      if (eq.getAttackBonus() > 0) {
        character = new WeaponDecorator(character, eq.getDescription(), eq.getAttackBonus());
      }
      if (eq.getDefenseBonus() > 0) {
        character = new ArmorDecorator(character, eq.getDescription(), eq.getDefenseBonus());
      }
    }

    return character;
  }
}
