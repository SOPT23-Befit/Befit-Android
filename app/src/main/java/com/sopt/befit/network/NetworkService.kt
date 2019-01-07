package com.sopt.befit.network

import com.sopt.befit.data.*

import com.sopt.befit.get.GetUserDataResponse

import com.sopt.befit.get.GetInitialBrandResponse
import com.sopt.befit.get.GetInitialGoodsResponse

import com.sopt.befit.post.PostForPwFindUserResponse
import com.sopt.befit.post.PostLoginResponse
import com.sopt.befit.post.PostSignUpResponse
import com.sopt.befit.post.PostTotalUserDataResponse
import com.sopt.befit.data.LoginData
import com.sopt.befit.get.GetBrandResponse
import com.sopt.befit.get.GetJjimBrandListResponse
import com.sopt.befit.get.GetProductListResponse
import com.sopt.befit.post.*
import com.sopt.befit.put.PutModifyPwResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    //회원 가입
    @POST("/user")
    fun postSignUpResponse(
            @Header("Content-Type: application/json")
            @Body userData: UserData
    ): Call<PostSignUpResponse>

    // 로그인
    @POST("/login")
    fun postLoginResponse(
            @Header("Content-Type: application/json")
            @Body loginData: LoginData
    ): Call<PostLoginResponse>

    //pw 재설정
    @PUT("/user")
    @Headers("Content-Type: application/json")
    fun putModifyPWResponse(
            @Body modifyPWData: ModifyPWData
    ): Call<PutModifyPwResponse>


    //pw 찾기를 위한 회원 조회

    @POST("/user/passwordFind")
    fun ForPwUserDataResponse(
            @Header("Content-Type") type : String,
            @Body forPwUserData: ForPwUserData
    ): Call<PostForPwFindUserResponse>

    @POST("/user/combineForm")
    fun TotalUserDataResponse(
            @Header("Content-Type: application/json")
            @Body combineFormData: CombineFormData
    ): Call<PostTotalUserDataResponse>

    @GET("/user")
    fun getUserDataResponse(
            @Header("Content-Type") content_type : String,
            @Header("Authorization") token : String,
            @Body userData : UserTotalData
    ) : Call<GetUserDataResponse>

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

    //좋아요한 물품 보기
    @GET("/likes/products")
    fun getJjimProductListResponse(
            @Header("Authorization") authorization: String
    ): Call<GetProductListResponse>

    //상품 좋아요
    @POST("/likes/products/{product_idx}")
    fun postProductLikeResponse(
            @Header("Authorization") authorization: String,
            @Path("product_idx") product_idx: Int
    ): Call<PostProductLikeResponse>

    //상품 좋아요 취소
    @DELETE("/likes/products/{product_idx}")
    fun postProductUnlikeResponse(
            @Header("Authorization") authorization: String,
            @Path("product_idx") product_idx: Int
    ): Call<PostProductUnlikeResponse>

    //좋아요한 브랜드 보기
    @GET("/likes/brands")
    fun getJjimBrandListResponse(
            @Header("Authorization") authorization: String
    ): Call<GetJjimBrandListResponse>

    //브랜드 좋아요
    @POST("/likes/brands/{brand_idx}")
    fun postBrandLikeResponse(
            @Header("Authorization") authorization: String,
            @Path("brand_idx") brand_idx: Int
    ): Call<PostBrandLikeResponse>

    //브랜드 좋아요 취소
    @DELETE("/likes/brands/{brand_idx}")
    fun postBrandUnlikeResponse(
            @Header("Authorization") authorization: String,
            @Path("brand_idx") brand_idx: Int
    ): Call<PostBrandUnlikeResponse>

    //브랜드 매인 정보 통신
    @GET("/brands/{brand_idx}")
    fun getBrandResponse(
            @Header("Authorization") authorization: String,
            @Path("brand_idx") brand_idx: Int
    ): Call<GetBrandResponse>

    //상품 전체 리스트 조회
    @GET("/products")
    fun getAllProductListResponse(
            @Header("Authorization") authorization: String
    ): Call<GetProductListResponse>

    //상품 전체 카테고리 최신순 조회
    @GET("/products/new/category/{category_idx}")
    fun getCategoryNewProductListResponse(
            @Header("Authorization") authorization: String,
            @Path("category_idx") category_idx: Int
    ): Call<GetProductListResponse>

    //상품 전체 카테고리 최신순 조회
    @GET("/products/popular/category/{category_idx}")
    fun getCategoryPopularProductListResponse(
            @Header("Authorization") authorization: String,
            @Path("category_idx") category_idx: Int
    ): Call<GetProductListResponse>

    //상품 전체 브랜드 최신순 조회
    @GET("/products/new/brand/{brand_idx}")
    fun getBrandNewProductListResponse(
            @Header("Authorization") authorization: String,
            @Path("brand_idx") brand_idx: Int
    ): Call<GetProductListResponse>

    //상품 전체 브랜드 인기순 조회
    @GET("/products/popular/brand/{brand_idx}")
    fun getBrandPopularProductListResponse(
            @Header("Authorization") authorization: String,
            @Path("brand_idx") brand_idx: Int
    ): Call<GetProductListResponse>

}