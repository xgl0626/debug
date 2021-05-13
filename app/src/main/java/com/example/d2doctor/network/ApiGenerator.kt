package com.example.d2doctor.network

import com.example.d2doctor.network.config.Api
import com.example.d2doctor.network.userctrl.UserInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: RayleighZ
 * Time: 2021-05-01 16:08
 */
object ApiGenerator : UserInfo.TokenReceiver {

    //用户登陆认证
    private var token: String = ""

    private var retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl(Api.BASE_URL)
        OkHttpClient.Builder().interceptors().add(Interceptor {
            it.proceed(
                it.request().newBuilder().header("Authorization", "Bearer $token")
                    .build()
            )
        })
    }
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    init {
        //配置BaseUrl和Rxjava以及token
        UserInfo.resignTokenReceiver(this)
    }

    fun <T> getApiService(clazz: Class<T>) = run { retrofit.create(clazz) }

    override fun receiveToken(token: String) {
        this.token = token
    }

}