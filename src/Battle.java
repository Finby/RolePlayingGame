import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Battle extends Thread {
    private Creature player1;
    private Creature player2;

    public Battle(Creature player1, Creature player2) {
        // shuffle players to randomise first plying
        List<Creature> players = Arrays.asList(player1, player2);
        Collections.shuffle(players);

        this.player1 = players.get(0);
        this.player2 = players.get(1);
    }

    @Override
    public void run() {
        // battle until both have HP
        int turn = 0;
        while (player1.getHp() > 0 && player2.getHp() > 0) {
//            System.out.println("--------- turn " + ++turn);
//            System.out.println(player1);
//            System.out.println(player2);
            player2.setHp(Math.max(0, player2.getHp() - player1.attackResult()));
            if (player2.getHp() <= 0 ) {
                break;
            }
            player1.setHp(Math.max(0, player1.getHp() - player2.attackResult()));
        }

    }
}
