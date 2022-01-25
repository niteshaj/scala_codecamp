class CustomerTest(val name: String, val address: String, hidden: String) {
  // If we don't use val or var (hidden in constructor) it will not crete any method

  private var _id = ""

  //if var is used, public getter and setters are created
  var displayName = ""

  //if use val it will public getter but it will not create public setter
  val UUID = "wewrwerwe"

  def id = _id

  def id_=(id: String) {
    if (_id.isEmpty())
      _id = id
  }

}

// we can make the constructor private by adding private after class name
// We can make private constructor if we want force user to use factory method
class Person /*private*/ (forename: String, initials: String = "", surname: String) {
  val fullName = if (initials != null && !initials.isEmpty())
    forename + " " + initials + ". " + surname
  else
    forename + " " + surname

  private val _id = Person.nextId()

  def id = _id
}

//companion class as both have same name person
//we can define static method in this singleton class
object Person {
  //this will create a factory method for Person class so we can call Person("Nitesh", "A", "Jawanjal")
  def apply(forename: String, initials: String, surname: String): Person = new Person(forename, initials, surname)
  private var sequenceOfIds = 0

  private def nextId() = {
    sequenceOfIds += 1
    sequenceOfIds
  }

}

//When we use object scala will create single object(singleton pattern)
object HelloFirst {
  def main(args: Array[String]): Unit = {
    val nitesh = new CustomerTest("Nitesh", "Pune", "")
    //nitesh.id_= ("23231")
    //This is private field it will generate private getters and setters.
    //As scala provide default getter and setters we can not override.
    //To over come this grated private variable with different name (as _id)
    //this will create private method with _id name and we can write our own public getter and setters
    nitesh.id = "321313"

    println(nitesh)
    println(nitesh.UUID)

    val person = new Person("Nitesh", "A", "Jawanjal")
    println("id: " + person.id + " Full Name: " + person.fullName)
    val person2 = new Person("Nitesh", surname = "Jawanjal")
    println("id: " + person2.id + " Full Name: " + person2.fullName)

    val person3 = Person("Nitesh", "A", "Jawanjal")
    println("id: " + person3.id + " Full Name: " + person3.fullName)
  }

  //println(new HelloFirst().square(10))

  //val list : new List( 1, 2, 3)
  //(1 to 100).foreach(println(_))
}