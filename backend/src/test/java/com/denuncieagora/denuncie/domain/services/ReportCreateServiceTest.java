package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.ApplicationConfigTest;
import com.denuncieagora.denuncie.domain.entities.Report;
import com.denuncieagora.denuncie.domain.enums.HateCrimeTypeEnum;
import com.denuncieagora.denuncie.domain.repositories.ReportRepository;
import com.denuncieagora.denuncie.dtos.requests.ReportRequestDTO;
import com.denuncieagora.denuncie.dtos.responses.ReportResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


public class CreateReportServiceTest extends ApplicationConfigTest {

    @InjectMocks
    ReportService reportService;

    @MockBean
    ReportRepository repository;

    @Test
    public void create_new_report() {
        ReportRequestDTO requestDTO = new ReportRequestDTO(
                1,
                UUID.randomUUID().toString(),
                LocalDate.now(),
                "Santa Catarina",
                "Tubarao",
                "Teste Create Teste CreateTeste CreateTeste CreateTeste Create Teste Create Teste Create Teste Create Teste Create Teste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste CreateTeste Create"
        );

        ReportResponseDTO response = reportService.create(requestDTO);

        Assertions.assertNotNull(response.getId());
    }

    @BeforeEach
    public void createReport() {
        Mockito.when(repository.save(Mockito.any(Report.class))).thenAnswer(r -> r.getArguments()[0]);
    }
}
