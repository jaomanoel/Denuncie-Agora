package com.example.denuncieagora.domain.useCases.new_report

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)