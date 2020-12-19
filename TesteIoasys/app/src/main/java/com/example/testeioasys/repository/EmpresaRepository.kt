package com.example.testeioasys.repository

import com.example.testeioasys.endpoints.EmpresaEndpoint
import com.example.testeioasys.endpoints.UserEndpoint
import com.example.testeioasys.models.EnterpriseDto
import com.example.testeioasys.models.EnterpriseListDto
import com.example.testeioasys.models.UserLoginDto
import com.example.testeioasys.models.UserLoginResponseDto
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

class EmpresaRepository {
    fun carregaEmpresas(
        acessToken: String,
        client: String,
        uid: String,
        enterpriseTypes: String,
        name: String
    ): Call<EnterpriseListDto> {
        val endpoint = NetworkUtil.getRetrofitInstance().create(EmpresaEndpoint::class.java)
        return endpoint.carregaEmpresas(acessToken = acessToken, client = client, uid = uid, enterpriseTypes = enterpriseTypes, name = name)
    }

}