class Civilian(val name: String)

case class SuperHero(heroName: String, alterEgo: String, power: List[String]) extends Civilian(heroName)

object PatternMatchingExample {
  def main(args: Array[String]): Unit = {
    //val hero = new SuperHero("Batman", "Bruce Wayne", List("Intellect", "Strong"))
    val hero = new Civilian("Nitesh")
    hero match {
      //constructor should match the primary constructor of SuperHero class
      case SuperHero(_, "Bruce Wayne", _) => println("Batman")
      case SuperHero(_, _, _) => println("Unknown Hero")
      case _ => println("I am a Civilian.")
    }

    def supperPowerFor(person: Civilian) = {
      person match {
        case SuperHero(_, _, power) => power
        case _ => List()
      }
    }

    val bruce = SuperHero("Batman", "Bruce Wayne", List("Intellect", "Strong"))
    val nitesh = new Civilian("Nitesh")

    println("Supper power for Bruce Wayne :: " + supperPowerFor(bruce))
    println("Supper power for Nitesh :: " + supperPowerFor(nitesh))

    def nameFor(person: Civilian) = {
      person match {
        case hero: SuperHero => hero.alterEgo
        case person: Civilian => person.name
      }
    }
    println("Supper power for Bruce Wayne :: " + nameFor(bruce))
    println("Supper power for Nitesh :: " + nameFor(nitesh))
  }
}

//using match without case class
class Business (val name: String, val address: String)

object Business  {
  def unapply(business: Business): Option[(String, String)] = Some((business.name, business.address))

  def main(args: Array[String]): Unit = {
    val business = new Business("Xoriant", "Banner, Pune")

    business match {
      case Business(name, address) => println(name + "  " + address)
    }
  }
}