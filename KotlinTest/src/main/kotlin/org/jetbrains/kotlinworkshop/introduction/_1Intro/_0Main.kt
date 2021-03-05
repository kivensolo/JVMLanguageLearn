package org.jetbrains.kotlinworkshop.introduction._1Intro

/**
 * KoltinWorkShop中的演讲Demo
 * https://github.com/Kotlin/workshop/tree/master/instructor/introduction/org/jetbrains/kotlinworkshop/introduction
 */

fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name!")

    // ${}
}
