package com.sopt.befit.post

data class PostLoginResponse(//응답바디데이타
        val status : Int,
        val message : String,
        val data : LoginresponseData
)

data class LoginresponseData(
        val token : String
)