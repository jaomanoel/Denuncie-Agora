package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.exceptions.*;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.UUID;

public class ReportCreateServiceTest extends ApplicationConfigTest {

    @InjectMocks
    ReportService service;

    @Mock
    ReportRepository repository;

    @Spy
    ValidateReportData validate;

    @Test
    public void create_report_success() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        Report report = new Report();
        report.setId(UUID.randomUUID());
        Mockito.when(repository.save(Mockito.any())).thenReturn(report);

        ReportResponseDTO response = service.create(requestDTO);

        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void create_report_failure() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();

        Assertions.assertThrows(NullPointerException.class, () -> {
            service.create(requestDTO);
        });
    }

    @Test
    public void create_report_without_identity_failure() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Sao Paulo");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity("");
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        Assertions.assertThrows(IdentityInvalidException.class, () -> {
            service.create(requestDTO);
        });
    }

    @Test
    public void create_report_without_state_failure() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        Assertions.assertThrows(StateInvalidException.class, () -> {
            service.create(requestDTO);
        });
    }

    @Test
    public void create_report_without_city_failure() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete ");
        requestDTO.setDate(LocalDate.now());

        Assertions.assertThrows(CityInvalidException.class, () -> {
            service.create(requestDTO);
        });
    }

    @Test
    public void create_report_with_description_short_failure() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("test");
        requestDTO.setDate(LocalDate.now());

        Assertions.assertThrows(DescriptionInvalidException.class, () -> {
            service.create(requestDTO);
        });
    }

    @Test
    public void create_report_with_date_future_failure() {
        ReportRequestDTO requestDTO = new ReportRequestDTO();
        requestDTO.setAbout(1);
        requestDTO.setState("Santa Catarina");
        requestDTO.setCity("Tubarao");
        requestDTO.setIdentity(UUID.randomUUID().toString());
        requestDTO.setDescription("tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete tete");
        requestDTO.setDate(LocalDate.parse("2040-05-12"));

        Assertions.assertThrows(DateInvalidException.class, () -> {
            service.create(requestDTO);
        });
    }
}
