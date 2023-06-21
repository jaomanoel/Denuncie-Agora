package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.exceptions.ReportNotFoundException;
import com.denuncieagora.denuncie.domain.services.ReportService;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportGetControllerTest extends ApplicationConfigTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    ReportService service;

    @InjectMocks
    ReportController controller;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void report_get_all() throws Exception {
        mockMvc.perform(get("/reports").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void report_get_by_id_success() throws Exception {
        UUID uuid = UUID.randomUUID();

        ReportResponseDTO response = new ReportResponseDTO();
        response.setAbout(HateCrimeTypeEnum.RACISMO);
        response.setCity("Tubarao");
        response.setDate(LocalDate.now());
        response.setIdentity(UUID.randomUUID().toString());
        response.setState("Sao Paulo");
        response.setDescription("teste stes teste teste stes teste teste stes teste teste stes teste teste stes teste teste stes teste teste stes teste teste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes testeteste stes teste ");

        Mockito.when(service.getById(uuid)).thenReturn(response);

        mockMvc.perform(get("/reports/{id}", uuid.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void report_get_by_id_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.when(service.getById(uuid)).thenThrow(new ReportNotFoundException("id = " + uuid + " not found!"));

        Exception exception = Assertions.assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/reports/{id}", uuid.toString()))
                    .andExpect(status().isNotFound());
        });

        Throwable rootCause = exception.getCause();
        Assertions.assertTrue(rootCause instanceof ReportNotFoundException);
        Assertions.assertEquals("id = " + uuid + " not found!", rootCause.getMessage());
    }
}
