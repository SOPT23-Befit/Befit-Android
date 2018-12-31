package com.sopt.befit.db

import android.content.Context
import android.content.SharedPreferences



//DB를 이용하지 않고 데이터 저장하는 방법
//key -value 맵구조
class SharedPreferenceController {
    private  val USER_NAME =  "MYKEY"
    private  val myAuth = "myAuth"

    private  var pref :SharedPreferences? = null


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
    fun getPref(context: Context){
        if (pref == null){
            pref = context.getSharedPreferences(SHARED_PREFS_CONFIGURATION,Context.MODE_PRIVATE)
        }
    }
    fun load(context: Context){
        getPref(context)
    }
    fun setPrefData(key : String, value : Float){
        val editor =pref!!.edit()

        editor.putFloat(key,value)
        editor.commit()
    }
    fun setPrefData(key : String, value : String){
        val editor =pref!!.edit()

        editor.putString(key,value)
        editor.commit()
    }
    fun setPrefData(key : String, value : Int){
        val editor =pref!!.edit()

        editor.putInt(key,value)
        editor.commit()
    }
    fun setPrefData(key : String, value : Long){
        val editor =pref!!.edit()

        editor.putLong(key,value)
        editor.commit()
    }

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
}