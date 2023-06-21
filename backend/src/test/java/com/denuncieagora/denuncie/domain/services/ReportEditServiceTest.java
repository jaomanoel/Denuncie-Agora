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
import java.util.Optional;
import java.util.UUID;

public class ReportEditServiceTest extends ApplicationConfigTest {

    @InjectMocks
    ReportService service;

    @Mock
    ReportRepository repository;

    @Spy
    ValidateReportData validate;

    @Test
    public void edit_report_success() {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO request = new ReportRequestDTO();
        request.setDate(LocalDate.now());
        request.setIdentity(uuid.toString());
        request.setAbout(1);
        request.setCity("Tubarao");
        request.setState("Santa Catarina");
        request.setDescription("Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test ");

        Report report = new Report();
        report.setId(uuid);
        report.setIdentity(uuid.toString());

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(report));

        Mockito.when(repository.save(Mockito.any())).thenReturn(report);

        ReportResponseDTO response = service.edit(request, uuid);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(response.getCity(), request.getCity());
    }

    @Test
    public void edit_report_failure() {
        ReportRequestDTO request = new ReportRequestDTO();

        Assertions.assertThrows(NullPointerException.class, () -> {
            service.edit(request, null);
        });
    }

    @Test
    public void edit_report_without_identity_failure() {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO request = new ReportRequestDTO();
        request.setDate(LocalDate.now());
        request.setIdentity(UUID.randomUUID().toString());
        request.setAbout(1);
        request.setCity("Tubarao");
        request.setState("Santa Catarina");
        request.setDescription("Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test ");

        Report report = new Report();
        report.setId(uuid);
        report.setIdentity(uuid.toString());

        Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(report));

        Assertions.assertThrows(IdentityInvalidException.class, () -> {
            service.edit(request, uuid);
        });
    }

    @Test
    public void edit_report_with_date_invalid_failure() {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO request = new ReportRequestDTO();
        request.setDate(LocalDate.parse("2040-04-01"));
        request.setIdentity(uuid.toString());
        request.setAbout(1);
        request.setCity("Tubarao");
        request.setState("Santa Catarina");
        request.setDescription("Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test ");

        Report report = new Report();
        report.setId(uuid);
        report.setIdentity(uuid.toString());

        Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(report));

        Assertions.assertThrows(DateInvalidException.class, () -> {
            service.edit(request, uuid);
        });
    }

    @Test
    public void edit_report_with_description_short_failure() {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO request = new ReportRequestDTO();
        request.setDate(LocalDate.now());
        request.setIdentity(uuid.toString());
        request.setAbout(1);
        request.setCity("Tubarao");
        request.setState("Santa Catarina");
        request.setDescription("Test.");

        Report report = new Report();
        report.setId(uuid);
        report.setIdentity(uuid.toString());

        Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(report));

        Assertions.assertThrows(DescriptionInvalidException.class, () -> {
            service.edit(request, uuid);
        });
    }

    @Test
    public void edit_report_without_state_failure() {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO request = new ReportRequestDTO();
        request.setDate(LocalDate.now());
        request.setIdentity(uuid.toString());
        request.setAbout(1);
        request.setCity("Tubarao");
        request.setState("");
        request.setDescription("Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test.");

        Report report = new Report();
        report.setId(uuid);
        report.setIdentity(uuid.toString());

        Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(report));

        Assertions.assertThrows(StateInvalidException.class, () -> {
            service.edit(request, uuid);
        });
    }

    @Test
    public void edit_report_without_city_failure() {
        UUID uuid = UUID.randomUUID();

        ReportRequestDTO request = new ReportRequestDTO();
        request.setDate(LocalDate.now());
        request.setIdentity(uuid.toString());
        request.setAbout(1);
        request.setCity("");
        request.setState("Santa Catarina");
        request.setDescription("Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test Test test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test test test Test test.");

        Report report = new Report();
        report.setId(uuid);
        report.setIdentity(uuid.toString());

        Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(report));

        Assertions.assertThrows(CityInvalidException.class, () -> {
            service.edit(request, uuid);
        });
    }
}
