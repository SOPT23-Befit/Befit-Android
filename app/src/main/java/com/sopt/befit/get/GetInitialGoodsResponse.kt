package com.sopt.befit.get

import java.util.*

data class GetInitialGoodsResponse (
        val status : String,
        val message: String,
        val data : ArrayList<InitialGoods>
)
data class InitialGoods(
        val idx : Int,
        val name : String,
        val product_category_index : Int,
        val image_url : String,
        val brand_idx : Int,
        val measure : Object
)