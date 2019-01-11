package com.sopt.befit.network

import com.sopt.befit.data.*

import com.sopt.befit.post.PostForPwFindUserResponse
import com.sopt.befit.post.PostLoginResponse
import com.sopt.befit.post.PostSignUpResponse
import com.sopt.befit.post.PostTotalUserDataResponse
import com.sopt.befit.data.LoginData


import com.sopt.befit.get.GetBrandResponse
import com.sopt.befit.get.GetBrandListResponse
import com.sopt.befit.get.GetProductListResponse

import com.sopt.befit.get.*
import com.sopt.befit.post.*
import com.sopt.befit.put.PutModifyBrandResponse
import com.sopt.befit.put.PutModifyPwResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    //회원 가입
    @POST("/user")
    @Headers("Content-Type: application/json")
    fun postSignUpResponse(
            @Body userData: UserData
    ): Call<PostSignUpResponse>

    // 로그인
    @POST("/login")
    @Headers("Content-Type: application/json")
    fun postLoginResponse(
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

    @PUT("/user/combineForm")
    @Headers("Content-Type: application/json")
    fun TotalUserDataResponse(
            @Header("Authorization") authorization: String,
            @Body combineFormData: CombineFormData
    ): Call<PostTotalUserDataResponse>

    @GET("/user")
    fun getUserDataResponse(
            @Header("Authorization") token : String
    ) : Call<GetUserDataResponse>

    //사이즈 추가할 때 브랜드 검색
    @GET("/brands")
    fun getBrandsByInitialResponse(
            @Header("Authorization") authorization: String,
            @Query("initial") initial : Char
    ) : Call<GetInitialBrandResponse>

    //사이즈 추가할 때 브랜드 상품 검색
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
    ): Call<GetBrandListResponse>

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
            @Path("category_idx") category_idx: Int,
            @Query("gender") gender : String
    ): Call<GetProductListResponse>

    //상품 전체 카테고리 최신순 조회
    @GET("/products/popular/category/{category_idx}")
    fun getCategoryPopularProductListResponse(
            @Header("Authorization") authorization: String,
            @Path("category_idx") category_idx: Int,
            @Query("gender") gender : String
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


    @GET("/brands/randomPopular/three")
    fun getBrandRecommendResponse(
    @Header("Authorization") token : String
    ) : Call<GetBrandRecommendResponse>

    //상품 검색 초기 페이지
    @GET("/search/firstSearchPage")
    fun getSearchInitalListResponse(
            @Header("Authorization") authorization: String
    ): Call<GetProductListResponse>

    //상품 검색 신상순
    @GET("/search/products/new")
    fun getSearchNewProductResponse(
            @Header("Authorization") authorization: String,
            @Query("name") name : String
    ): Call<GetProductListResponse>

    //상품 검색 인기순
    @GET("/search/products/popular")
    fun getSearchPopularProductResponse(
            @Header("Authorization") authorization: String,
            @Query("name") name : String
    ): Call<GetProductListResponse>

    //브랜드 검색
    @GET("/search/brands")
    fun getSearchBrandResponse(
            @Header("Authorization") authorization: String,
            @Query("name") name : String
    ): Call<GetBrandListResponse>

    //특정 상품 정보 조회
    @GET("/products/{product_idx}")
    fun getEachProductListResponse(
            @Header("Authorization") authorization: String,
            @Path("product_idx") brand_idx: Int
    ): Call<GetEachProductResponse>

    //상품 사이즈 비교
    @GET("/closet/{closet_idx}/compare/{product_idx}") //?product_size={product_size}
    fun getCompareSizeResponse(
            @Header("Authorization") authorization: String,
            @Path("closet_idx") closet_idx: Int,
            @Path("product_idx") product_idx: Int,
            @Query("product_size")
      product_size: String
    ): Call<GetCompareSizeResponse>

    //나의 옷장 리스트
    @GET("/closet/category/{category_idx}")
    fun getClosetListResponse(
            @Header("Authorization") authorization: String,
            @Path("category_idx") category_idx: Int
    ): Call<GetClosetListResponse>

    //나의 옷장 아이템 삭제
    @DELETE("/closet/{closet_idx}")
    fun deleteClosetResponse(
            @Header("Authorization") authorization: String,
            @Path("closet_idx") closet_idx: Int
    ): Call<DeleteClosetResponse>

    @GET("/brands/preference")
    fun getBrandRankingResponse(
            @Header("Authorization") token : String
    ) : Call<GetBrandRankingResponse>


    //특정 상품 디테일 사이즈
    @GET("/closet/{closet_idx}")
    fun getCheckMySizeResponse(
            @Header("Authorization") token : String,
            @Path("closet_idx") closet_idx: Int
    ) : Call<GetCheckMySizeResponse>

    @GET("/products/forUserRec")
    fun getMyRecommendProduct(
            @Header("Authorization") token : String
    ) : Call<GetMyRecommendProduct>

    @POST("/closet")
    @Headers("Content-Type: application/json")
    fun postAddMyCloset(
            @Header("Authorization") authorization: String,
            @Body closetAddData: ClosetAddData
    ): Call<PostAddMyClosetResponse>


    @PUT("/user/brand")
    @Headers("Content-Type: application/json")
    fun putModifyBrandResponse(
            @Header("Authorization") token : String,
            @Body modifyBrandData : ModifyBrandData
    ): Call<PutModifyBrandResponse>

    @DELETE("/user")
    fun deleteAccountResponse(
            @Header("Authorization") authorization: String
    ): Call<DeleteAccountResponse>
}