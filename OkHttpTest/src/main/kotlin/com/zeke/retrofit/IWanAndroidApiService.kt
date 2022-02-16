package com.zeke.retrofit

import com.zeke.retrofit.bean.ArticleData
import com.zeke.retrofit.response.WanAndroidResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 接口层的这个返回值怎么定义？？？ 一直没搞懂
 *
 * 应该是支持一个泛型。
 */
interface IWanAndroidApiService {
    /**
     * 获取文章列表
     * @param pageIndex 页码从0开始
     * @return 包含response的Call对象
     */
    @GET("/article/list/{pageIndex}/json")
    fun requestArticlesWithCall(@Path("pageIndex") pageIndex: Int): Call<WanAndroidResponse<ArticleData>>

    /**
     * 获取文章列表
     * @param pageIndex 页码从0开始
     * @return response数据类型
     * 非挂起函数的返回值直接写 WanAndroidResponse<ArticleData> 会提示 Could not locate call adapter，导致抛异常。
     *
     * 因Retrofit内部对kotlin 挂起函数做了返回参数的封装，会包裹一层Call类型。所以挂起函数
     */
    @GET("/article/list/{pageIndex}/json")
    suspend fun requestArticles(@Path("pageIndex") pageIndex: Int): WanAndroidResponse<ArticleData>

}