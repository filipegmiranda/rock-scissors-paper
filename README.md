# rock-scissors-paper


## Bullet  Points

- There are 2 solutions into this project, one is in Java, and the other one in Scala, one is not just a translation of the other. Please consider the Scala one the one for evaluation, if you guys can only choose 1.

- It is an SBT project and its Main class published is client.GameClient(Scala), however you can go the the Java Main class also: MainGameController

- There are some commands for playing the game:
      - :computer -> for playing computer vs computer
      - :player -> for playing player vs computer
      - :play -> that is to be sent once you have chosen your Weapon
      - :exit -> it can be used to exit the game once it has started


The solution differs between the Java Solution and Scala solution specially because in the Java one, the behaviour of Attack of one Weapon is plugged into the Weapon itself, an enum in my choice, and gets executed by the Weapon object itself.

Specially in the Java one, the way to provide new Rules, and new Weapons to the game, is by implementing the class GameRules, this class also provides along with a Builder for the rule, a Small DSL(Fluent Interfaces) for defining how the game behaves

In scala, there is a similar concept, but in General I tried to use Referential Transparency as much as possible, and some Scala built-in Features, such as Try[T] and Either

Again, in the Scala version, the behaviour is defined in a similar way as in java, by implementing a trait GameRules, however, the behaviour of how the Weapons related to each other after attack is also in the trait, kind of like reminds me a Mediator Pattern

Next Phase would be refatoring the client, I see a potential for decreasing the amount of code needed
