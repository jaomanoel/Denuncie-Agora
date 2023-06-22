package com.example.denuncieagora.data.remote.dto

import com.example.denuncieagora.data.remote.CityDto
import com.example.denuncieagora.data.remote.StateDto
import com.example.denuncieagora.domain.model.City
import com.example.denuncieagora.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface IbgeApi {

    @GET(Constants.IBGE_ENDPOINT+"/states")
    suspend fun getStates(): List<StateDto>

    @GET(Constants.IBGE_ENDPOINT+"/cities/{id}")
    suspend fun getCities(@Path("id") id: Long): List<CityDto>
}