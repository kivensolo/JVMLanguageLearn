package org.jetbrains.kotlinworkshop.introduction._8Delegation
import kotlin.reflect.KProperty

/**
 * 自定义代理
 * 把Service中的String类型的someProperty变量，搞一个ExternalFunctionality的代理，
 * 并添加getValue和setValue的operator方法, 那么参数的获取和设置都会走一遍代理
 *
 * 因为代理会将对应于该属性的get()（和set()）将委托给其getValue()和setValue()方法
 */
class Service {
    var someProperty: String by ExternalStringDelegator()
}

class ExternalStringDelegator {
    var backingField = "Default"
    operator fun getValue(service: Service, property: KProperty<*> ): String {
        println("getValue called with params: \n" +
                "service: $service\n" +
                "property: ${property.name}"
        )
        return backingField
    }
    operator fun setValue(service: Service, property: KProperty<*>, value: String) {
        backingField = value
    }
}

fun main(){
    print(Service().someProperty)
}
// OutPut:
//  getValue called with params:
//  service: org.jetbrains.kotlinworkshop.introduction._8Delegation.Service@6d5380c2
//  property: someProperty
//  Default


