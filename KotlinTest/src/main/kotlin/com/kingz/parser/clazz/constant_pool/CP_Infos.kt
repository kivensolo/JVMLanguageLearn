@file:Suppress("MemberVisibilityCanBePrivate")

package com.kingz.parser.clazz.constant_pool

/**
 * Constant Pool Data Entry Structure
 */
@Suppress("PropertyName", "ClassName")
class CP_Infos{

    abstract class C_Info{
        abstract fun size():Int
    }

    class CONSTANT_Utf8_Info: C_Info() {
        val tag = byteArrayOf(0x01)

        //UTF-8编码的字符串占用的字符数
        var length = ByteArray(2)

        //长度为length的UTF-8编码的字符串
        var bytes = ByteArray(1)
        override fun size(): Int {
            return tag.size + length.size + bytes.size
        }
    }

    class CONSTANT_Integer_Info : C_Info() {
        val tag = byteArrayOf(0x03)

        //Big-endian (high byte first) order.
        var bytes = ByteArray(4)
        override fun size(): Int {
            return tag.size + bytes.size
        }
    }

    class CONSTANT_Float_Info : C_Info() {
        val tag = byteArrayOf(0x04)

        //Big-endian (high byte first) order.
        var bytes = ByteArray(4)
        override fun size(): Int {
            return tag.size + bytes.size
        }
    }

    class CONSTANT_Long_Info : C_Info() {
        val tag = byteArrayOf(0x05)

        //Big-endian (high byte first) order.
        var bytes = ByteArray(8)
        override fun size(): Int {
            return tag.size + bytes.size
        }
    }
    class CONSTANT_Double_Info : C_Info() {
        val tag = byteArrayOf(0x06)

        //Big-endian (high byte first) order.
        var bytes = ByteArray(8)
        override fun size(): Int {
            return tag.size + bytes.size
        }
    }

    class CONSTANT_Class_Info : C_Info() {
        val tag = byteArrayOf(0x07)

        //Big-endian (high byte first) order.
        var index = ByteArray(2)
        override fun size(): Int {
            return tag.size + index.size
        }
    }

    class CONSTANT_String_Info : C_Info() {
        val tag = byteArrayOf(0x08)

        //Big-endian (high byte first) order.
        var index = ByteArray(2)
        override fun size(): Int {
            return tag.size + index.size
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Fields, methods, and interface methods">
    class CONSTANT_Fieldref_Info : C_Info() {
        val tag = byteArrayOf(0x09)

        //May be either a class type or an interface type.
        var class_index = ByteArray(2)
        //
        var name_and_type_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + class_index.size + name_and_type_index.size
        }
    }

    class CONSTANT_Methodref_Info : C_Info() {
        val tag = byteArrayOf(0x0A)
        //Must be a class type, not an interface type.
        var class_index = ByteArray(2)
        var name_and_type_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + class_index.size + name_and_type_index.size
        }
    }

    class CONSTANT_InterfaceMethodref_Info : C_Info() {
        val tag = byteArrayOf(0x0B)
        //Must be an interface type.
        var class_index = ByteArray(2)
        var name_and_type_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + class_index.size + name_and_type_index.size
        }
    }
    // </editor-fold>

    class CONSTANT_NameAndType_Info : C_Info() {
        val tag = byteArrayOf(13.toByte())
        //值必须是 constant_pool 表的有效索引。且索引处的条目必须是CONSTANT_Utf8_Info结构
        var name_index = ByteArray(2)
        //Taget must be a CONSTANT_Utf8_Info structure
        var descriptor_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + name_index.size + descriptor_index.size
        }
    }

    class CONSTANT_MethodHandle_Info : C_Info() {
        val tag = byteArrayOf(15.toByte())
        // must be in the range 1 to 9,
        // The value denotes the kind of this method handle, which characterizes its bytecode behavior (§5.4.3.5).
        var reference_kind = ByteArray(2)

        /*
         * 有点复杂，需查看4.4.8章节
         */
        var reference_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + reference_kind.size + reference_index.size
        }
    }
    class CONSTANT_MethodType_Info: C_Info() {
        val tag = byteArrayOf(16.toByte())
        //Taget must be a CONSTANT_Utf8_Info structure
        var descriptor_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + descriptor_index.size
        }
    }

    class CONSTANT_InvokeDynamic_Info: C_Info() {
        val tag = byteArrayOf(18.toByte())
        var bootstrap_method_attr_index = ByteArray(2)
        //Taget must be a CONSTANT_Utf8_Info structure
        var descriptor_index = ByteArray(2)
        override fun size(): Int {
            return tag.size + bootstrap_method_attr_index.size + descriptor_index.size
        }
    }
}
