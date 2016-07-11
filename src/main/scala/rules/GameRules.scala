package rules

import models.ActionResult.ActionResult
import models._

import scala.util.{Failure, Success, Try}

/**
  * Game Rules - implement to apply th Set of Rules to a game session
  */
trait GameRules {
  type Rules = Map[Weapon, Set[Weapon]]

  def define: Rules

  def winner(p1: Player)(p2: Player): Try[Either[Player, ActionResult]] = {
    val weapon1 = p1.play
    val weapon2 = p2.play
    if (weapon1 == weapon2) return Success(Right(ActionResult.TIES))
    val defeated = define.get(weapon1).getOrElse(Set())
    if (defeated.isEmpty) {
      Failure(new RuntimeException(s"no rule defined for weapon $weapon1"))
    } else if (defeated.contains(weapon2)) {
      Success(Left(p1))
    } else {
      Success(Left(p2))
    }
  }

  final def wins(ws: Weapon*) =
    ws.toSet
}

/**
  * Classical Rules of the game
  */
class ClassicalRules extends GameRules {
  def define =
    Map(
      Rock -> wins(Scissors),
      Scissors -> wins(Paper),
      Paper -> wins(Rock)
    )
}
