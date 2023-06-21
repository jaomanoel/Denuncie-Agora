package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.exceptions.AboutInvalidException;
import com.denuncieagora.denuncie.domain.exceptions.IdentityInvalidException;
import com.denuncieagora.denuncie.domain.exceptions.ReportNotFoundException;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ValidateReportData validate;

    public List<ReportResponseDTO> getAll(HateCrimeTypeEnum hateCrime){
        List<ReportResponseDTO> response = new ArrayList<>();

        if(hateCrime == null) {
            response = repository
                    .findAll(PageRequest.of(0, 5, Sort.by("date").descending()))
                    .stream().map(this::toResponse)
                    .toList();
        }

        if(hateCrime != null){
            response = repository
                    .findByAbout(hateCrime, PageRequest.of(0, 5, Sort.by("date").descending()))
                    .stream().map(this::toResponse)
                    .toList();
        }

        return response;
    }

    public ReportResponseDTO getById(UUID uuid) {
        Report report = repository.findById(uuid)
                .orElseThrow(() -> new ReportNotFoundException("id = " + uuid + " not found!"));

        return toResponse(report);
    }

    public ReportResponseDTO create(ReportRequestDTO request){
        validate.validateIdentity(request.getIdentity());
        validate.validateDate(request.getDate());;
        validate.validateDescription(request.getDescription());
        validate.validateCity(request.getCity());
        validate.validateState(request.getState());

        Report report = toModel(request);

        report = repository.save(report);

        return toResponse(report);
    }

    @Transactional
    public void delete(UUID id, String identity){
        validate.validateIdentity(identity);

        Report model = repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Id = " + id + " is invalid."));

        if(!model.getIdentity().equals(identity)) throw new IdentityInvalidException("Identity is invalid!");

        repository.delete(model);
    }

    @Transactional
    public ReportResponseDTO edit(ReportRequestDTO request, UUID id){
        validate.validateId(id);

        Report model = repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("id = " + id + " not found!"));

        if(!model.getIdentity().equals(request.getIdentity())) throw new IdentityInvalidException("Identity is invalid!");

        validate.validateDate(request.getDate());
        validate.validateDescription(request.getDescription());
        validate.validateState(request.getState());
        validate.validateCity(request.getCity());

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
                .orElseThrow(() -> new AboutInvalidException("This kind of hate crime is not valid."));
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
