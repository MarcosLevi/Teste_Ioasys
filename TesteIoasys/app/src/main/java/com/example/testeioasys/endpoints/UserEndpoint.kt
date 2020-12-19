package com.example.testeioasys.endpoints

import com.example.testeioasys.models.UserLoginDto
import com.example.testeioasys.models.UserLoginResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserEndpoint {

    @Headers("Content-Type: application/json")
    @POST("/api/v1/users/auth/sign_in")
    fun login(@Body body: UserLoginDto): Call<UserLoginResponseDto>
}