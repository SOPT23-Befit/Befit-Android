package com.sopt.befit.get

import com.sopt.befit.data.UserTotalData

data class GetUserDataResponse(
        val data: ArrayList<UserTotalData>,
        val message: String,
        val status: Int
)


