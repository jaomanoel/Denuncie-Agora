package com.example.denuncieagora.domain.useCases.new_report

class ValidateCity {

    operator fun invoke(city: String): ValidationResult {
        if(city.equals(null) || city == "") {
            return ValidationResult(successful = false,
                errorMessage = "Cidade é necessária."
            )
        }

        return ValidationResult(successful = true, errorMessage = null)
    }
}