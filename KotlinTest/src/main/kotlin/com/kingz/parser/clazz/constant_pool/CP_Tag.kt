package com.kingz.parser.clazz.constant_pool

/**
 * Constant pool tags
 */
@Suppress("EnumEntryName","ClassName")
enum class CP_Tag(internal val value: Int){
    CONSTANT_Utf8(1),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_Class(7),
    CONSTANT_Fieldref(9),
    CONSTANT_Methodref(10),
    CONSTANT_InterfaceMethodref(11),
    CONSTANT_NameAndType(12),
    CONSTANT_MethodHandle(15),
    CONSTANT_MethodType(16),
    CONSTANT_InvokeDynamic(18);

    companion object {
        private val valueTypeMap: MutableMap<Int, CP_Tag> = HashMap()

        fun fromValue(value: Int?): CP_Tag? {
            return valueTypeMap[value]
        }

        init {
            values().map {
                valueTypeMap.put(it.value, it)
            }
        }

        private val typeMap = mutableMapOf(
            Pair(1,"UTF-8"),
            Pair(3,"Integer"),
            Pair(4,"Float"),
            Pair(5,"Long"),
            Pair(6,"Double"),
            Pair(7,"Class"),
            Pair(9,"Fieldref"),
            Pair(10,"Methodref"),
            Pair(11,"InterfaceMethodref"),
            Pair(12,"NameAndType"),
            Pair(15,"MethodHandle"),
            Pair(16,"MethodType"),
            Pair(18,"InvokeDynamic")
        )

        @JvmStatic
        fun getTypeName(type:Int):String{
            return valueTypeMap[type]?.name?:"unKnow"
        }
    }


    override fun toString(): String {
        return name
    }
}