package client

import models._

import scala.util.Random

/**
  * Created by filipemiranda on 7/11/16.
  */
trait SimpleConsoleGame {
  val modeComputer = ":computer"
  val modePlayer = ":player"
  val actionPlay = ":play"
  val actionStop = ":exit"

  val actions = Set(actionPlay, actionStop)

  val modes = Set(modePlayer, modeComputer)

  val symbolsWeapons = Set("R", "S", "P")

  val weapons = Array(Rock, Scissors, Paper)

  def randomChoice: Weapon = {
    val s = 0
    val i = new Random().nextInt((0 to weapons.length -1).length)
    val n = if(i < 0) 0 else i
    weapons(n)
  }

}
