package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.requests.ReportDeleteRequestDTO;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import com.denuncieagora.denuncie.mappers.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    public ReportResponseDTO getById(UUID uuid){
        return mapper.toResponse(repository.findById(uuid).get());
    }

    public ReportResponseDTO create(ReportRequestDTO request){
        Report model = mapper.toModel(request);
        repository.save(model);

        return mapper.toResponse(model);
    }

    @Transactional
    public void delete(ReportDeleteRequestDTO request){
        Report model = repository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("id is invalid"));

        if(!model.getIdentity().equals(request.getIdentity())) throw new IllegalArgumentException("identity is invalid");

        repository.delete(model);
    }

    @Transactional
    public ReportResponseDTO edit(ReportRequestDTO request, UUID id){
        Report model = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found!"));

        if(!model.getIdentity().equals(request.getIdentity())) throw new IllegalArgumentException("identity is invalid");

        model.setAbout(Arrays.stream(HateCrimeTypeEnum.values())
                .filter(type -> type.getId().equals(request.getAbout()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("About is invalid")));
        model.setState(request.getState());
        model.setCity(request.getCity());
        model.setDate(request.getDate());
        model.setDescription(request.getDescription());

        repository.save(model);

        return mapper.toResponse(model);
    }

}
