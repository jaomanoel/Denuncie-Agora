package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.exceptions.IdentityInvalidException;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Optional;
import java.util.UUID;

public class ReportDeleteServiceTest extends ApplicationConfigTest {
    @InjectMocks
    ReportService service;

    @Mock
    ReportRepository repository;

    @Spy
    ValidateReportData validate;

    @Test
    public void delete_report_success() {
        UUID uuid = UUID.randomUUID();

        Report report = new Report();
        report.setIdentity(uuid.toString());

        Mockito.doNothing().when(repository).delete(Mockito.any(Report.class));

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(report));

        Assertions.assertDoesNotThrow(() -> {
            service.delete(uuid, uuid.toString());
        });
    }

    @Test
    public void delete_report_failure() {
        UUID uuid = UUID.randomUUID();

        Report report = new Report();

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(report));

        Assertions.assertThrows(NullPointerException.class, () -> {
            service.delete(uuid, uuid.toString());
        });
    }

    @Test
    public void delete_report_identity_invalid_failure() {
        UUID uuid = UUID.randomUUID();

        Report report = new Report();
        report.setIdentity(uuid.toString());

        Assertions.assertThrows(IdentityInvalidException.class, () -> {
            service.delete(uuid, "");
        });
    }

    @Test
    public void delete_report_identity_not_equals_failure() {
        UUID uuid = UUID.randomUUID();

        Report report = new Report();
        report.setIdentity(UUID.randomUUID().toString());

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(report));

        Assertions.assertThrows(IdentityInvalidException.class, () -> {
            service.delete(uuid, uuid.toString());
        });
    }
}
