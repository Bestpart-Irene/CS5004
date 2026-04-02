package problem2;

public class WeaponDecorator extends GameCharacter {
  private final GameCharacter wrappedCharacter;
  private final String weaponName;
  private final int attackBonus;

  public WeaponDecorator(GameCharacter character, String weaponName, int attackBonus) {
    super(character.getName(), character.getHealth(),
        character.getAttack() + attackBonus, character.getDefense());
    this.wrappedCharacter = character;
    this.weaponName = weaponName;
    this.attackBonus = attackBonus;
  }

  @Override
  public GameCharacter clone() {
    return new WeaponDecorator(wrappedCharacter.clone(), this.weaponName, this.attackBonus);
  }

  @Override
  public String getDescription() {
    return wrappedCharacter.getDescription() + " + Weapon[" + weaponName + "]";
  }
}
