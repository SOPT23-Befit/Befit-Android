package com.sopt.befit.get

data class GetCompareSizeResponse(
        val status : Int,
        val message : String,
        val data  : Compare
)
data class Compare(
        val measure : Any,
        val percent : String,
        val my_url : String,
        val compare_url : String
)