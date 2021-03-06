package com.sopt.befit.data

import java.io.Serializable

data class ProductData(
        val idx : Int,
        val name : String,
        val price : String,
        val image_url : String,
        val product_category_index : Int,
        val brand_idx : Int,
        val date : String,
        val link : String,
        val measure : Any,
        var like_score : Int,
        var product_like : Int,
        val name_korean : String,
        val name_english : String
) : Serializable