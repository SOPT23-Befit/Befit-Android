package com.sopt.befit.get

import com.sopt.befit.data.BrandData

data class GetBrandResponse(
        val status : Int,
        val message : String,
        val data : BrandData
)