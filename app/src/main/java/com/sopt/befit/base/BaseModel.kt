package com.sopt.befit.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// 나중에 다른 data class 에 상속해주면 된다.
open class BaseModel (
     // json key값이 field에 넣겠다
    @SerializedName("status")
    @Expose
    open var status: Int,
    @SerializedName("message")
    @Expose
    open var message : String
)