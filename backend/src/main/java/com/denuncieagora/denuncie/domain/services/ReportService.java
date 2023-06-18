package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import com.denuncieagora.denuncie.mappers.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        return toResponse(report);
    }

    public ReportResponseDTO create(ReportRequestDTO request){
        Report report = toModel(request);

        if(report.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date is invalid!");
        }

        if(report.getIdentity().equals("")) {
            throw new IllegalArgumentException("Report is invalid!");
        }

        if(report.getDescription().length() <= 200) {
            throw new IllegalArgumentException("Description is invalid!");
        }

        repository.save(report);

        return toResponse(report);
    }

    @Transactional
    public void delete(UUID id, String identity){
        if(identity.equals("")) {
            throw new IllegalArgumentException("Identity is required!");
        }

        Report model = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id is invalid"));

        if(!model.getIdentity().equals(identity)) throw new IllegalArgumentException("Identity is invalid!");

        repository.delete(model);
    }

    @Transactional
    public ReportResponseDTO edit(ReportRequestDTO request, UUID id){
        Report model = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found!"));

        if(!model.getIdentity().equals(request.getIdentity())) throw new IllegalArgumentException("identity is invalid");

        model.setAbout(toHateCrimeTypeEnum(request.getAbout()));
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

    private Report toModel(ReportRequestDTO requestDTO) {
        Report report = new Report();
        report.setAbout(toHateCrimeTypeEnum(requestDTO.getAbout()));
        report.setIdentity(requestDTO.getIdentity());
        report.setState(requestDTO.getState());
        report.setCity(requestDTO.getCity());
        report.setDate(requestDTO.getDate());
        report.setDescription(requestDTO.getDescription());

        return report;
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
