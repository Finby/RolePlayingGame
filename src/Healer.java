import java.util.Scanner;

public class Healer extends Creature {
    public Healer() {
        super("Tom", 0, 0, 0, 0, Integer.MAX_VALUE, "healer");
    }

    public void interact() {
        boolean exit=false;
            int choice = this.makeChoice();
            switch (choice) {
                case 1:
                    this.trade();
                    break;
                case 2:
                    break;
            }
    }

    private void trade() {
    }

    private int makeChoice() {
        String menu = "1. Buy bottle of healing potion - 30 HP - 5 golds\n"+
                "2. Exit";
        System.out.println(menu);
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
                System.out.println("you have to pass integer: 1, 2 or 3");
            }
        }
    }
}
