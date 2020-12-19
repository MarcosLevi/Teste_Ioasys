package com.example.testeioasys.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeioasys.models.UserLoginDto
import com.example.testeioasys.models.UserLoginResponseDto
import com.example.testeioasys.repository.NetworkUtil
import com.example.testeioasys.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _response = MutableLiveData<UserLoginResponseDto>()
    val response: LiveData<UserLoginResponseDto> = _response

    fun doLogin(userLogin: UserLoginDto) {
        val callback = UserRepository().login(userLogin)
        configuraCallbackLogin(callback)
    }

    private fun configuraCallbackLogin(callback: Call<UserLoginResponseDto>) {
        callback.enqueue(object : Callback<UserLoginResponseDto> {
            override fun onFailure(call: Call<UserLoginResponseDto>, t: Throwable) {
                Log.d("Erros", "Erros desconhecidos")
            }

            override fun onResponse(
                call: Call<UserLoginResponseDto>,
                response: Response<UserLoginResponseDto>
            ) {
                val code = response.code()
                when (code) {
                    NetworkUtil.RESPONSE_OK -> loginOk(response)
                    else -> loginError(response.errorBody())
                }
            }
        })
    }

    fun loginOk(response: Response<UserLoginResponseDto>) {
        val headers = response.headers()
        val acessToken = headers["access-token"]
        val client = headers["client"]
        val uid = headers["uid"]

        val response = response.body()
        response?.acessToken = acessToken
        response?.client = client
        response?.uid = uid
        _response.value = response
    }

    fun loginError(response: ResponseBody?) {
        val gson = Gson()
        val type = object : TypeToken<UserLoginResponseDto>() {}.type
        val errorResponse: UserLoginResponseDto? = gson.fromJson(response!!.charStream(), type)
        _response.value = errorResponse
    }
}