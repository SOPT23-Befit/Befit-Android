package com.sopt.befit.get

import com.sopt.befit.data.BrandData
import com.sopt.befit.data.BrandRankingData

data class GetBrandRankingResponse(
        val status : Int,
        val message : String,
        val data : BrandRankingData
)