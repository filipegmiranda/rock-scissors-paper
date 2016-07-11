package tests

import models.ActionResult._
import models.{Paper, Player, Rock, Scissors}
import org.junit.Test
import rules.ClassicalRules
import org.junit.Assert.assertTrue
import scala.util.Try


/**
  * Created by filipemiranda on 7/11/16.
  */
class GameRulesSpecs {

  var gameRules = new ClassicalRules
  var pWithRock = Player("RockPlayer", Rock)
  var pWithPaper = Player("PaperPlayer", Paper)
  var pWithScissors = Player("ScissorsPlayer", Scissors)


  @Test
  def pWithRockShouldDefeatScissors: Unit ={
    val result = pWithRock rockPaperScissors pWithScissors
    assertTrue(toPlayer(result) == pWithRock)
  }

  @Test
  def pWithRockShouldTIERock: Unit = {
    val result = pWithRock rockPaperScissors pWithRock
    assertTrue(toActionResult(result) == TIES)
  }

  @Test
  def pWithScissorsShouldDefeatPaper: Unit = {
    val result = pWithScissors rockPaperScissors pWithPaper
    assertTrue(toPlayer(result) == pWithScissors)
  }

  @Test
  def pWithScissorsTIEScissors: Unit = {
    val result = pWithScissors rockPaperScissors pWithScissors
    assertTrue(toActionResult(result) == TIES)
  }

  @Test
  def pWithPaperShouldDefeatRock: Unit = {
    val result = pWithPaper rockPaperScissors pWithRock
    assertTrue(toPlayer(result) == pWithPaper)
  }

  @Test
  def pWithPaperShoudTIESPaper: Unit = {
    val result = pWithPaper rockPaperScissors pWithPaper
    assertTrue(toActionResult(result) == TIES)
  }

  private[this] def toPlayer(t: Try[Either[Player, ActionResult]]) = {
    t.get.left.get
  }

  private[this] def toActionResult(t: Try[Either[Player, ActionResult]]) = {
    t.get.right.get
  }

  implicit class PimpPlayer(p: Player) {
    def rockPaperScissors: (Player) => Try[Either[Player, ActionResult]] = gameRules.winner(p)
  }

}
