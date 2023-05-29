package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {
}
