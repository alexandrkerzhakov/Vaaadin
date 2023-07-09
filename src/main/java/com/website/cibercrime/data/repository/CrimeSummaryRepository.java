package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.CrimeSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeSummaryRepository extends JpaRepository<CrimeSummary, Long> {
//    @Query("select c from CrimeReport c " +
//            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
//            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
//    List<CrimeReport> search(@Param("searchTerm") String searchTerm);
}
