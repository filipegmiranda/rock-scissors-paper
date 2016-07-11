package org.rsp.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rps.java.models.Weapon;
import org.rps.java.rules.ClassicalRules;
import org.rps.java.rules.GameRules;
import org.rps.java.rules.WeaponRule;

import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.rps.java.models.ActionResult.*;
import static org.rps.java.models.Weapon.*;

/**
 *
 */
public class ClassicalRulesTest {

    @BeforeClass
    public static void setUp() {
        GameRules gameRules = new ClassicalRules();
        Set<WeaponRule> rules = gameRules.provideRules();
        Weapon.applyRules(rules);
    }

    @Test
    public void rockCrushesScissors(){
        assertTrue(ROCK.wins(SCISSORS) == WINS);
    }

    @Test
    public void scissorsIsCrushedRock() {
        assertTrue(SCISSORS.wins(ROCK) == LOOSES);
    }

    @Test
    public void rockTieRock() {
        assertTrue(ROCK.wins(ROCK) == TIE);
    }

    @Test
    public void testScissorsCutsPaper() {
        assertTrue(SCISSORS.wins(PAPER) == WINS);
    }

    @Test
    public void testScissorsTieScissors() {
        assertTrue(SCISSORS.wins(SCISSORS) == TIE);
    }

    @Test
    public void paperIsCutByScissors() {
        assertTrue(PAPER.wins(SCISSORS) == LOOSES);
    }

    @Test
    public void paperCoversRock() {
        assertTrue(PAPER.wins(ROCK) == WINS);
    }

    @Test
    public void paperTiePaper() {
        assertTrue(PAPER.wins(PAPER) == TIE);
    }

    @Test
    public void rockIsCoverByPaper() {
        assertTrue(ROCK.wins(PAPER) == LOOSES);
    }


}
