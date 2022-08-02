package com.kingz.kt

import com.kingz.kt.utils.HexUtil
import org.junit.Test

class HexUnitTest {

    @Test
    fun testPrintHexAsString(){
        val byteArray = ByteArray(4)
        byteArray[0] = 0x52
        byteArray[1] = 0x49
        byteArray[2] = 0x46
        byteArray[3] = 0x46
        val formatHexString = HexUtil.hexBytesToString(byteArray, addSpace = true)
        print("字符串:[$formatHexString]")
    }
}