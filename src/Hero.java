import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hero extends Creature {

    private ArrayList<Integer> levels100;
    private Integer level;
    private static Integer levelStep = 5;
    private Integer INITIAL_HEALTH = 40;

    public Hero(String name, Integer hp, Integer strength, Integer agility, Integer experience, Integer gold) {
        super(name, hp, strength, agility, experience, gold, "hero");

        level = 0;
        levels100 = Stream.iterate(0, n -> n + 1)
                .map(x -> Math.max(x * x * levelStep + (x-1) * levelStep, 0))
                .limit(100)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(Arrays.deepToString(levels100.toArray()));
    }

    public Integer getLevel() {
        return this.level;
    }

    public void updateLevel() {
        // TODO: increase stats of hero
        if (this.getExperience() > levels100.get(this.level) && level <= 100) {
            level++;
        }
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

    public void heal() {
        // TODO: need dependency on level
        this.setHp(INITIAL_HEALTH);
    }
}
