import CustomerDataSet.customers

import scala.util.{Failure, Success, Try}
import java.io.FileReader

object CustomerDataSet {
  val customers = new Customers()

  val albert = Customer("Albert", Some(Address("1a Bridge St", None)))
  val beatriz = Customer("Beatriz", None)
  val carol = Customer("Carol", Some(Address("2a Finsbury Circus", Some("AL1 2PY"))))
  val sherlock = Customer("Sherlock", Some(Address("21b Baker Street", Some("NW1 6XE"))))

  customers.add(albert)
  customers.add(beatriz)
  customers.add(carol)
  customers.add(sherlock)
}

object Example extends App {
  val customersList = Set("Albert", "Beatriz", "Carol", "Dave", "Erin", "Sherlock")

  def generateShippingLabel_ForComprehension(): Set[String] = {
    customersList.flatMap { name => customers.find(name) }
    for {
      name     <- customersList
      customer <- customers.find(name) // implicitly coverts None to empty set
      address  <- customer.address
      postcode <- address.postcode
    } yield {
      shippingLabel(name, address.street, postcode)
    }
    // what if it couldn't do a label
  }

  //println("4. " + generateShippingLabel_ForComprehension())

  def shippingLabel(name: String, street: String, postcode: String) = {
    "\n\tShip to:\n\t" + "========\n\t" + name + "\n\t" + street + "\n\t" + postcode + "\n"
  }

  val tried:Try[Int] = Try(new FileReader("notes.md")).map( f => f.read())

  tried match {
    case Success(value) => println(s"Success : value")
    case Failure(exception) => exception.printStackTrace()
  }
}