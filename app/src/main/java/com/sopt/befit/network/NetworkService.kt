package com.sopt.befit.network

import com.google.gson.JsonObject
import com.sopt.befit.data.ForPwUserData
import com.sopt.befit.data.LoginData
import com.sopt.befit.data.ModifyPWData
import com.sopt.befit.data.UserData
import com.sopt.befit.post.PostForPwFindUserResponse
import com.sopt.befit.post.PostLoginResponse
import com.sopt.befit.post.PostSignUpResponse
import com.sopt.befit.put.PutModifyPwResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface NetworkService {


    //회원 가입
    @POST("/user")
    fun postSignUpResponse(
            @Header("Content-Type: application/json")
            @Body userData: UserData
    ) : Call<PostSignUpResponse>

    // 로그인
    @POST("/login")
    fun postLoginResponse(
            @Header("Content-Type: application/json")
            @Body loginData: LoginData
    ): Call<PostLoginResponse>

    //pw 재설정
    @PUT("/user")
    fun putModifyPWResponse(
            @Header("Content-Type: application/json")
            @Body modifyPWData: ModifyPWData
    ) : Call<PutModifyPwResponse>


    //pw 찾기를 위한 회원 조회

    @POST("/user/passwordFind")
    fun ForPwUserDataResponse(
            @Header ("Content-Type: application/json")
            @Body forPwUserData: ForPwUserData
    ): Call<PostForPwFindUserResponse>


}