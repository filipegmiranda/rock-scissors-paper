package org.rps.java.rules;

import org.rps.java.models.Weapon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by filipemiranda on 7/10/16.
 */
public class WeaponRule {

    private Weapon weapon;

    private Map<Weapon, Set<Weapon>> mapWins = new HashMap<>();

    WeaponRule(Weapon weapon, Set<Weapon> wins) {
        this.weapon = weapon;
        mapWins.put(weapon, wins);
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Set<Weapon> getDefeatedWepons(Weapon weapon) {
        return mapWins.get(weapon);
    }

    public static class RuleBuilder {

        private Weapon weapon;

        private Set<Weapon> wins = new HashSet<>();

        public RuleBuilder(Weapon weapon) {
            this.weapon = weapon;
        }

        public RuleBuilder wins(Weapon weapon) {
            wins.add(weapon);
            return this;
        }

        public RuleBuilder and(Weapon weapon) {
            wins.add(weapon);
            return this;
        }

        public WeaponRule build() {
            if(this.weapon == null || wins.isEmpty()){
                throw new IllegalRuleException("Impossible to define Either Weapon or weapons it defeats is not defined for this rule");
            }
            return new WeaponRule(this.weapon, this.wins);
        }
    }

    private static class IllegalRuleException extends RuntimeException {
        public IllegalRuleException(String msg){
            super(msg);
        }
    }

}
