package com.denuncieagora.denuncie.mappers;

import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportResponseDTO toResponse(Report model);
}
