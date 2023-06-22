package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.dtos.responses.CityResponseDTO;
import com.denuncieagora.denuncie.dtos.responses.StateResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://servicodados.ibge.gov.br/api/v1/localidades/", name = "ibge")
public interface IbgeService {

    @GetMapping("estados")
    List<StateResponseDTO> getAllState();

    @GetMapping("estados/{id}/regioes-imediatas")
    List<CityResponseDTO> getCitiesByState(@PathVariable("id") Long id);
}
