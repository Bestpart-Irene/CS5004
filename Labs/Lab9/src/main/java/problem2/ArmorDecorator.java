package problem2;

public class ArmorDecorator extends GameCharacter {
  private final GameCharacter wrappedCharacter;
  private final String armorName;
  private final int defenseBonus;

  public ArmorDecorator(GameCharacter character, String armorName, int defenseBonus) {
    super(character.getName(), character.getHealth(),
        character.getAttack(), character.getDefense() + defenseBonus);
    this.wrappedCharacter = character;
    this.armorName = armorName;
    this.defenseBonus = defenseBonus;
  }

  @Override
  public GameCharacter clone() {
    return new ArmorDecorator(wrappedCharacter.clone(), this.armorName, this.defenseBonus);
  }

  @Override
  public String getDescription() {
    return wrappedCharacter.getDescription() + " + Armor[" + armorName + "]";
  }
}
