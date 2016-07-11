package models

import rules.GameRules

/**
  * Created by filipemiranda on 7/11/16.
  */
sealed trait Weapon {
}

final case object Rock extends Weapon
final case object Paper extends Weapon
final case object Scissors extends Weapon

object Weapon {
  def fromString(s: String): Weapon = s match {
    case "R" => Rock
    case "S" => Scissors
    case "P" => Paper
  }
}