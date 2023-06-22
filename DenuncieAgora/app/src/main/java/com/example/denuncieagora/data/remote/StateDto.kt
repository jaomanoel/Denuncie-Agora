package com.example.denuncieagora.data.remote

import com.example.denuncieagora.domain.model.State

data class StateDto(
    val id: Long,
    val sigla: String,
    val nome: String
)

fun StateDto.toState(): State {
    return State(
        id = id,
        nome = nome
    )
}