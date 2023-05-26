package com.denuncieagora.denuncie.domain.repositories;

import com.denuncieagora.denuncie.domain.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
}
