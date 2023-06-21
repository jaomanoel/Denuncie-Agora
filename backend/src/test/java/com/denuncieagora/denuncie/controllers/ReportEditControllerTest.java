package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.exceptions.*;
import com.denuncieagora.denuncie.domain.services.ReportService;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportEditControllerTest extends ApplicationConfigTest {

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
    public void edit_report_success() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenReturn(new ReportResponseDTO());

        mockMvc.perform(put("/reports")
                .param("id", uuid.toString())
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void edit_report_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenThrow(new ReportNotFoundException("Id is invalid!"));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(put("/reports")
                    .param("id", uuid.toString())
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        });

        Assertions.assertNotNull(exception);

        Throwable rootCause = exception.getCause();

        Assertions.assertTrue(rootCause instanceof ReportNotFoundException);
        Assertions.assertEquals(rootCause.getMessage(), "Id is invalid!");
    }

    @Test
    public void edit_report_without_identity_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity("");
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenThrow(new IdentityInvalidException("Identity is invalid!"));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(put("/reports")
                    .param("id", uuid.toString())
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        });

        Assertions.assertNotNull(exception);

        Throwable rootCause = exception.getCause();

        Assertions.assertTrue(rootCause instanceof IdentityInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "Identity is invalid!");
    }

    @Test
    public void edit_report_date_future_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(uuid.toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.parse("2040-05-01"));

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenThrow(new DateInvalidException("The date is invalid, the date cannot be greater than the current date."));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(put("/reports")
                    .param("id", uuid.toString())
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        });

        Assertions.assertNotNull(exception);

        Throwable rootCause = exception.getCause();

        Assertions.assertTrue(rootCause instanceof DateInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "The date is invalid, the date cannot be greater than the current date.");
    }

    @Test
    public void edit_report_without_state_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(uuid.toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenThrow(new StateInvalidException("State is invalid."));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(put("/reports")
                    .param("id", uuid.toString())
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        });

        Assertions.assertNotNull(exception);

        Throwable rootCause = exception.getCause();

        Assertions.assertTrue(rootCause instanceof StateInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "State is invalid.");
    }

    @Test
    public void edit_report_without_city_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("");
        requestDTO.setIdentity(uuid.toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenThrow(new CityInvalidException("City is invalid."));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(put("/reports")
                    .param("id", uuid.toString())
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        });

        Assertions.assertNotNull(exception);

        Throwable rootCause = exception.getCause();

        Assertions.assertTrue(rootCause instanceof CityInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "City is invalid.");
    }

    @Test
    public void edit_report_with_description_short_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(uuid.toString());
        requestDTO.setDescription("test");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.edit(requestDTO, uuid)).thenThrow(new DescriptionInvalidException("The description is short, the minimum is 200 characters."));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(put("/reports")
                    .param("id", uuid.toString())
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        });

        Assertions.assertNotNull(exception);

        Throwable rootCause = exception.getCause();

        Assertions.assertTrue(rootCause instanceof DescriptionInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "The description is short, the minimum is 200 characters.");
    }
}
