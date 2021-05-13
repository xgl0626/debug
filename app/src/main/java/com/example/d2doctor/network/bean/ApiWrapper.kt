package com.example.d2doctor.network.bean

/**
 * Author: RayleighZ
 * Time: 2021-05-5 17:18
 */
data class ApiWrapper<T>(
    val data: T
) : ApiStatus()
