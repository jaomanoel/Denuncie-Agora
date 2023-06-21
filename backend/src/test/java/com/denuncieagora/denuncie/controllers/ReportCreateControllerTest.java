package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.services.ReportService;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportCreateControllerTest extends ApplicationConfigTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    ReportService service;

    @InjectMocks
    ReportController controller;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void create_report_success() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenReturn(new ReportResponseDTO());

        mockMvc.perform(post("/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void create_report_failure() throws Exception {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new IllegalArgumentException("Report is invalid!"));

        mockMvc.perform(post("/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_report_with_description_short_failure() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new IllegalArgumentException("Description is invalid!"));

        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_report_with_future_date_failure() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete");
        requestDTO.setDate(LocalDate.parse("2040-12-03"));

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new IllegalArgumentException("Date is invalid!"));

        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
