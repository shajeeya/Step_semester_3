package abstraction_and_interfaces.assignment_problems;

public class Problem4 {

    interface Attackable {
        String attack();

        String attack(String weaponName);
    }

    interface Defendable {
        String defend();
    }

    static abstract class GameCharacter {

        private static int characterCounter = 1000;
        private final String characterId;

        public GameCharacter() {
            characterId = "CHAR-" + (++characterCounter);
        }

        public final String getCharacterId() {
            return characterId;
        }

        public abstract String getSpecialMove();
    }

    static class Warrior extends GameCharacter
            implements Attackable, Defendable {

        private String name;

        public Warrior(String name) {
            this.name = name;
        }

        @Override
        public String attack() {
            return name + " strikes with a blade";
        }

        @Override
        public String attack(String weaponName) {
            return name + " strikes with an " + weaponName;
        }

        @Override
        public String defend() {
            return name + " raises a shield";
        }

        @Override
        public String getSpecialMove() {
            return name + " unleashes Whirlwind Slash";
        }
    }

    static class Trap implements Defendable {

        private String trapType;

        public Trap(String trapType) {
            this.trapType = trapType;
        }

        @Override
        public String defend() {
            return trapType + " triggers automatically";
        }
    }

    static void resolveDefense(Defendable[] combatants) {

        for (Defendable combatant : combatants) {
            System.out.println(combatant.defend());
        }
    }

    public static void main(String[] args) {

        Warrior w = new Warrior("Kael");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());
        System.out.println(w.getCharacterId());

        Trap t = new Trap("Spike Pit");

        System.out.println(t.defend());

        Defendable[] combatants = {w, t};

        resolveDefense(combatants);
    }
}