package com.sopt.befit.post

data class PostForPwFindUserResponse(

        var status : Int,
        var message : String,
        var data : UserIdxData
)

data class UserIdxData(
        var idx : Int
)