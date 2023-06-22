package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.domain.services.IbgeService;
import com.denuncieagora.denuncie.dtos.responses.CityResponseDTO;
import com.denuncieagora.denuncie.dtos.responses.StateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ibge")
public class IbgeController {

    @Autowired
    private IbgeService service;

    @GetMapping("/states")
    @ResponseStatus(HttpStatus.OK)
    public List<StateResponseDTO> getAll() {
        return service.getAllState();
    }

    @GetMapping("/cities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CityResponseDTO> getCitiesByState(@PathVariable("id") Long id ) {
        return service.getCitiesByState(id);
    }
}
