package org.rps.java.models;

/**
 * Created by filipemiranda on 7/10/16.
 */
public class Player {

    private String name;

    private int score = 0;

    private Weapon weapon;

    private boolean chosen;

    private Player(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("player needs a valid name");
        }
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public void chooseWeapon(Weapon weapon) {
        if (weapon == null) {
            throw new IllegalArgumentException("Weapon cannot be null");
        }
        this.weapon = weapon;
        chosen = true;
    }

    public Weapon play() {
        if (!hasChosen()) {
            throw new IllegalStateException("Player has no Weapon to play");
        }
        return this.weapon;
    }

    public void increaseScore() {
        this.score++;
    }

    public boolean hasChosen() {
        return chosen;
    }

    public void restart() {
        this.score = 0;
        this.chosen = false;
        this.weapon = null;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "player - " + getName() + " score: " + this.score;
    }
}
