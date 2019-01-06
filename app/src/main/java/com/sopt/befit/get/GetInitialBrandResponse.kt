package com.sopt.befit.get


data class GetInitialBrandResponse(
        val status : String,
        val message: String,
        val data : ArrayList<InitialBrand>
)
data class InitialBrand(
        val idx : Int,
        val name_korean : String,
        val name_english : String,
        val logo_url : String
)