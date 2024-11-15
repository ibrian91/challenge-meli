package com.mercadolibre.mutant_detector.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant_detector.model.StatsEntity;
import com.mercadolibre.mutant_detector.service.MutantDetector;

import jakarta.annotation.PostConstruct;

@Service
public class DatabaseInitializer {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private MutantDetector mutantDetector;

    @PostConstruct
    public void init() {
        // Verifica si ya existe el registro de estadísticas
        StatsEntity stats = statsRepository.findById(1L).orElseGet(() -> {
            StatsEntity initialStats = new StatsEntity(0, 0, 0);
            statsRepository.save(initialStats);
            return initialStats;
        });

        // Sincroniza los contadores locales en MutantDetector
        mutantDetector.setCountMutantDna(stats.getCountMutantDna());
        mutantDetector.setCountHumanDna(stats.getCountHumanDna());
    }
}
