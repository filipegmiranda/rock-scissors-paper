package org.rps.java;

import org.rps.java.models.ActionResult;
import org.rps.java.models.Player;
import org.rps.java.models.Weapon;
import org.rps.java.rules.ClassicalRules;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by filipemiranda on 7/10/16.
 */
public class MainGameController {


    public static void main(String... args) {
        System.out.println("Rock-Paper-Scissors... press ENTER to get started, :exit to quit");

        GameSession session = null;

        Player player1 = null;

        Player player2 = null;

        Scanner sIn = new Scanner(System.in);

        String command = ":new";

        while (!command.equals(":exit")) {
            if (command.equals(":new")) {
                if (session != null) {
                    System.out.println(session);
                    System.out.println("winner of session is: " + session.winner());
                }
                System.out.println("new session starting...");
                System.out.println("choose the mode ->  :player (you vs computer), :computer (computer vs computer) \n");
                String mode = sIn.nextLine();
                while (!mode.equals(":player")) {
                    System.out.println("Invalid MODE -> " + mode + " \n");
                    System.out.println("Available modes: :player | :computer");
                    mode = sIn.nextLine();
                }
                if (mode.equals(":player")) {
                    System.out.println("Choose a name");
                    String playerName = sIn.nextLine();
                    player1 = Player.of(playerName);
                    player2 = Player.of("ComputerMaster");
                    session = GameSession.of(player1, player2, new ClassicalRules());
                    System.out.println(session);
                }
            }
            ActionResult roundResult = ActionResult.TIE;
            while (roundResult == ActionResult.TIE) {
                System.out.println(player1.getName() + " choose your Weapon! ");
                System.out.println("Weapons available { \n" + "  " + Arrays.toString(Weapon.values()) + "\n} \n type the weapon as it appears! ");
                System.out.println("============\n");
                Weapon weapon = null;
                while(weapon == null) {
                    weapon = Weapon.valueOf(sIn.nextLine().toUpperCase());
                    if (weapon == null){
                        System.out.println("Invalid Weapon! Choose from: [ "+Arrays.toString(Weapon.values())+" ]");
                    }
                }
                player1.chooseWeapon(weapon);
                Weapon weaponP2 = randomWeapon();
                player2.chooseWeapon(weaponP2);
                System.out.println("player1 with " + weapon + " - VS - player2 with " + weaponP2);
                roundResult = session.rockPaperScissor();
                if (session.rockPaperScissor() == ActionResult.TIE) {
                    System.out.println("Round is TIE, let's try again");
                }
            }
            System.out.println(session);
            System.out.println("Press ENTER to continue to next round, :new to start a new Session or :exit to quit");
            command = sIn.nextLine();
        }
    }

    private static Weapon randomWeapon() {
        final int MIN = 1;
        final int MAX = Weapon.values().length - 1;
        int r = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
        if (r < 0) r = r++;
        return Weapon.values()[r];
    }

}
