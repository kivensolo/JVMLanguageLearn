/**
 * 高阶函数定义
 * 函数参数为函数的写法:
 *   op: (String) -> String
 *
 *   op --- 函数类型参数的形参
 *   (String) --- 此函数接受的参数类型
 *   -> String 此函数的返回参数类型
 *
 *   若是泛型：
 *   op: T.() -> Result<Data>
 *   op: suspend T.() -> Result<Data>
 */
fun higherOrder(value: String, op: (String) -> String): String {
    println("Executing the operation $op")
    return op(value) //run op block
}

fun lowerCase(value: String) = value.toLowerCase()

fun main() {
    /**
     * 函数参数的调用， 双冒号
     */
    println(higherOrder("HELLO", ::lowerCase))
}


