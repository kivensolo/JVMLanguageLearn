package com.kingz.kt.deep
/**
 * author: King.Z <br>
 * date:  2020/3/4 17:34 <br>
 * description: 探究@JvmStatic和@JvmField的实质 <br>
 */
class AnnotationTest{
    companion object {
        @JvmField
        val compainleVal_1 :String = "compainleVal_1"
        // 最好用下面这种
        const val compainleVal_2 :String = "compainleVal_2"

        @JvmField
        var compainleVar_1 :String = "compainleVar_1"

        var compainleVar_2 :String = "compainleVar_2"
    }
}