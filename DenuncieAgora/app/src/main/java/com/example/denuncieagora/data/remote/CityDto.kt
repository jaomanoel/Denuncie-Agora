package com.example.denuncieagora.data.remote

import com.example.denuncieagora.domain.model.City

data class CityDto(
    val id: Long,
    val nome: String
)

fun CityDto.toCity(): City {
    return City(
        nome = nome
    )
}