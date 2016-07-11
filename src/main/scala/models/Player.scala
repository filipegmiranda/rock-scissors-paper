package models

/**
  * Created by filipemiranda on 7/11/16.
  */
class Player private(name: String,
                     private var score: Int,
                     private var chosen: Boolean,
                     private var weapon: Weapon
                    ) {
  def hasChosen = chosen

  def getScore = score

  def increaseScore: Int = { score = score + 1; score}

  def choseWeapon(w: Weapon) = weapon = w

  def play = weapon

  override def toString = s"Player $name - with weapon $weapon"

}

object Player {
  def apply(name: String, weapon: Weapon): Player = {
    if (weapon == null || name == null || name.isEmpty) throw new IllegalArgumentException("weapon must be defined for Player and name must be defined")
    new Player(name, 0, false, weapon)
  }
}