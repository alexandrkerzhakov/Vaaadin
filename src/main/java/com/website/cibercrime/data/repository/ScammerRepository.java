package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.Scammer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScammerRepository extends JpaRepository<Scammer, Long> {
}
