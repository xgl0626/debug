package com.example.d2doctor.network

import com.example.d2doctor.bean.Order
import com.example.d2doctor.bean.UserInfo
import com.example.d2doctor.network.bean.ApiStatus
import com.example.d2doctor.network.bean.ApiWrapper
import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Author: RayleighZ
 * Time: 2021-05-3 16:15
 */
interface ApiService {

    //用户操作部分

    @FormUrlEncoded
    @POST("/register")
    fun registerUser(): Observable<ApiStatus>

    @FormUrlEncoded
    @POST("/login")
    fun login(): Observable<ApiStatus>

    @FormUrlEncoded
    @POST("/user/info")
    fun modifyUserInfo(): Observable<ApiStatus>

    //获取个人相关数据
    @GET("/user/info")
    fun getUserInfo(): Observable<ApiWrapper<UserInfo>>

    @GET("/order")
    fun getUserOrder(): Observable<ApiWrapper<List<Order>>>

    //用户自我数据管理

}