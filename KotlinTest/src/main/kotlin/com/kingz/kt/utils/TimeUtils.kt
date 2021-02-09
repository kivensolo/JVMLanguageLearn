package com.kingz.kt.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * All rights reserved. <br></br>
 * author: King.Z <br></br>
 * date:  2018/2/13 15:00 <br></br>
 * description: XXXXXXX <br></br>
 */
object TimeUtils {
    private val format = SimpleDateFormat("HH:mm:ss")
    private val TimeDelta = System.currentTimeMillis() - timestamp()

    val currentTime: String?
        get() {
            return try {
                format.format(Date(currentTimeMillis()))
            } catch (var1: Exception) {
                var1.printStackTrace()
                null
            }
        }

    private fun currentTimeMillis(): Long {
        return timestamp() + TimeDelta
    }

    private fun timestamp(): Long {
        return System.nanoTime() / 1_000_000L
    }
}
