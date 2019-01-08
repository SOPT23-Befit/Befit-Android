package com.sopt.befit.get

import com.sopt.befit.data.BrandRecommendData

data class GetBrandRecommendResponse(

        val status : Int,
        val message : String,
        val data : ArrayList<BrandRecommendData>

)