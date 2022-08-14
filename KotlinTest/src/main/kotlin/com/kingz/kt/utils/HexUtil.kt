package com.kingz.kt.utils

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*

/**
 * 十六进制&二进制&十进制处理工具
 */
@Suppress("MemberVisibilityCanBePrivate")
object HexUtil {

    private val DIGITS_LOWER = charArrayOf(
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    )

    private val DIGITS_UPPER = charArrayOf(
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    )


// <editor-fold defaultstate="collapsed" desc="Byte扩展函数">

    fun Byte.readBit(bitPos: Int): Int {
        return (this.toInt() shr bitPos) and 1
    }

    fun Byte.readBitStr(bitPos: Int): String {
        return this.readBit(bitPos).toString()
    }

    /**
     * 设置字节的指定位为1
     */
    fun Byte.setBit(bitPos: Int): Byte {
        return (this.toInt() or (1 shl bitPos)).toByte()
    }

    /**
     * 根据isSet设置字节指定位为0还是1
     */
    fun Byte.setBit(bitPos: Int, isSet: Boolean): Byte {
        return if (isSet) setBit(bitPos) else unsetBit(bitPos)
    }

    /**
     * 设置字节的指定位为0
     */
    fun Byte.unsetBit(position: Int): Byte {
        return (this.toInt() and (1 shl position).inv()).toByte()
    }

    fun Byte.toUnsignedInt(): Int {
        return this.toInt() and 0xff
    }

    fun Byte.toHexString(): String {
        var hex = Integer.toHexString(this.toInt() and 0xFF)
        if (hex.length == 1) {
            hex = "0$hex"
        }
        return hex
    }

    fun ByteArray.unsetBit(bitPos: Int) {
        if (bitPos < 0) {
            return
        }
        val i = bitPos / 8
        val j = bitPos % 8
        this[i] = this[i].unsetBit(j)
    }

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="基础数据类型扩展函数">

    /**
     * 按照大端或者小端模式，获取Short数据类型的对应的字节数组
     */
    fun Short.getByteArray(littleOrder: Boolean = true): ByteArray {
        return ByteBuffer.allocate(Short.SIZE_BYTES).order(
            if (littleOrder) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN
        ).putShort(this).array()
    }

    /**
     * 使用位移操作计算Int对应的字节数组数据
     * @param littleOrder 是否按照小端模式排序，默认开启
     */
    fun Int.toBinaryByteArray(littleOrder: Boolean = true): ByteArray =
        ByteBuffer.allocate(Int.SIZE_BYTES).order(if (littleOrder) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN).putInt(this).array()

// </editor-fold>



// <editor-fold defaultstate="collapsed" desc="ByteArray相关操作">
    /**
     * 指定字节数组指定位的大小
     * return  0 | 1
     */
    fun ByteArray.readBit(bitPos: Int): Int {
        val i = bitPos / 8
        val j = bitPos % 8
        return this[i].readBit(j)
    }

    fun ByteArray.setBit(position: Int) {
        if (position < 0) {
            return
        }
        val i = position / 8
        val j = position % 8
        this[i] = this[i].setBit(j)
    }


    /**
     * Print Hex byte array as String
     * @param bytes byte data array
     * @param addSpace Enable Space
     * @param toLowerCase Result value use lower case.
     */
    fun hexBytesToString(bytes: ByteArray?,
                         addSpace: Boolean = false,
                         toLowerCase:Boolean = false): String {
        if (bytes == null || bytes.isEmpty()) return "null"
        val sb = StringBuilder()
        for (i in bytes.indices) {
            var hex = Integer.toHexString(bytes[i].toInt() and 0xFF)
            if (hex.length == 1) {
                hex = "0$hex"
            }
            sb.append(hex)
            if (addSpace) sb.append(" ")
        }
        val result = sb.toString().trim { it <= ' ' }
        return if(toLowerCase) result.toLowerCase(Locale.ENGLISH) else result.toUpperCase(Locale.ENGLISH)
    }

    /**
     * 16进制文本字符串转成16进制字节数组
     */
    fun hexStringToBytes(hex: String): ByteArray? {
        var inHex = hex
        var hexlen = inHex.length
        val result: ByteArray
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++
            result = ByteArray(hexlen / 2)
            inHex = "0$inHex"
        } else {
            //偶数
            result = ByteArray(hexlen / 2)
        }
        var j = 0
        var i = 0
        while (i < hexlen) {
            result[j] = hexToByte(inHex.substring(i, i + 2))
            j++
            i += 2
        }
        return result
    }

    /**
     * 将字节数组转为小端模式
     */
    fun ByteArray.toLittleEndian(): ByteArray {
        return ByteBuffer.wrap(this).order(ByteOrder.LITTLE_ENDIAN).array()
    }

    /**
     * 将字节数组转为大端模式
     */
    fun ByteArray.toBigEndian(): ByteArray {
        return ByteBuffer.wrap(this).order(ByteOrder.BIG_ENDIAN).array()
    }

    /**
     * 根据输入的字节数组读取int值
     * @param srcBytes 目标字节数组，size 可以是3、4、5....
     * @param littleEndian 是否小端排序
     */
    fun readInt(srcBytes:ByteArray, littleEndian:Boolean = false):Int{
        var bytes = srcBytes
        if(srcBytes.size > 4){
            bytes = ByteArray(4)
            System.arraycopy(srcBytes, 0, bytes, 0, bytes.size)
        }else if(srcBytes.size < 4){
            bytes = ByteArray(4)
            System.arraycopy(srcBytes, 0, bytes, 4 - srcBytes.size, srcBytes.size)
        }
        val buffer = ByteBuffer.wrap(bytes)
        if(littleEndian){
            buffer.order(ByteOrder.LITTLE_ENDIAN)
        }else{
            buffer.order(ByteOrder.BIG_ENDIAN)
        }
        return buffer.asIntBuffer().get()
    }


// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="XxxToByte">
    private fun hexToByte(inHex: String): Byte {
        return inHex.toInt(16).toByte()
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="ByteToXxx">
    /**
     * Hex to Int
     * @param byte byte value
     */
    fun byteToInt(byte: Byte): Int {
        return byte.toInt() and 0xFF
    }
// </editor-fold>
}

