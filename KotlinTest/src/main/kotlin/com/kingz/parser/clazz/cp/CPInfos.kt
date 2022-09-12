@file:Suppress("MemberVisibilityCanBePrivate")

package com.kingz.parser.clazz.cp

import com.google.common.base.Preconditions
import com.google.common.collect.ImmutableMap
import com.kingz.kt.utils.HexUtil
import java.nio.ByteBuffer
import java.nio.charset.Charset


/**
 * Constant Pool Data Entry Structure
 * 常量遵循以下格式：
 * cp_info {
 *    u1 tag;
 *    uX info[];
 * }
 * 必须以1个字节开始，表明其实cp_info是哪种类型(字节码一共有14种类型的cp_info)
 * info数组的内容随 tag 的值而变化。
 * 每个标记字节后面必须跟2个或更多字节，提供有关特定常数的信息。
 * 附加信息的格式随标记值的不同而不同。
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4
 */
@Suppress("PropertyName", "ClassName")
class CPInfos{

    enum class CP_Tag(internal val code: Int){
        Utf8(0x0001),
        Integer(0x0003),
        Float(0x0004),
        Long(0x0005),
        Double(0x0006),
        Class(0x0007),
        String(0x0008),
        FieldRef(0x0009),
        MethodRef(0x000A),
        INTERFACE_METHOD_REF(0x000B),
        NameAndType(0x000C),
        METHOD_HANDLE(0x000F),
        METHOD_TYPE(0x0010),
        INVOKE_DYNAMIC(0x0012);

        companion object {
            private val FROM_CODE: Map<Int, CP_Tag>

            fun fromValue(value: Int): CP_Tag? {
                return FROM_CODE[value]
            }

            init {
                val builder = ImmutableMap.builder<Int, CP_Tag>()
                values().map { tag ->
                    builder.put(tag.code, tag)
                }
                FROM_CODE = builder.build()
            }

            private val typeMap = mutableMapOf(
                Pair(0x0001,"UTF-8"),
                Pair(0x0003,"Integer"),
                Pair(0x0004,"Float"),
                Pair(0x0005,"Long"),
                Pair(0x0006,"Double"),
                Pair(0x0007,"Class"),
                Pair(0x0009,"Fieldref"),
                Pair(0x000A,"Methodref"),
                Pair(0x000B,"InterfaceMethodref"),
                Pair(0x000C,"NameAndType"),
                Pair(0x000F,"MethodHandle"),
                Pair(0x0010,"MethodType"),
                Pair(0x0012,"InvokeDynamic")
            )

            @JvmStatic
            fun getTypeName(type:Int): kotlin.String {
                return FROM_CODE[type]?.name ?: "unKnow"
            }

            @JvmStatic
            fun getTypeFromCode(code:Int):CP_Tag?{
                return Preconditions.checkNotNull(FROM_CODE[code], "Unknown chunk type: %s", code)
            }
        }


        override fun toString(): kotlin.String {
            return name
        }
    }

    companion object{
        /**
         * Create CpInfo Object by tag.
         */
        fun createCpInfo(tag:Byte):CPInfo{
            val info: CPInfo
            when (tag.toInt()) {
                0x01 -> info = CONSTANT_Utf8_Info()
                0x03 -> info = CONSTANT_Integer_Info()
                0x04 -> info = CONSTANT_Float_Info()
                0x05 -> info = CONSTANT_Long_Info()
                0x06 -> info = CONSTANT_Double_Info()
                0x07 -> info = CONSTANT_Class_Info()
                0x08 -> info = CONSTANT_String_Info()
                0x09 -> info = CONSTANT_Fieldref_Info()
                0x0A -> info = CONSTANT_Methodref_Info()
                0x0B -> info = CONSTANT_InterfaceMethodref_Info()
                0x0C -> info = CONSTANT_NameAndType_Info()
                0x0F -> info = CONSTANT_MethodHandle_Info()
                0x10 -> info = CONSTANT_MethodType_Info()
                0x12 -> info = CONSTANT_InvokeDynamic_Info()
                else -> {
                    throw Exception("没有找到该TAG[$tag.toInt()]对应的常量类型")
                }
            }
            return info
        }
    }


    abstract class CPInfo(val tag:Byte){
        /**  total size of current cp_info  in byte**/
        abstract fun size():Int

        /** read data from buffer. **/
        @Throws(Exception::class)
        abstract fun read(codeBuf: ByteBuffer)

        override fun toString(): String {
            return CP_Tag.getTypeName(tag.toInt()) + "\t\t\t"
        }
    }

    class CONSTANT_Utf8_Info: CPInfo(CP_Tag.Utf8.code.toByte()) {
        //The value of the length item gives the number of bytes in the bytes array
        //(not the length of the resulting string).
        var length = ByteArray(2)

        //The bytes array contains the bytes of the string.
        var bytes = ByteArray(0)

        override fun size(): Int {
            return 1 + 2 + bytes.size
        }

        override fun read(codeBuf: ByteBuffer) {
            length = byteArrayOf(codeBuf.get(), codeBuf.get())
            val lengthInt = HexUtil.readInt(length)
            bytes = ByteArray(lengthInt)
            codeBuf.get(bytes, 0, lengthInt)
        }

        override fun toString(): String {
            return super.toString() + getValue()
        }

        fun getValue():String{
            return  bytes.toString(Charset.forName("UTF-8"))
                //转移反斜杠
                .replace("\n","\\n")
        }
    }

    class CONSTANT_Integer_Info : CPInfo(CP_Tag.Integer.code.toByte()) {
        //Big-endian (high byte first) order.
        var bytes = ByteArray(4)
        override fun size() = 5 //1 + 4

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(bytes, 0, bytes.size)
        }

        override fun toString(): String {
            return "CONSTANT_Integer_info"
        }
    }

    class CONSTANT_Float_Info : CPInfo(CP_Tag.Float.code.toByte()) {
        //Big-endian (high byte first) order.
        var bytes = ByteArray(4)
        override fun size() = 5 //1 + 4

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(bytes, 0, bytes.size)
        }

        override fun toString(): String {
            return super.toString()
        }
    }

    open class CONSTANT_Long_Info(_tag: Byte = CP_Tag.Long.code.toByte()):CPInfo(_tag) {
        //Big-endian (high byte first) order.
        var high_bytes = ByteArray(4)
        var low_bytes = ByteArray(4)
        override fun size() = 9

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(high_bytes, 0, high_bytes.size)
            codeBuf.get(low_bytes, 0, low_bytes.size)
        }

        override fun toString(): String {
            return super.toString()
        }
    }

    class CONSTANT_Double_Info : CONSTANT_Long_Info(CP_Tag.Double.code.toByte()) {
        override fun toString(): String {
            return super.toString()
        }
    }

    /**
     * The structure is used to represent a class or an interface.
     */
    class CONSTANT_Class_Info : CPInfo(CP_Tag.Class.code.toByte()) {

        /**
         * The value of the name_index item must be
         * a valid index into the constant_pool table.
         *
         * Big-endian (high byte first) order.
         */
        var name_index = ByteArray(2)
        override fun size() = 3 // 1 + 2

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(name_index, 0, name_index.size)
        }

        override fun toString(): String {
            return super.toString() + "#${HexUtil.readInt(name_index)}"
        }

        fun getIndexValue():Int {
            return HexUtil.readInt(name_index)
        }
    }

    class CONSTANT_String_Info : CPInfo(CP_Tag.String.code.toByte()) {
        //Big-endian (high byte first) order.
        var string_index = ByteArray(2)
        override fun size() = 3
        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(string_index, 0, string_index.size)
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Refs">
    open class CONSTANT_Fieldref_Info(_tag:Byte = CP_Tag.FieldRef.code.toByte())
        : CPInfo(_tag) {
        //May be either a class type or an interface type.
        var class_index = ByteArray(2)
        var name_and_type_index = ByteArray(2)
        override fun size(): Int = 5

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(class_index, 0, class_index.size)
            codeBuf.get(name_and_type_index, 0, name_and_type_index.size)
        }

        override fun toString(): String {
            return super.toString() + "#${HexUtil.readInt(class_index)}" +
                    ".#${HexUtil.readInt(name_and_type_index)}"
        }
    }

    class CONSTANT_Methodref_Info : CONSTANT_Fieldref_Info(CP_Tag.MethodRef.code.toByte()) {
        //class_index must be a class type, not an interface type.
        override fun toString(): String {
            return super.toString()
        }
    }

    class CONSTANT_InterfaceMethodref_Info : CONSTANT_Fieldref_Info(CP_Tag.INTERFACE_METHOD_REF.code.toByte()) {
        // class_index must be an interface type.
        override fun toString(): String {
            return "CONSTANT_InterfaceMethodref_Info"
        }
    }
    // </editor-fold>

    class CONSTANT_NameAndType_Info : CPInfo(CP_Tag.NameAndType.code.toByte()) {
        //值必须是 constant_pool 表的有效索引。且索引处的条目必须是CONSTANT_Utf8_Info结构
        var name_index = ByteArray(2)
        //Taget must be a CONSTANT_Utf8_Info structure
        var descriptor_index = ByteArray(2)
        override fun size() = 5
        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(name_index, 0, name_index.size)
            codeBuf.get(descriptor_index, 0, descriptor_index.size)
        }

        override fun toString(): String {
            return super.toString() + "#${HexUtil.readInt(name_index)}" +
                    ":#${HexUtil.readInt(descriptor_index)}"
        }
    }

    class CONSTANT_MethodHandle_Info : CPInfo(CP_Tag.METHOD_HANDLE.code.toByte()) {
        // must be in the range 1 to 9,
        // The value denotes the kind of this method handle, which characterizes its bytecode behavior (§5.4.3.5).
        var reference_kind = ByteArray(2)

        /*
         * 有点复杂，需查看4.4.8章节
         */
        var reference_index = ByteArray(2)
        override fun size() = 5

        override fun read(codeBuf: ByteBuffer) {
            TODO("Not yet implemented")
        }

        override fun toString(): String {
            return "CONSTANT_MethodHandle_Info"
        }
    }

    /**
     * 表示方法类型，与CONSTANT_MethodHandle_info结构一样，
     * 也是虚拟机为实现动态调用invokedynamic指令所增加的常量结构
     */
    class CONSTANT_MethodType_Info: CPInfo(CP_Tag.METHOD_TYPE.code.toByte()) {
        /**
         * Taget must be a CONSTANT_Utf8_Info structure
         */
        var descriptor_index = ByteArray(2)
        override fun size() = 3

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(descriptor_index, 0, descriptor_index.size)
        }
    }

    /**
     * 表示invokedynamic指令用到的引导方法bootstrap method
     * 以及引导方法所用到的动态调用名称、参数、返回类型。
     */
    class CONSTANT_InvokeDynamic_Info: CPInfo(CP_Tag.INVOKE_DYNAMIC.code.toByte()) {
        //指向class文件结构属性表中引导方法表的某个引导方法
        var bootstrap_method_attr_index = ByteArray(2)
        //指向常量池中某个CONSTANT_NameAndType_Info结构的常量
        var name_and_type_index = ByteArray(2)
        override fun size() = 5

        override fun read(codeBuf: ByteBuffer) {
            codeBuf.get(bootstrap_method_attr_index, 0, bootstrap_method_attr_index.size)
            codeBuf.get(name_and_type_index, 0, name_and_type_index.size)
        }
    }
}
