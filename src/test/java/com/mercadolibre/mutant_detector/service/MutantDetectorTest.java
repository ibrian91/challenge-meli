package com.mercadolibre.mutant_detector.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolibre.mutant_detector.repository.DnaSequenceRepository;
import com.mercadolibre.mutant_detector.repository.StatsRepository;


class MutantDetectorTest {

    @InjectMocks
    private MutantDetector mutantDetector;

    @Mock
    private DnaSequenceRepository dnaSequenceRepository;

    @Mock
    private StatsRepository statsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsMutant_withMutantDna_shouldReturnTrue() {
        String[] mutantDna = {"AAAA", "CCCC", "GGGG", "TTTT"};
        when(dnaSequenceRepository.existsBySequence(anyString())).thenReturn(false);
        
        boolean result = mutantDetector.isMutant(mutantDna);
        
        assertTrue(result);
    }

    @Test
    void testIsMutant_withHumanDna_shouldReturnFalse() {
        String[] humanDna = {"ACGT", "TGCA", "CAGT", "TGAC"};
        when(dnaSequenceRepository.existsBySequence(anyString())).thenReturn(false);

        boolean result = mutantDetector.isMutant(humanDna);
        
        assertFalse(result);
    }
}