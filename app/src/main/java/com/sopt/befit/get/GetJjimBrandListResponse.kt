package com.sopt.befit.get

import com.sopt.befit.data.JjimBrandData


data class GetJjimBrandListResponse(
        val status : Int,
        val message : String,
        val data : ArrayList<JjimBrandData>
)