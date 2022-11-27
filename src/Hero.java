import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hero extends Creature {

    private ArrayList<Integer> levels100;
    private Integer level;
    private static final Integer levelStep = 5;
    private Integer hpMax; // level based HP max value to have limit for healing

    public Hero(String name, Integer hp, Integer strength, Integer agility, Integer experience, Integer gold) {
        super(name, hp, strength, agility, experience, gold, "hero");
        this.hpMax = this.getHp();
        level = 0;
        levels100 = Stream.iterate(0, n -> n + 1)
                .map(x -> Math.max(x * x * levelStep + (x - 1) * levelStep, 0))
                .limit(100)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(Arrays.deepToString(levels100.toArray()));
    }

    public Integer getLevel() {
        return this.level;
    }

    public void updateLevel() {
        // TODO: increase stats of hero by asking player
        if (this.getExperience() > levels100.get(this.level) && level <= 100) {
            level++;
        }
        // randomly add 10 stat point to 3 characteristics: HP, strength, agility
        int addHP = (new Random()).nextInt(5);
        int addstrength = (new Random()).nextInt(5);
        int addAgility = 10 - addHP - addstrength;

        this.hpMax += addHP;  // increase hpMax, current this.hp must be restored by healing
        this.setStrength(this.getStrength() + addstrength);
        this.setAgility(this.getAgility() + addAgility);

    }

    public void earnExp() {
        this.setExperience(this.getExperience() + levelStep);
        this.updateLevel();
    }

    public void earnGold() {
        Random rn = new Random();
        Integer gold = rn.nextInt(10);
        this.setGold(this.getGold() + gold);
    }

    public boolean heroHasMoney(int moneytoSpend) {
        if (this.getGold() < moneytoSpend) {
            System.out.println("You don't have enough money. Just " + this.getGold() + " in your pocket.");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + this.getName() + '\'' +
                ", hp=" + this.getHp() +
                ", strength=" + this.getStrength() +
                ", agility=" + this.getAgility() +
                ", experience=" + this.getExperience() +
                ", LEVEL=" + this.getLevel() +
                ", gold=" + this.getGold() +
                ", creatureType='" + this.getCreatureType() + '\'' +
                '}';
    }

    public void heal(int healHitPoints) {
        // TODO: need dependency on level
        this.setHp(Math.min(this.hpMax, this.getHp() + healHitPoints));
    }
}
