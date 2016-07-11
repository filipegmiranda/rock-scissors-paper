package client


import game.GameStatelessSession
import models.{Player, Weapon}
import rules.ClassicalRules

import scala.annotation.tailrec
import scala.io.StdIn._
import scala.util.{Failure, Random, Success}

/**
  * Created by filipemiranda on 7/11/16.
  */
object GameClient extends App with SimpleConsoleGame {

  println("Starting new Game! HAVE FUN  !!!")

  var nrRounds = 0

  implicit val mode = readMode

  val n = name

  val w = makeTheChoice(mode)

  val p1 = Player(n, w)

  val p2 = Player(randomName, randomChoice)

  val gameSession = GameStatelessSession(p1, p2, new ClassicalRules)

  if (mode == modePlayer) {
    play(readAction)
  } else {
    println("playing 10 seconds rounds COMPUTER vs COMPUTER")
    @tailrec
    def loop(n: Int): Unit = {
      if (n == 0) {
        play(actionStop)
        return
      }
      Thread.sleep(1000)
      play(actionPlay)
      loop(n - 1)
    }
    loop(10)
  }

  @tailrec
  def play(action: String): Unit = {
    if (action == actionStop) return
    gameSession.nextRound(nrRounds) match {
      case Success(n) => nrRounds = n
      case Failure(e) =>
        println("===================================================================")
        println(s"\ncould not finish the round, probably TIEs... check failure => $e")
        println(s"\nplayer1-weapon[  ${p1.play} ] | player2-weapon[ ${p2.play} ]\n")
        println("===================================================================")
        p1.choseWeapon(makeTheChoice)
        p2.choseWeapon(randomChoice)
        gameSession.nextRound(nrRounds)
    }
    println(s"\nCurrent game: \n $gameSession |    nr rounds $nrRounds \n")
    p1.choseWeapon(makeTheChoice)
    p2.choseWeapon(randomChoice)
    if (action == actionPlay && mode == modePlayer) play(readAction) else return
  }

  @tailrec
  def readMode: String = {
    val mode = readLine("choose your mode [  :player | :computer  ]\n")
    if (!mode.isEmpty && modes.contains(mode)) {
      mode
    } else {
      println(s"Invalid mode $mode\n")
      readMode
    }
  }


  @tailrec
  def name: String = {
    if (mode == modeComputer) randomChoice
    val n = readLine("Enter your name: \n")
    if (!n.isEmpty) n else name
  }

  def randomName: String = return "Computer-" + new Random().nextInt(100)

  @tailrec
  def choseWeapon: Weapon = {
    val choice = readLine("choose your weapon from the following Options: [ R - Rock, S - Scissors, P - Paper  ]: \n")
    if (symbolsWeapons.contains(choice)) Weapon.fromString(choice) else choseWeapon
  }

  @tailrec
  def readAction: String = {
    val action = readLine(":play for keep playing :exit for quiting: \n")
    if (actions.contains(action)) action else readAction
  }

  def makeTheChoice(implicit mode: String): Weapon = {
    if (mode == modePlayer) choseWeapon else randomChoice
  }


}
