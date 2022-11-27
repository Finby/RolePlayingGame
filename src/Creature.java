import java.util.Random;

public abstract class Creature {

    private static final Integer critHitChance = 5; // every 5th, 20%

    private String name;
    private Integer hp;
    private Integer strength;
    private Integer agility;
    private Integer experience;
    private Integer gold;
    private final String creatureType;

    public Creature(String name, Integer hp, Integer strength, Integer agility, Integer experience, Integer gold, String creatureType) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.agility = agility;
        this.experience = experience;
        this.gold = gold;
        this.creatureType = creatureType;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", strength=" + strength +
                ", agility=" + agility +
                ", experience=" + experience +
                ", gold=" + gold +
                ", creatureType='" + creatureType + '\'' +
                '}';
    }

    public int attackResult() {
        if ((new Random()).nextInt(101) < this.agility * 3) {
            Integer critChance = (new Random()).nextInt(1000);
            return (critChance % Creature.critHitChance == 0) ? this.strength * 2 : this.strength;
        }

        return 0;
    }

    public String getCreatureType() {
        return creatureType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }


    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }
}
