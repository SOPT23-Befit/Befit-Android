package com.sopt.befit.get

import com.sopt.befit.data.MySizeLookupData

data class GetMySizeLookupListResponse(
        val status : Int,
        val message : String,
        val data : ArrayList<MySizeLookupData>
)