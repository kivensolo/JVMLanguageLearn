package org.jetbrains.kotlinworkshop.introduction._8Delegation
import com.kingz.kt.collcetions.Customer

/**
 * 代理属性官方文档:
 *  https://kotlinlang.org/docs/delegated-properties.html
 */
interface Repository {
    fun getById(id: Int): Customer
    fun getAll(): List<Customer>
}

interface Logger {
    fun logAll()
}

/**
 * 这是什么玩法 ？？？？
 */
class Controller(repository: Repository, logger: Logger): Repository by repository, Logger by logger {
}