package com.example.d2doctor.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Author: RayleighZ
 * Time: 2021-05-10 18:16
 */
data class Order(
    @SerializedName("title")
    val title: String,
    @SerializedName("publish_time")
    val publishTime: Long,
    @SerializedName("price")
    val price: Int,
    @SerializedName("feature_id")
    val feature_id: Int,
    @SerializedName("topic")
    val topic: Int,
    @SerializedName("user_info")
    val user_info: UserInfo
): Serializable
