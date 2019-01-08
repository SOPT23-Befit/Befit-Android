package com.sopt.befit.get

import java.io.Serializable

data class GetClosetListResponse(
    val data: ArrayList<ClosetDetail>,
    val message: String,
    val status: Int
)

data class ClosetDetail(
    val closet_idx: Int,
    val image_url: String,
    val measure: Any,
    val name: String,
    val name_english: String,
    val name_korean: String,
    val product_category_index: Int,
    val product_size: String
) : Serializable
