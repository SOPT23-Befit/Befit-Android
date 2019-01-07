package com.sopt.befit.post

data class PostForPwFindUserResponse(

        val status : Int,
        val message : String,
        val data : UserIdxData
)

data class UserIdxData(
        val idx : Int
)