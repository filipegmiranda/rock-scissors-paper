package org.rps.java.models;

import org.rps.java.rules.WeaponRule;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.rps.java.models.ActionResult.*;

/**
 * Created by filipemiranda on 7/9/16.
 */
public enum Weapon {

    ROCK,
    PAPER,
    SCISSORS;

    private Set<WeaponRule> rules;

    private void setRules(Set<WeaponRule> rules){
        this.rules = rules;
    }

    public ActionResult wins(Weapon weapon) {
        if(this == weapon) {
            return TIE;
        }
        Set<Weapon> defeated = rules
                .stream()
                .flatMap(r -> {
                    if (r.getWeapon() == this) {
                        return r.getDefeatedWepons(this).stream();
                    } else {
                        return Stream.empty();
                    }
                }).collect(Collectors.toSet());
        return defeated.contains(weapon) ? WINS : LOOSES;
    }

    public static void applyRules(Set<WeaponRule> rules) {
        for (Weapon weapon: Weapon.values()) {
            weapon.setRules(rules);
        }
    }

}
