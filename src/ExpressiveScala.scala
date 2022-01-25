//Faking function call
object ArrayExample {

  val numerals = Array("I", "II", "III", "IV", "V", "VI", "VII")

  def main(args: Array[String]): Unit = {
    for(i <- 0 to numerals.length - 1) {
      println(i + " = " + numerals(i))
      //same as apply
      println(i + " = " + numerals.apply(i))


      val a = 0 to 5
      val b = 0 to 5

      val x: Seq[Seq[(Int, Int)]] = a.map(i => b.map(j => (i,j)))

      println(x)

      val y: Seq[(Int, Int)] = a.flatMap(i => b.map(j => (i, j)))

      println(y)
    }

    //We can replace () with {} if we are passing exactly one argument
    numerals.foreach {
      println(_)
    }
  }
}

class Directory {
  private val numbers = scala.collection.mutable.Map(
    "Athos" -> "7782 234324",
    "Nitesh" -> "3432 342324"
  )

  def apply(name: String) = {
    numbers.get(name)
  }

  def update(name: String, number: String) = {
    numbers.update(name, number)
  }

  def update(areaCode: Int, newAreaCode: String) ={
    numbers.foreach( entry => {
      if(entry._2.startsWith(areaCode.toString)) {
        numbers(entry._1) = entry._2.replace(areaCode.toString, newAreaCode)
      }
    })
  }

  override def toString: String = numbers.toString()
}

object Directory {
  def main(args: Array[String]): Unit = {
    val yellowPages = new Directory()
    println(yellowPages)
    yellowPages("Nitesh") = "Not present"
    println("Nitesh number is :: " + yellowPages("Nitesh")) //we can also use yellowPages.apply("Nitesh")

    println(yellowPages)
    yellowPages(7782) = "3434"
    println(yellowPages)
  }
}

//By-named parameter, Higher order function and Currying
abstract class UI {

  var customer: DiscountedCustomer

  def updateCustomerBasket(): Unit = {

  }

  def updateOfferFor(customer: DiscountedCustomer): Unit = {

  }

  def updateUIElement() = {
    runInThread {
      () => updateCustomerBasket()
    }
    // we can write using () or {}
    runInThread(() => updateOfferFor(customer))

    //we can remove () => if we follow 2 function definition without ()
    runInThread {
      updateCustomerBasket()
    }

    runInThreadGroup("group-1") {
      updateCustomerBasket()
    }
  }

  //1> function with no argument
  /*def runInThread(function: () => Unit): Unit = {
    new Thread() {
      override def run(): Unit = function()
    }.start()
  }*/

  //2> more natural way
  def runInThread(function: => Unit): Unit = {
    new Thread() {
      override def run(): Unit = function
    }.start()
  }

  //curried function
  def runInThreadGroup(group: String)(function: => Unit) = {
    new Thread(new ThreadGroup(group), () => function).start();
  }
}