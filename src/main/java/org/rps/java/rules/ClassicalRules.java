package org.rps.java.rules;

import static org.rps.java.models.Weapon.*;


/**
 * Created by filipemiranda on 7/10/16.
 */

public class ClassicalRules extends GameRules {

    @Override
    public WeaponRule[] init() {
        return createRules(
                rule(ROCK)
                        .wins(SCISSORS)
                        .build(),
                rule(SCISSORS)
                        .wins(PAPER)
                        .build(),
                rule(PAPER)
                        .wins(ROCK)
                        .build()
        );
    }

}