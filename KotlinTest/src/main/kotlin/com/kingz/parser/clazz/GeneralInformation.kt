package com.kingz.parser.clazz

/**
 * 字节码常规信息
 */
object GeneralInformation {
    var minorVersion = 0
    var majorVersion = 0
        set(value) {
            field = value
            val desc = (value - 45).toFloat()
            majorVersionInJDK = 1.1f + (desc/10)
        }
    private var majorVersionInJDK = 0f
    var constantPoolCount = 0
        set(value){
            field = value - 1
        }
    var accessFlags = 0
    var thisClass = 0
    var superClass = 0
    var interfacesCount = 0
    var methodsCount = 0
    var attributesCount = 0

    override fun toString(): String {
        return "GeneralInformation[\n" +
                "\tminorVersion=$minorVersion\n" +
                "\tmajorVersion=$majorVersion[$majorVersionInJDK]\n" +
                "\tconstantPoolCount=$constantPoolCount\n" +
                "\taccessFlags=$accessFlags\n" +
                "\tthisClass=$thisClass\n" +
                "\tsuperClass=$superClass\n" +
                "\tinterfacesCount=$interfacesCount\n" +
                "\tmethodsCount=$methodsCount\n" +
                "\tattributesCount=$attributesCount\n]"
    }


}