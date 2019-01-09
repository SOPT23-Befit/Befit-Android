package com.sopt.befit.data

import java.io.Serializable

data class BrandData(
        val idx: Int,
        val name_korean: String,
        val name_english: String,
        val gender: String,
        val style1: String,
        val style2: String,
        var like_score: Int,
        val link: String,
        val logo_url: String,
        val mainpage_url: String,
        val mainfeed_url: String,
        var likeFlag: Int
) : Serializable