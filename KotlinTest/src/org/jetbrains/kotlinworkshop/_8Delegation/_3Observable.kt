import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Person(val name: String, age: Int, salary: Int) {
    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        println("Property value $oldValue has changed to $newValue")
    }
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

fun main() {
    val person = Person("Alice", 25, 2000)
    person.age++
    person.salary += 100
}
// OutPut:
//Property value 25 has changed to 26
//Property value 2000 has changed to 2100


