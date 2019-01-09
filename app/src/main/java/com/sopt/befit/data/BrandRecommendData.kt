package com.sopt.befit.data

import java.io.Serializable

data class BrandRecommendData (
        var idx : Int,
        var name_korean :String,
        var name_english : String,
        var gender : String,
        var style1:String,
        var style2:String,
        var like_score : String,
        var link : String,
        var logo_url : String,
        var mainpage_url :String,
        var mainfeed_url : String,
        var brand_like : Int,
        var products : ArrayList<ProductData>
): Serializable
//시리얼 상속시키며누번들 넣을 수 있음
