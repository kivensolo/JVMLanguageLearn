package com.kingz.kt.collcetions

import java.util.concurrent.ConcurrentHashMap

/**
 * author: King.Z <br>
 * date:  2020/3/18 17:37 <br>
 * description:  <br>
 */
object Collection {

    @JvmStatic
    fun main(args: Array<String>) {
         var taskCacheMap: ConcurrentHashMap<String, String> = ConcurrentHashMap()
        taskCacheMap["1"] = "2"
        taskCacheMap["2"] = "2"
        taskCacheMap["3"] = "2"
        taskCacheMap["45"] = "2"
        taskCacheMap.forEach{
            if(it.key == "45"){
                taskCacheMap.remove(it.key)
            }
        }

        print("${taskCacheMap.size}")


    }
}