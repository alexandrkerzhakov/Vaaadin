package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.Claimant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimantRepository extends JpaRepository<Claimant, Long> {
}
