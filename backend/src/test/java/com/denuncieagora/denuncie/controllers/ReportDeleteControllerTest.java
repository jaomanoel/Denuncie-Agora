package com.denuncieagora.denuncie.controllers;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

public class ReportDeleteControllerTest extends ApplicationConfigTest {

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
    public void delete_report_success() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.doNothing().when(service).delete(uuid, uuid.toString());

        mockMvc.perform(delete("/reports")
                .param("id", uuid.toString())
                .param("identity", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_report_failure() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.doThrow(new IllegalArgumentException("Identity is required!")).when(service).delete(uuid, "");

        mockMvc.perform(delete("/reports")
                .param("id", uuid.toString())
                .param("identity", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
