package com.sopt.befit.get

import com.sopt.befit.data.BrandData


data class GetJjimBrandListResponse(
        val status : Int,
        val message : String,
        val data : ArrayList<BrandData>
)