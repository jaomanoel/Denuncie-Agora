package com.denuncieagora.denuncie.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReportDeleteRequestDTO {

    @NotBlank
    @NotNull
    private UUID id;

    @NotNull
    @NotBlank
    private String identity;
}
