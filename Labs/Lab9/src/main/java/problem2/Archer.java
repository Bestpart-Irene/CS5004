package problem2;

public class Archer extends GameCharacter {

  public Archer(String name, int health, int attack, int defense) {
    super(name, health, attack, defense);
  }

  @Override
  public GameCharacter clone() {
    return new Archer(this.name, this.health, this.attack, this.defense);
  }

  @Override
  public String getDescription() {
    return "Archer '" + name + "'";
  }
}
