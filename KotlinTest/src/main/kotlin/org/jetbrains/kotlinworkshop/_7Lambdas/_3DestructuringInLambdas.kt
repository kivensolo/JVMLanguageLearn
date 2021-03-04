package org.jetbrains.kotlinworkshop._7Lambdas

/**
 * 解构的lambda
 */
fun main() {
    val map = mapOf(1 to "one", 2 to "two")
    map.forEach { (key, value) ->
        println(value)
    }
    map.forEach { (_, value) ->
        println(value)
    }
    map.forEach { entry ->
        val (key, value) = entry
        println(value)
    }
}