package com.denuncieagora.denuncie.mappers;

import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportMapper MAPPER = Mappers.getMapper(ReportMapper.class);

    ReportResponseDTO toResponse(Report model);

    Report toModel(ReportRequestDTO request);

    default HateCrimeTypeEnum toHateCrimeTypeEnum(Integer value) {
        return Stream.of(HateCrimeTypeEnum.values())
                .filter(type -> type.getId().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("About is invalid!"));
    }

    default Integer toInteger(HateCrimeTypeEnum value) {
        return value.getId();
    }
}
