package com.mercadolibre.mutant_detector.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolibre.mutant_detector.model.Stats;

@SpringBootTest
@Transactional
class MutantDetectorIntegrationTest {

    @Autowired
    private MutantDetector mutantDetector;

    @Test
    void testIsMutant_shouldPersistData() {
        String[] mutantDna = {"AAAA", "CCCC", "GGGG", "TTTT"};

        boolean result = mutantDetector.isMutant(mutantDna);

        assertTrue(result);
        Stats stats = mutantDetector.getStats();
        assertTrue(stats.getCount_mutant_dna() > 0);
    }
}