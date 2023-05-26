package com.denuncieagora.denuncie.dtos.requests;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportRequestDTO {

    @NotNull
    private Integer about;

    @NotNull
    @NotBlank
    @Min(32)
    @Max(36)
    private String identity;

    @NotNull
    @NotBlank
    private LocalDate date;

    @NotNull
    @NotBlank
    private String state;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String description;
}
