package com.mercadolibre.mutant_detector.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant_detector.model.StatsEntity;

import jakarta.annotation.PostConstruct;

@Service
public class DatabaseInitializer {

    @Autowired
    private StatsRepository statsRepository;

    @PostConstruct
    public void init() {
        if (!statsRepository.existsById(1L)) {
            StatsEntity initialStats = new StatsEntity(0, 0, 0);
            statsRepository.save(initialStats);
        }
    }
}
