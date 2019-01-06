package com.sopt.befit.get

import com.sopt.befit.data.JjimProductData

data class GetProductListResponse(
        val status : Int,
        val message : String,
        val data : ArrayList<JjimProductData>
)