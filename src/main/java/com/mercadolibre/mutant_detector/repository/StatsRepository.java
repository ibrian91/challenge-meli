package com.mercadolibre.mutant_detector.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mercadolibre.mutant_detector.model.StatsEntity;


public interface StatsRepository extends JpaRepository<StatsEntity, Long> {
}
