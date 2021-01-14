
import kotlin.reflect.KProperty


class Service {
    var someProperty: String by ExternalFunctionality()
}

class ExternalFunctionality {
    var backingField = "Default"
    operator fun getValue(
            service: Service, property: KProperty<*>
    ): String {
        println("getValue called with params: \n" +
                "service: $service\n" +
                "property: ${property.name}"
        )
        return backingField
    }
    operator fun setValue(
            service: Service, property: KProperty<*>, value: String
    ) {
        backingField = value
    }
}

fun main(){
    print(Service().someProperty)
}
// OutPut:
//  getValue called with params:
//  service: Service@6d5380c2
//  property: someProperty
//  Default


