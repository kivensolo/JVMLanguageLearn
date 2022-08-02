package com.kingz.kt.utils

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset
import java.text.DateFormat
import java.util.*

object StringUtils {

// <editor-fold defaultstate="collapsed" desc="字符串扩展函数">

    fun String.toBinaryByteArray(
        littleOrder: Boolean = true,
        charset: Charset = Charsets.UTF_8): ByteArray {
        val byteArray = toByteArray(charset)
        return ByteBuffer.wrap(byteArray).order(
            if (littleOrder) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN
        ).array()
    }


    /**
     * 将”yyyy-MM-dd“格式的字符串时间转为Calendar
     */
    fun String.parseTimeToCalendar(format: DateFormat): Calendar {
        val date = format.parse(this)
        val instance = Calendar.getInstance()
        instance.time = date!!
        return instance
    }
// </editor-fold>
}