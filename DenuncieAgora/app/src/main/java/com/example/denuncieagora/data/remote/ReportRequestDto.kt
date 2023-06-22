package com.example.denuncieagora.data.remote

data class ReportRequestDto(
    val identity: String,
    val about: Int,
    val date: String,
    val state: String,
    val city: String,
    val description: String
)