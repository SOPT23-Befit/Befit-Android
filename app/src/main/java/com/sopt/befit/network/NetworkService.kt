package com.sopt.befit.network

import com.google.gson.JsonObject
import com.sopt.befit.post.PostLoginResponse
import com.sopt.befit.post.PostSignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {


    //회원 가입
    @POST("/user")
    fun postSignUpResponse(
            @Header("Content-Type: application/json") content_type: String,
            @Body body: JsonObject
    ) : Call<PostSignUpResponse>


    @POST("/login")
    fun postLoginResponse(
            @Header("Content-Type") content_type : String,
            @Body() body : JsonObject
    ): Call<PostLoginResponse>
}