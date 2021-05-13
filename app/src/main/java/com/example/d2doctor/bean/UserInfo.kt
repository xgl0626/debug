package com.example.d2doctor.bean

import com.google.gson.annotations.SerializedName

/**
 * Author: RayleighZ
 * Time: 2021-05-13 18:19
 */
data class UserInfo(
    @SerializedName("user_id")
    val id: Int,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("nick_name")
    val nick_name: String
)
