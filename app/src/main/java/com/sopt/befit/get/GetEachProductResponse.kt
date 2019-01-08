package com.sopt.befit.get

import com.sopt.befit.data.ProductData

data class GetEachProductResponse (
        var status : Int,
        var message : String,
        var data : ProductData
)
