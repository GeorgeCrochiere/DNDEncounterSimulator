import java.util.Random;

class dnd {
    static Random rand = new Random();
    public static void main(String[] args) {
        System.out.println("DND Encounter Simulator\n");
        rand.setSeed(System.currentTimeMillis());
        
        //Create Characters
        //Teams: 0 = players, 1 = enemy
        character player1 = new character("P1", 24, 5, 16, 7, 5, "Psychic Blades", 6, 0);
        character player2 = new character("P2", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 0);
        character player3 = new character("P3", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 0);
        character player4 = new character("P4", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 0);
        character player5 = new character("P5", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 0);
        character player6 = new character("P6", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 0);
        character player7 = new character("P7", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 0);

        character enemy1 = new character("E1", 105, 4, 19, 8, 5, "Sword", 12, 1);
        character enemy2 = new character("E2", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 1);
        character enemy3 = new character("E3", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 1);
        character enemy4 = new character("E4", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 1);
        character enemy5 = new character("E5", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 1);
        character enemy6 = new character("E6", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 1);
        character enemy7 = new character("E7", health, initiativeInitial, AC, AB, DB, weaponName, weaponMax, 1);
        
        //Get all values together
        character[] charactersInGame = {player1, player2, player3, player4, player5, player6, player7, enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7};
        
        //Get and sort initiatives
        int[] initiative;
        for (int i = 0; i < charactersInGame.length; i++) {
            initiative[i] = charactersInGame[i].init;
        }

        for (int i = 0; i < initiative.length-1; i++) {
            if (initiative[i] < initiative[i+1]) {
                int temp = initiative[i];
                initiative[i] = initiative[i+1];
                initiative[i+1] = temp;
            }
        }

        int teamA = 0;
        int teamB = 0;

        //Do simulation
        boolean load = true;
        while (load) {
            for (int i = 0; i < initiative.length; i++) {
                for (int j = 0; j < charactersInGame.length; j ++) {
                    if ((charactersInGame[j].init == initiative[i]) && (charactersInGame[j].hasAttacked == 0)) {
                        character attacker = charactersInGame[j];
                        int attackRoll = damageCalc(charactersInGame[j]);
                        charactersInGame[j].hasAttacked = 1;
    
                        int temp = 0;
                        boolean cont = true;
                        while (cont) {
                            temp = rand.nextInt(charactersInGame.length);
                            if ((charactersInGame[temp].HP > 0) && (charactersInGame[temp].charTeam != attacker.charTeam)) {
                                cont = false;
                            }
                        }
                        int checkAttack = 1+ rand.nextInt(19);
                        if (checkHit(checkAttack, charactersInGame[temp])) {
                            charactersInGame[temp].HP -= attackRoll;
                        }
                    }
                }
                teamA = 0;
                teamB = 0;
                for (int k = 0; k < charactersInGame.length; k++) {
                    charactersInGame[k].hasAttacked = 0;
                    if (charactersInGame[k].charTeam == 0) {
                        teamA += charactersInGame[k].HP;
                    } else {
                        teamB += charactersInGame[k].HP;
                    }
                }
    
                if ((teamA <= 0) || (teamB <= 0)) {
                    load = false;
                }
    
            }
        }

        //Display Resulting Health
        for (int n = 0; n < charactersInGame.length; n++) {
            character a = charactersInGame[n];
            System.out.println("Team: " + a.charTeam + ", Name: " + a.name + ", HP: " + a.HP);
        }

    }

    //Check Hit Function
    public static boolean checkHit(int attacker, character defender) {
        return(attacker >= defender.armorClass);
    }

    public static int damageCalc(character c) {
        int temp = 1 + c.damageBonus + rand.nextInt((c.weaponAttack)-1);
        return(temp);
    }

}