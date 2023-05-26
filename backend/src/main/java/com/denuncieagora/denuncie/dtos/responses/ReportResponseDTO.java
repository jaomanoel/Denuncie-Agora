package com.denuncieagora.denuncie.dtos.responses;

import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {

    private UUID id;
    private HateCrimeTypeEnum about;
    private LocalDate date;
    private String state;
    private String city;
    private String description;
}
