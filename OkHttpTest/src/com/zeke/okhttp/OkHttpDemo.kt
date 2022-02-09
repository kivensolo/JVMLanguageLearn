package com.zeke.okhttp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

object OkHttpDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        testOKHttp()
        Thread.sleep(10 * 1000)
    }

    /**
     * okhttp请求样例代码
     */
    private fun testOKHttp() {
        GlobalScope.launch(Dispatchers.IO)  {
            println("launch Thread: ${Thread.currentThread().name}")
            //创建HttpClient对象，一般会将所有的网络请求使用同一个单例对象
            val client = OkHttpClient()
            //构建Request
            val request = Request.Builder().url("http://www.wanandroid.com").build()
            //构建请求Call
            val call = client.newCall(request)
            //发送网络请求，获取数据，进行后续处理
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("onFailure")
                }

                override fun onResponse(call: Call, response: Response) {
                    //回调线程是OKHTTP的线程
                    println("Thread: [${Thread.currentThread().name}]   onResponse:$response")
                }
            })
        }
    }
}