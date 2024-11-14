package com.mercadolibre.mutant_detector.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolibre.mutant_detector.model.Stats;
import com.mercadolibre.mutant_detector.model.StatsEntity;
import com.mercadolibre.mutant_detector.repository.StatsRepository;

class StatsServiceTest {

    @InjectMocks
    private MutantDetector mutantDetector;

    @Mock
    private StatsRepository statsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStats_shouldReturnCorrectStats() {
        StatsEntity statsEntity = new StatsEntity(10, 100, 0.1f);
        when(statsRepository.findById(1L)).thenReturn(java.util.Optional.of(statsEntity));

        Stats stats = mutantDetector.getStats();

        assertEquals(10, stats.getCount_mutant_dna());
        assertEquals(100, stats.getCount_human_dna());
        assertEquals(0.1f, stats.getRatio(), 0.001);
    }
}