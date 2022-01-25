import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.{MalformedURLException, URL}


/*object customer {
  def create(name: String, address: String, yearsACustomer : Int): Unit = {
    //If is an expression not a statement
    if (yearsACustomer > 2)
        new DiscountedCustomer(name, address)
    else
      new Customer(name, address)
  }
}*/


//matcher equivalent to switch and case in java
object Switch {
  def main(args: Array[String]): Unit = {
    val month = "February"
    var quarter : String = "???"

    month match {
      case "January" =>
      case "February" =>
      case "March" => quarter = "1st Quarter"
      case "April" =>
      case "May" =>
      case "June" => quarter = "2st Quarter"
      case _=> quarter = "unknown quarter"
    }
    println(month + " is the " + quarter) // this will not work we have to duplicate the string value

    month match {
      case "January" | "February" | "March" => quarter = "1st Quarter"
      case "April" | "May" | "June" => quarter = "2st Quarter"
      case _=> quarter = "unknown quarter"
    }
    println(month + " is the " + quarter)
  }
}

//Loops For comprehension
object ForComprehension {
  def main(args: Array[String]): Unit = {
    for(i <- 0 to 9) {
      print(i + " ")
    }
    println()
    //for each
    (0 to 9).foreach(i => print(i + " "))
    (0 to 9).foreach(println(_))
  }
}

//All the exception in Scala is RuntimeException
object ExceptionInScala {
  def main(args: Array[String]): Unit = {
    try {
      val url = new URL("") //it will throe exception
      val buffer = new BufferedReader(new InputStreamReader(url.openStream()))//it will throe exception
    } catch {
      //if we are not using exception object we can use _
      case _: MalformedURLException => println("Bad URL")
      case e: IOException => println("Problem with reading file :: ", e.getMessage)
    }
  }
}
