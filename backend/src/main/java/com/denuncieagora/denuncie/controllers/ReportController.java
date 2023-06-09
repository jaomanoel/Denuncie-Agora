package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.services.ReportService;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportResponseDTO> getAll(@RequestParam(name = "hatecrime", required = false, defaultValue = "") HateCrimeTypeEnum hateCrime) {
        return service.getAll(hateCrime);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponseDTO getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponseDTO create(@RequestBody @Valid ReportRequestDTO requestDTO)  {
        return service.create(requestDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam(name = "id") UUID id, @RequestParam(name = "identity") String identity){
        service.delete(id, identity);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private ReportResponseDTO edit(@RequestBody @Valid ReportRequestDTO request, @RequestParam(name = "id") UUID id){
        return service.edit(request, id);
    }
}
