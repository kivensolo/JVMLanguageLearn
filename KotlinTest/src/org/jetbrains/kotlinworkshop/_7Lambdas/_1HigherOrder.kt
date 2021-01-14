fun higherOrder(value: String, op: (String) -> String): String {
    println("Executing the operation $op")
    return op(value) //run op block
}

fun lowerCase(value: String) = value.toLowerCase()

fun main() {
    println(higherOrder("HELLO", ::lowerCase))
}
