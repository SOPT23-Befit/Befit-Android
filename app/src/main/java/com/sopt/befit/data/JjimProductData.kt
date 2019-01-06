package com.sopt.befit.data

data class JjimProductData(
        val idx : Int,
        val name : String,
        val price : String,
        val image_url : String,
        val product_category_index : Int,
        val brand_idx : Int,
        val date : String,
        val link : String,
        val mesure : Any,
        var like_score : Int,
        var product_like : Int,
        val brand_Korean_name : String
)