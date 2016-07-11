package game

import models.ActionResult.ActionResult
import models.Player
import rules.GameRules

import scala.util.{Failure, Success, Try}

/**
  * Created by filipemiranda on 7/11/16.
  */
class GameStatelessSession private(p1: Player, p2: Player, gameRules: GameRules) {

  implicit class PimpPlayer(p: Player) {
    def rockPaperScissors: (Player) => Try[Either[Player, ActionResult]] = gameRules.winner(p)
  }

  def runRound: Try[Player] = {
    val result = p1 rockPaperScissors p2
    result match {
      case Success(Left(p)) => Success(p)
      case Success(Right(a)) =>  Failure(new RuntimeException("Round is not over - TIEs..."))
      case Failure(e) => Failure(e)
    }
  }

  def nextRound(n: Int): Try[Int] = {
    runRound flatMap { p =>
      p.increaseScore
      val result = n + 1
      Success(result)
    }
  }

  override def toString = s"Status $p1 vs $p2 - current state: p1 scores: ${p1.getScore}, p2 scores: ${p2.getScore} "

}

object GameStatelessSession{
  def apply(p1: Player, p2: Player, gameRules: GameRules): GameStatelessSession = new GameStatelessSession(p1, p2, gameRules)
}