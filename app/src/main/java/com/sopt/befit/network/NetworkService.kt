package com.sopt.befit.network

import com.google.gson.JsonObject
import com.sopt.befit.data.*
import com.sopt.befit.get.GetInitialBrandResponse
import com.sopt.befit.get.GetInitialGoodsResponse
import com.sopt.befit.post.PostForPwFindUserResponse
import com.sopt.befit.post.PostLoginResponse
import com.sopt.befit.post.PostSignUpResponse
import com.sopt.befit.post.PostTotalUserDataResponse
import com.sopt.befit.put.PutModifyPwResponse
import retrofit2.Call
import retrofit2.http.*

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

    @POST("/user/combineForm")
    fun TotalUserDataResponse(
        @Header ("Content-Type: application/json")
        @Body combineFormData: CombineFormData
    ): Call<PostTotalUserDataResponse>

    @GET("/brands")
    fun getBrandsByInitialResponse(
            @Header("Authorization") authorization: String,
            @Query("initial") initial : Char
    ) : Call<GetInitialBrandResponse>

    @GET("/closet/brands/{brand_idx}/category/{category_idx}")
    fun getGoodsByInitialResponse(
            @Header("Authorization") authorization: String,
            @Path("brand_idx") brand_idx : Int,
            @Path("category_idx") category_idx : Int
    ) : Call<GetInitialGoodsResponse>
}