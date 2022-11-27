import java.util.Scanner;

public class Healer extends Creature {
    private static final int potionCost = 5;
    private static final int potionHP = 30;
    private static final String healerMenu =
            "1. Buy bottle of healing potion - " + Healer.potionHP + "HP - " + Healer.potionCost + " golds\n"
                    + "2. Exit";
    public Healer() {
        super("Tom", 0, 0, 0, 0, Integer.MAX_VALUE, "healer");
    }

    public void interact(Hero hero) {
        boolean exit=false;
            int choice = this.makeChoice();
            switch (choice) {
                case 1:
                    this.trade(hero);
                    break;
                case 2:
                    break;
            }
    }

    private void trade(Hero hero) {
        // check if there is money and update hero HPs if enough
        Integer heroMoney = hero.getGold();
        if (hero.heroHasMoney(Healer.potionCost)) {
            hero.setGold(heroMoney - Healer.potionCost);
            hero.heal(Healer.potionHP);
            System.out.println(hero);
        }
    }

    private int makeChoice() {
        System.out.println(Healer.healerMenu);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            try {
                int choice = Integer.parseInt(s);
                if (choice != 1 && choice != 2) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("you have to pass integer: 1 or 2");
            }
        }
    }
}
