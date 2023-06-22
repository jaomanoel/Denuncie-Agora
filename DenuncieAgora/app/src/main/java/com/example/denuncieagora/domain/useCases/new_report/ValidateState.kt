package com.example.denuncieagora.domain.useCases.new_report

class ValidateState {

    operator fun invoke(state: String): ValidationResult {
        if(state.equals(null) || state == "") {
            return ValidationResult(successful = false,
                errorMessage = "Estado e invalido."
            )
        }

        return ValidationResult(successful = true, errorMessage = null)
    }
}