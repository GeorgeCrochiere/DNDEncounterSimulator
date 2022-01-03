import java.util.Random;

public class character {
    String name;
    int HP;
    int armorClass;
    int attackBonus;
    int damageBonus;
    int init;
    int charTeam;

    String weapon;
    int weaponAttack;
    int hasAttacked;

    Random rand = new Random();

    public character(String charName, int health, int initiativeInitial, int AC, int AB, int DB, String weaponName, int weaponMax, int team) {
        init = initiativeInitial + 1 + rand.nextInt(19);
        name = charName;
        HP = health;
        armorClass = AC;
        attackBonus = AB;
        damageBonus = DB;
        weapon = weaponName;
        weaponAttack = weaponMax;
        charTeam = team; 
        hasAttacked = 0;
    }
}
