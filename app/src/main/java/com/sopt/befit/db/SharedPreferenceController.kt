package com.sopt.befit.db

import android.content.Context
//DB를 이용하지 않고 데이터 저장하는 방법
//key -value 맵구조
object SharedPreferenceController {
    private  val USER_NAME =  "MYKEY"
    private  val myAuth = "myAuth"

    fun setAuthorization(context: Context, authorization : String){
        val pref = context.getSharedPreferences(USER_NAME,Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(myAuth,authorization)
        editor.commit()

    }
    fun getAuthorization(context: Context) : String {
        val pref = context.getSharedPreferences(USER_NAME,Context.MODE_PRIVATE)
        return pref.getString(myAuth,"")
    }


}