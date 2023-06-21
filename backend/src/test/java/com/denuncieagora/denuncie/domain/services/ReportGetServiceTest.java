package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.exceptions.ReportNotFoundException;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class ReportGetServiceTest extends ApplicationConfigTest {

    @InjectMocks
    ReportService service;

    @Mock
    ReportRepository repository;

    @Spy
    ValidateReportData validate;

    @Test
    public void getAll_report_without_about_success() {
        Report report1 = new Report();
        report1.setId(UUID.randomUUID());
        report1.setIdentity(UUID.randomUUID().toString());
        report1.setDate(LocalDate.now());
        report1.setAbout(HateCrimeTypeEnum.RACISMO);

        List<Report> reports = new ArrayList<>();
        reports.add(report1);
        reports.add(report1);

        Page<Report> reportPage = new PageImpl<>(reports);

        when(repository.findAll(PageRequest.of(0, 5, Sort.by("date").descending())))
                .thenReturn(reportPage);

        List<ReportResponseDTO> response = service.getAll(null);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.size());
    }

    @Test
    public void getAll_report_with_about_success() {
        Report report = new Report();
        report.setId(UUID.randomUUID());
        report.setIdentity(UUID.randomUUID().toString());
        report.setDate(LocalDate.now());
        report.setAbout(HateCrimeTypeEnum.RACISMO);
        report.setState("Santa Catarina");
        report.setCity("Tubarao");
        report.setDescription("test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test");

        List<Report> reports = new ArrayList<>();
        reports.add(report);
        reports.add(report);

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("date").descending());

        when(repository.findByAbout(HateCrimeTypeEnum.RACISMO, pageRequest)).thenReturn(reports);

        List<ReportResponseDTO> response = service.getAll(HateCrimeTypeEnum.RACISMO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.size());
    }

    @Test
    public void get_by_id_report_success() {
        UUID uuid = UUID.randomUUID();

        Report report = new Report();
        report.setId(uuid);

        when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(report));

        ReportResponseDTO response = service.getById(uuid);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(report.getId(), uuid);
    }

    @Test
    public void get_by_id_report_failure() {
        UUID uuid = UUID.randomUUID();

        doAnswer(invocation -> {
            throw new ReportNotFoundException("id = " + uuid + " not found!");
        }).when(repository).findById(uuid);

        Exception exception = Assertions.assertThrows(ReportNotFoundException.class, () -> {
            ReportResponseDTO response = service.getById(uuid);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("id = " + uuid + " not found!", exception.getMessage());
    }
}
