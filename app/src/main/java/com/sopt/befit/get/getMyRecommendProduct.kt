package com.sopt.befit.get

import com.sopt.befit.data.ProductData

data class GetMyRecommendProduct(
        val status : Int,
        val message : String,
        val data : ArrayList<ProductData>
)