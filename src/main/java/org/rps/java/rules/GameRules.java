package org.rps.java.rules;

import org.rps.java.models.Weapon;

import java.util.*;
import java.util.stream.Collectors;

import static org.rps.java.models.Weapon.*;

/**
 * Created by filipemiranda on 7/10/16.
 */
public abstract class GameRules {

    private Set<WeaponRule> rules;

    protected abstract WeaponRule[] init();

    protected GameRules() {
        WeaponRule[] rules = init();
        if(rules == null || rules.length == 0) {
            throw new IllegalStateException("Impossible to create new Game Rules, no rules were defined for this game");
        }
        this.rules = Arrays.asList(rules).stream().collect(Collectors.toSet());
    }

    protected final WeaponRule.RuleBuilder rule(Weapon weapon) {
        return new WeaponRule.RuleBuilder(weapon);
    }

    protected WeaponRule[] createRules(WeaponRule... rules) {
        return rules;
    }

    public Set<WeaponRule> provideRules() {
        return rules;
    }

}
