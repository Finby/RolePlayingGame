import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    Hero hero;
    boolean heroAlive;
    Healer healer;
    Creature opponent;
    static String gameMenu = """
            1. Go to healer-trader
            2. Go to dark forest (battle)
            3. Exit game.
            """;
    public Game() {
        this.hero = createHero();
        this.heroAlive = true;
        this.healer = new Healer();

        // opponent will be initialized every time before battle
        this.opponent = null;

    }

    private Hero createHero() {
        // TODO: allow enter hero parameters with initial restriction hp+strength + agility = 150
        return new Hero("Vit", 40, 50, 60, 0, 0);
    }

    public static void main(String[] args) {

        Game myGame = new Game();
        myGame.begin();

    }

    private void battleRound() {
        System.out.println("!!! Your opponent: " + this.opponent);
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
            this.heroAlive = false;
        }

        System.out.println(resultMessage);
        System.out.println("Your state after battle: " + this.hero);

    }

    private void initOpponent() {
        // initial parameters sum = 100
        // +5% for every hero level
        float multiplier = 1 + 0.05f * (this.hero.getLevel() - 1);
        Skeleton skeleton = new Skeleton("Skel", Math.round(60 * multiplier), Math.round(30 * multiplier), Math.round(10 * multiplier), 0, 0);
        Goblin goblin = new Goblin("Gobby", Math.round(60 * multiplier), Math.round(10 * multiplier), Math.round(30 * multiplier), 0, 0);
        List<Creature> opps = Arrays.asList(skeleton, goblin);
        Collections.shuffle(opps);

        this.opponent = opps.get(0);
//        System.out.println("!!! OPPONENT: " + this.opponent);
    }

    private void begin() {
        boolean playing = true;
//        this.initOpponent();
        while (playing && this.heroAlive) {
            int input = this.makeChoice();
            switch (input ) {
                case 1:
                    this.healer.interact(this.hero);
                    break;
                case 2:
                    this.initOpponent();
                    this.battleRound();
                    break;
                case 3:
                    playing = false;
                    break;
            }

        }


//        for (int i = 0; i < 10; i++) {
//            System.out.println("Battle " + ++i );
//            this.battleRound();
//
//            hero.heal();
//            initOpponent();
//        }
    }

    private int makeChoice() {
        System.out.println(Game.gameMenu);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            try {
                int choice = Integer.parseInt(s);
                if (choice != 1 && choice != 2 && choice != 3) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("you have to pass integer: 1, 2 or 3");
            }
        }
    }


}
