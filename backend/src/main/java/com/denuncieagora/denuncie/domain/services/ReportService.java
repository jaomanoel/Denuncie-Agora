package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.requests.ReportDeleteRequestDTO;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import com.denuncieagora.denuncie.mappers.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ReportMapper mapper;

    public List<ReportResponseDTO> getAll(HateCrimeTypeEnum hateCrime){
        List<ReportResponseDTO> response = repository
                .findAll(PageRequest.of(0, 5, Sort.by("date").descending()))
                .stream().map(this::toResponse)
                .toList();

        if(hateCrime != null){
            response = repository
                    .findByAbout(hateCrime, PageRequest.of(0, 5, Sort.by("date").descending()))
                    .stream().map(this::toResponse)
                    .toList();
        }

        return response;
    }

    public ReportResponseDTO getById(UUID uuid){
        Report report = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("id is invalid!"));

        ReportResponseDTO response = new ReportResponseDTO();
        response.setIdentity(report.getIdentity());
        response.setDate(report.getDate());
        response.setDescription(report.getDescription());
        response.setCity(report.getCity());
        response.setAbout(report.getAbout());
        response.setState(report.getState());
        response.setId(report.getId());

        return response;
    }

    public ReportResponseDTO create(ReportRequestDTO request){
        Report report = new Report();
        report.setAbout(toHateCrimeTypeEnum(request.getAbout()));
        report.setIdentity(request.getIdentity());
        report.setState(request.getState());
        report.setDescription(request.getDescription());
        report.setCity(request.getCity());
        report.setDate(request.getDate());


        repository.save(report);

        return toResponse(report);
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

        return toResponse(model);
    }

    private HateCrimeTypeEnum toHateCrimeTypeEnum(Integer value) {
        return Stream.of(HateCrimeTypeEnum.values())
                .filter(type -> type.getId().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("About is invalid!"));
    }

    private ReportResponseDTO toResponse(Report report) {
        ReportResponseDTO response = new ReportResponseDTO();
        response.setId(report.getId());
        response.setAbout(report.getAbout());
        response.setCity(report.getCity());
        response.setState(report.getState());
        response.setIdentity(report.getIdentity());
        response.setDate(report.getDate());
        response.setDescription(report.getDescription());

        return response;
    }

}
