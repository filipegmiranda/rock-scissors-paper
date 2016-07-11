package org.rps.java;

import org.rps.java.models.ActionResult;
import org.rps.java.models.Player;
import org.rps.java.models.Weapon;
import org.rps.java.rules.GameRules;

import java.util.Optional;

import static org.rps.java.models.ActionResult.*;

/**
 * Created by filipemiranda on 7/10/16.
 */
public class GameSession {

    private Player player1;
    private Player player2;

    private int numberOfRounds;

    private GameSession(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public static GameSession of(Player player1, Player player2, GameRules rules) {
        if(player1 == null || player2 == null){
            throw new IllegalArgumentException("Player 1 and Player 2 should be defined for this Session");
        }
        if(rules == null) {
            throw new  IllegalArgumentException("Impossible to Initiate Game without rules defined");
        }
        Weapon.applyRules(rules.provideRules());
        return new GameSession(player1, player2);
    }

    public ActionResult rockPaperScissor() {
        ActionResult result = player1.play().wins(player2.play());
        if (result == WINS) {
            player1.increaseScore();
        } else if (result == LOOSES) {
            player2.increaseScore();
        }
        if(result !=  TIE) numberOfRounds++;
        return result;
    }

    public Optional<Player> winner() {
        if (player1.getScore() == player2.getScore()) {
            return Optional.of(null);
        }
        return Optional.of(player1.getScore() > player2.getScore() ? player1 : player2);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("GameSession in Rock-Paper-Scissors    -   Number of Rounds: \n" + numberOfRounds)
                .append("-\n")
                .append("player1:  " + player1.getName() + " | score: " + player1.getScore() + " \n")
                .append("player2:  " + player2.getName() + " | score: " + player2.getScore() + " \n")
                .append("-----------------\n")
                .toString();
    }
}
