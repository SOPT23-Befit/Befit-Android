package com.sopt.befit.get

import com.sopt.befit.data.ClosetData

data class GetRegisterClothResponse(
        val status : Int,
        val mesage : String,
        val data: ArrayList<ClosetData>
)