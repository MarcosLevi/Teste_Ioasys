package com.example.testeioasys.endpoints

import com.example.testeioasys.models.EnterpriseListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EmpresaEndpoint {

    @GET("/api/v1/enterprises")
    fun carregaEmpresas(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("access-token") acessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String,
        @Query("enterprise_types") enterpriseTypes: String,
        @Query("name") name: String
    ): Call<EnterpriseListDto>
}