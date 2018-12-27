package com.sopt.befit.network

import com.google.gson.JsonObject
import com.sopt.befit.post.PostLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {


    @POST("/login")
    fun postLoginResponse(
            @Header("Content-Type") content_type : String,
            @Body() body : JsonObject
    ): Call<PostLoginResponse>
}