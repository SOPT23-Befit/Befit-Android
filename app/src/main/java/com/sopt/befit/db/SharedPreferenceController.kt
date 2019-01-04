package com.sopt.befit.db

import android.content.Context
import android.content.SharedPreferences
import android.widget.ShareActionProvider


//DB를 이용하지 않고 데이터 저장하는 방법
//key -value 맵구조

object SharedPreferenceController {
    private val USER_NAME: String = "MYKEY"
    private val myAuth = "myAuth"

    private val USER_ID: String = "user_id"
    private val USER_PW: String = "user_pw"

    private var pref: SharedPreferences? = null


    fun setAuthorization(context: Context, authorization: String) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(myAuth, authorization)
        editor.commit()

    }

    fun getAuthorization(context: Context): String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        return pref.getString(myAuth, "")
    }

    /*fun getPref(context: Context) {
        if (pref == null) {
            pref = context.getSharedPreferences(SHARED_PREFS_CONFIGURATION, Context.MODE_PRIVATE)
        }
    }
    */

    /*fun load(context: Context) {
        getPref(context)
    }
    */

    fun setPrefData(key: String, value: Float) {
        val editor = pref!!.edit()

        editor.putFloat(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: String) {
        val editor = pref!!.edit()

        editor.putString(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: Int) {
        val editor = pref!!.edit()

        editor.putInt(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: Long) {
        val editor = pref!!.edit()

        editor.putLong(key, value)
        editor.commit()
    }



    //여기서부터 아이디패스워드
    fun setUserID(ctx: Context, input_id: String) {                        //아이디 설정
        val preferences: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_ID, input_id)
        editor.commit()
    }

    fun setUserPW(ctx: Context, input_pw: String) {                            //비밀번호 설정
        val preferences: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_PW, input_pw)
        editor.commit()
    }

    fun getUserID(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(USER_ID, "")
    }

    fun getUserPW(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(USER_PW, "")
    }

    fun clearUserSharedPreferences(ctx: Context) {
        val preference: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context. MODE_PRIVATE )
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.commit()
    }
    /*
    companion object {
        //공유 명칭
        //SHARED_PREF 바꿔줘야한다!!!
        private val SHARED_PREFS_CONFIGURATION = "GithubConfiguration"
        @Volatile private  var sharedPreferenceManager: SharedPreferenceController? = null

        val instance : SharedPreferenceController?
            get(){
                if (sharedPreferenceManager == null){
                    synchronized(SharedPreferenceController::class.java){
                        if(sharedPreferenceManager ==null)
                            sharedPreferenceManager = SharedPreferenceController()
                    }
                }
                return sharedPreferenceManager

            }
    }
    */

}


