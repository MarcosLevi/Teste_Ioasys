package com.example.testeioasys.repository

import com.example.testeioasys.endpoints.UserEndpoint
import com.example.testeioasys.models.UserLoginDto
import com.example.testeioasys.models.UserLoginResponseDto
import retrofit2.Call

class UserRepository {
    fun login(userLogin: UserLoginDto): Call<UserLoginResponseDto> {
        val endpoint = NetworkUtil.getRetrofitInstance().create(UserEndpoint::class.java)
        return endpoint.login(userLogin)
    }
}