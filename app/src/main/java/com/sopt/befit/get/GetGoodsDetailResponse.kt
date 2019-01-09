package com.sopt.befit.get

import java.util.ArrayList

data class GetGoodsDetailResponse (
        val status : String,
        val message: String,
        val data : ArrayList<GoodsDetail>
)
data class GoodsDetail(
        val idx : Int,
        val name : String,
        val product_category_index : Int,
        val image_url : String,
        val brand_idx : Int,
        val measure : Any
)