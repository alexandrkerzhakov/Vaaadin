package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {
//    @Query("select c from CrimeReport c " +
//            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
//            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
//    List<CrimeReport> search(@Param("searchTerm") String searchTerm);
}
