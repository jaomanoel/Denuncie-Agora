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

        Mockito.when(service.create(requestDTO)).thenThrow(new IdentityInvalidException("Identity is invalid!"));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isBadRequest());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof IdentityInvalidException);
        Assertions.assertEquals("Identity is invalid!", rootCause.getMessage());
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

        Mockito.when(service.create(requestDTO)).thenThrow(new DescriptionInvalidException("The description is short, the minimum is 200 characters"));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isBadRequest());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof DescriptionInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "The description is short, the minimum is 200 characters");
    }

    @Test
    public void create_report_with_future_date_failure() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("test testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest testtest test");
        requestDTO.setDate(LocalDate.parse("2040-12-03"));

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new DateInvalidException("The date is invalid, the date cannot be greater than the current date."));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isBadRequest());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof DateInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "The date is invalid, the date cannot be greater than the current date.");
    }

    @Test
    public void create_report_without_state_failure() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new StateInvalidException("State is invalid."));

        Throwable exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isBadRequest());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof StateInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "State is invalid.");
    }

    @Test
    public void create_report_without_city_failure() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new CityInvalidException("City is invalid."));

        Exception exception = Assertions.assertThrows(ServletException.class, () ->  {
            mockMvc.perform(post("/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isBadRequest());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof CityInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "City is invalid.");
    }

    @Test
    public void create_report_without_about_failure() throws Exception {

        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(0);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        Mockito.when(service.create(requestDTO)).thenThrow(new AboutInvalidException("This kind of hate crime is not valid."));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isBadRequest());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof AboutInvalidException);
        Assertions.assertEquals(rootCause.getMessage(), "This kind of hate crime is not valid.");
    }
}
