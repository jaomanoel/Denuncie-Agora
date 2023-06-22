package com.example.denuncieagora.domain.useCases.new_report

class ValidateDescription {

    operator fun invoke(description: String): ValidationResult {
        if(description.equals(null) || description == "") {
            return ValidationResult(successful = false,
                errorMessage = "Descrição é necessária."
            )
        }

        if(description.length < 200) {
            return ValidationResult(successful = false,
                errorMessage = "Por favor, forneça mais informações sobre o caso."
            )
        }

        return ValidationResult(successful = true, errorMessage = null)
    }
}