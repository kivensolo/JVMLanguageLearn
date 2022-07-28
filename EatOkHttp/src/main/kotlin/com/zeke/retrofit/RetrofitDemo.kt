package com.zeke.retrofit

import com.zeke.retrofit.bean.ArticleData
import com.zeke.retrofit.response.WanAndroidResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 最原始的请求方式:
 * Step1: 创建retrofit对象,构建一个网络请求的载体对象,和OkHttp构建OkhttpClient对象有一样的意义，
 * 只不过retrofit在build的时候有非常多的初始化内容，这些内容可以为后面网络请求提供准备，
 * 如准备,现成转换Executor,Gson convert,RxJavaCallAdapter。
 *
 * Step2：Retrofit的精髓，为统一配置网络请求完成动态代理的设置。
 * Step3：构建具体网络请求对象Request（service），在这个阶段要完成的任务：
 *
 *  1）将接口中的注解翻译成对应的参数；
 *  2）确定网络请求接口的返回值response类型以及对应的转换器；
 *  3）将Okhttp的Request封装成为Retrofit的 OKhttpCall。总结来说，就是根据请求service 的Interface来封装Okhttp请求Request。
 *
 * Step4：后面就进行网络请求了，然后处理网络请求的数据了
 */

fun main() = runBlocking {
    //step1 创建retrofit对象,和OkHttp构建OkhttpClient对象有一样的意义
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.wanandroid.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //step2 Retrofit的精髓，为统一配置网络请求完成动态代理的设置
    val apiService = retrofit.create(IWanAndroidApiService::class.java)

    val handler = CoroutineExceptionHandler { _, throwable ->
        println("has Exception: $throwable") // Prints "handler: java.io.IOException"
    }
//  val result =  async { requestBase(apiService) }
    val result = async(handler) {
//        throw SocketTimeoutException("TTTT")
        requestDirectly(apiService)
    }
    println("Scope end: " + result.await())
}

/**
 * 直接请求，返回数据对象
 */
private suspend fun requestDirectly(apiService: IWanAndroidApiService) {
    val result = apiService.requestArticles(1)
    println("Result:$result")
}

/**
 * 以最基础的方式请求
 * 返回Call对象
 */
private suspend fun requestBase(apiService: IWanAndroidApiService) {
    //step3 构建具体网络请求对象Request（service)
    val defaultOkHttpCall = apiService.requestArticlesWithCall(1)
    //step4 进行网络请求
    defaultOkHttpCall.enqueue(object : Callback<WanAndroidResponse<ArticleData>> {

        override fun onFailure(call: Call<WanAndroidResponse<ArticleData>>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(
            call: Call<WanAndroidResponse<ArticleData>>,
            response: Response<WanAndroidResponse<ArticleData>>
        ) {
            if (response.isSuccessful) {
                println("result:" + response.body().toString())
            }
        }
    })
}