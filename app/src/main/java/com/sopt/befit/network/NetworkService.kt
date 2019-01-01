package com.sopt.befit.network

import com.google.gson.JsonObject
import com.sopt.befit.data.LoginData
import com.sopt.befit.data.UserData
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
            @Header("Content-Type: application/json")
            @Body userData: UserData
    ) : Call<PostSignUpResponse>


    @POST("/login")
    fun postLoginResponse(
            @Header("Content-Type: application/json")
            @Body() loginData: LoginData
    ): Call<PostLoginResponse>


}