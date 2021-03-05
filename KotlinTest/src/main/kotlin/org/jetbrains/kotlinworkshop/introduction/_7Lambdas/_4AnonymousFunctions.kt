package org.jetbrains.kotlinworkshop.introduction._7Lambdas


fun op(x: Int, op: (Int) -> Int): Int {

    return op(x)
}

fun main() {

    op(3) { x -> x * x }

    /**
     * 匿名函数
     */
    op(2, fun(x): Int {
        return if (x > 10) {
            0
        } else {
            x * x
        }
    })

}