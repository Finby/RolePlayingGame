import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    Hero hero;
    Healer healer;
    Creature opponent;

    public Game() {
        this.hero = createHero();
        this.healer = new Healer();

        // opponent will be initialized every time before battle
        this.opponent = null;

    }

    private Hero createHero() {
        // TODO: allow enter hero parameters with initial restriction hp+strength + agility = 150
        Hero hero = new Hero("Vit", 40, 50, 60, 0, 0);
        return hero;
    }

    public static void main(String[] args) {

        Game myGame = new Game();
        myGame.begin();

    }

    private void battleRound() {
        Battle battle = new Battle(this.hero, this.opponent);
        battle.start();
        try {
            battle.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String resultMessage = "------------ game results: ";
        if (this.hero.getHp() > 0) {
            resultMessage += "hero " + this.hero.getName() + " WON";
            hero.earnExp();
            hero.earnGold();
        }
        else {
            resultMessage += this.opponent.getCreatureType() + " " + this.opponent.getName() + " WON";
        }


        System.out.println(resultMessage);
        System.out.println(this.hero);
        System.out.println(this.opponent);
    }

    private void initOpponent() {
        // initial parameters sum = 100
        // TODO: must depend on hero level
        //
        Skeleton skeleton = new Skeleton("Skel", 90, 50, 10, 0, 0);
        Goblin goblin = new Goblin("Gobby", 90, 30, 30, 0, 0);
        List<Creature> opps = Arrays.asList(skeleton, goblin);
        Collections.shuffle(opps);

        this.opponent = opps.get(0);
    }

    private void begin() {

        this.initOpponent();
        for (int i = 0; i < 10; i++) {
            System.out.println("Battle " + ++i );
            this.battleRound();

            hero.heal();
            initOpponent();
        }
    }



}
