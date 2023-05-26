package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.domain.services.ReportService;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping
    public List<ReportResponseDTO> getAll() {
        return service.getAll();
    }
}
