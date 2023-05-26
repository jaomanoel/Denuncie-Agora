package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import com.denuncieagora.denuncie.mappers.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ReportMapper mapper;

    public List<ReportResponseDTO> getAll(){
        return repository.findAll()
                .stream()
                .map((model) -> mapper.toResponse(model))
                .toList();
    }
}
