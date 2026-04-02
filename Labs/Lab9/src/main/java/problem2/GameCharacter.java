package problem2;

public abstract class GameCharacter implements Cloneable {
  protected String name;
  protected int health;
  protected int attack;
  protected int defense;

  public GameCharacter(String name, int health, int attack, int defense) {
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.defense = defense;
  }

  public String getName() { return name; }
  public int getHealth() { return health; }
  public int getAttack() { return attack; }
  public int getDefense() { return defense; }

  public void setName(String name) { this.name = name; }
  public void setHealth(int health) { this.health = health; }
  public void setAttack(int attack) { this.attack = attack; }
  public void setDefense(int defense) { this.defense = defense; }

  @Override
  public abstract GameCharacter clone();

  public abstract String getDescription();

  @Override
  public String toString() {
    return getDescription() + " [HP:" + health + " ATK:" + attack + " DEF:" + defense + "]";
  }
}
