package com.example.testeioasys.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeioasys.models.EnterpriseDto
import com.example.testeioasys.models.EnterpriseListDto
import com.example.testeioasys.repository.EmpresaRepository
import com.example.testeioasys.repository.NetworkUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class EmpresasViewModel : ViewModel() {
    private val _empresas = MutableLiveData<MutableList<EnterpriseDto>>()
    val empresas: LiveData<MutableList<EnterpriseDto>> = _empresas
    fun carregaEmpresas(
        acessToken: String,
        client: String,
        uid: String,
        parametro: String
    ) {
        var enterpriseTypes = ""
        var name = parametro
        try {
            enterpriseTypes = parametro.toInt().toString()
            name = ""
        } catch (e: Exception) {
        }
        val callback = EmpresaRepository().carregaEmpresas(acessToken, client, uid, enterpriseTypes, name)
        configuraCallbackCarregaEmpresas(callback)
    }

    private fun configuraCallbackCarregaEmpresas(callback: Call<EnterpriseListDto>) {
        callback.enqueue(object : Callback<EnterpriseListDto> {
            override fun onFailure(call: Call<EnterpriseListDto>, t: Throwable) {
                Log.d("Erros", "Erros desconhecidos")
            }

            override fun onResponse(
                call: Call<EnterpriseListDto>,
                response: Response<EnterpriseListDto>
            ) {
                val code = response.code()
                when (code) {
                    NetworkUtil.RESPONSE_OK -> carregaEmpresasOk(response.body())
                    else -> carregaEmpresasError()
                }
            }

        })
    }

    fun carregaEmpresasOk(body: EnterpriseListDto?) {
        _empresas.value = body?.enterprises
    }

    fun carregaEmpresasError() {
    }
}