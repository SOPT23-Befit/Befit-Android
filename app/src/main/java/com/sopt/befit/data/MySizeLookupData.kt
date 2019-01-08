package com.sopt.befit.data

data class MySizeLookupData(
        val closet_idx : Int,
        val name_korean : String,
        val name_english : String,
        val name : String,
        val image_url : String,
        var product_category_index : Int,
        var product_size : Int,
        val mesure : Any
)