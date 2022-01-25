import java.io.File
import java.nio.CharBuffer
import java.util

import scala.collection.mutable

case class Address (street: String, postcode: Option[String]) {}

case class Customer(name: String, address: Option[Address]) extends Ordered[Customer] {
  val items = new util.ArrayList[Int]()

  def add(item: Int) {
    items add item
  }

  def total : Double = {
    var total = 0
    items.forEach(item => total += item)
    total
  }

  override def compare(that: Customer): Int = name.compare(that.name)

  override def toString: String = name + " $ " + total
}

class DiscountedCustomer (name: String, address: Option[Address]) extends Customer (name, address) {

  override def total : Double = {
    super.total * 0.90
  }
}

object DiscountedCustomer {
  def main(args: Array[String]): Unit = {
    val customers = new Customers()
    val toby = new DiscountedCustomer("Toby Weston", Some(Address("128",Some("Bullpen Street"))))
    toby.add(10)
    toby.add(20)
    customers.add(toby)

    val bob = new DiscountedCustomer("Bob Weston", Some(Address("128",Some("Bullpen Street"))))
    bob.add(10)
    customers.add(bob)
    println("Total :: " + customers.sort)
  }
}

//Interface in scala is trait
trait Readable{
  def read(buffer: CharBuffer) : Int
}

class FileReader(file : File) extends Readable with AutoCloseable{
  override def read(buffer: CharBuffer): Int = {
    val lineRead : Int = 0
    //.....
    lineRead
  }

  //??? not implemented yet. It will throw error if we try to call this method
  override def close(): Unit = ???
}

trait Sortable[A <: Ordered[A]] extends Iterable[A] {
  def sort : Seq[A] = {
    this.toList.sorted
  }
}

class Customers extends Sortable[Customer] {
  private val customers = mutable.Set[Customer]()

  def add(customer: Customer) = customers add customer //we can also use customers.add(customer)

  def iterator: Iterator[Customer] = customers.iterator

  def find(name: String) = customers.find(customer => customer.name == name)
}

//Concrete and Abstract fields on trait
trait Counter {
  protected var count = 0             //Concrete Field
  protected var incrementBy : Int     //Abstract field
  def increment()
}

class IncrementByOne extends Counter {
  override var incrementBy: Int = 1
  //override can be ignored
  override def increment(): Unit = count + incrementBy
}

//as count is protected it is not accessible in package as in java
// it is more restricted
class Foo {
  var foo = new IncrementByOne()
  //foo.count  //not allowed
}


//Abstract classes
abstract class AbstractPerson {
  def duty : String
}

class Manager extends AbstractPerson {
  override def duty: String =  {
    "Manage"
  }
}

//Polymorphism and Traits