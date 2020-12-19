package com.example.testeioasys.sharedpreferences

import android.content.Context

class Preferences(context: Context) {
    val PREFERENCE_NAME = "SharedPreferences"
    val PREFERENCE_TOKEN = "acessToken"
    val PREFERENCE_CLIENT = "client"
    val PREFERENCE_UID = "uid"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getAcessToken(): String
    {
        return preference.getString(PREFERENCE_TOKEN,"").toString()
    }

    fun setAcessToken(token: String){
        val editor = preference.edit()
        editor.putString(PREFERENCE_TOKEN,token)
        editor.apply()
    }
    fun getClient(): String
    {
        return preference.getString(PREFERENCE_CLIENT,"").toString()
    }

    fun setClient(client: String){
        val editor = preference.edit()
        editor.putString(PREFERENCE_CLIENT,client)
        editor.apply()
    }
    fun getUid(): String
    {
        return preference.getString(PREFERENCE_UID,"").toString()
    }

    fun setUid(uid: String){
        val editor = preference.edit()
        editor.putString(PREFERENCE_UID,uid)
        editor.apply()
    }
}