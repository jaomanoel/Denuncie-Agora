package com.denuncieagora.denuncie.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StateResponseDTO {

    private Long id;
    private String sigla;
    private String nome;
}