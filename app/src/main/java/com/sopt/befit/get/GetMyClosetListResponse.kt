package com.sopt.befit.get

data class GetMyClosetListResponse(
        val status : Int,
        val message : String,
        val data : Array<ClosetDetail>
)
data class ClosetDetail(
        val closet_idx : Int,
        val name_korean : String,
        val name_english : String,
        val name : String,
        val image_url : String,
        val product_category_index : Int,
        val product_size : String,
        val measure : Any
)