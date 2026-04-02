package problem2;

public class Mage extends GameCharacter {

  public Mage(String name, int health, int attack, int defense) {
    super(name, health, attack, defense);
  }

  @Override
  public GameCharacter clone() {
    return new Mage(this.name, this.health, this.attack, this.defense);
  }

  @Override
  public String getDescription() {
    return "Mage '" + name + "'";
  }
}
