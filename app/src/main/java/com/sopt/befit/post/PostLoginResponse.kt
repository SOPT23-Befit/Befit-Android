package com.sopt.befit.post

data class PostLoginResponse(
        val status : Int,
        val message : String,
        val data : LoginData
)

data class LoginData(
        val token : String
)