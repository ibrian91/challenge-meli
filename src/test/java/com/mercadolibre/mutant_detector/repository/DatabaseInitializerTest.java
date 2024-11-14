package com.mercadolibre.mutant_detector.repository;

import com.mercadolibre.mutant_detector.model.StatsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseInitializerTest {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private DatabaseInitializer databaseInitializer;

    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada prueba
        statsRepository.deleteAll();
    }

    @Test
    void testDatabaseInitialization() {
        // Asegura que las estadísticas se inicializan si no existen
        databaseInitializer.init();  // Simula la inicialización

        StatsEntity stats = statsRepository.findById(1L).orElse(null);
        assertNotNull(stats);
        assertEquals(0, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(0, stats.getRatio());
    }

    @Test
    void testDatabaseInitializationWhenExists() {
        // Guarda estadísticas iniciales
        StatsEntity initialStats = new StatsEntity(10, 5, 2);
        statsRepository.save(initialStats);

        // Asegura que las estadísticas no se sobrescriban
        databaseInitializer.init();

        StatsEntity stats = statsRepository.findById(1L).orElse(null);
        assertNotNull(stats);
        assertEquals(10, stats.getCountMutantDna());
        assertEquals(5, stats.getCountHumanDna());
        assertEquals(2, stats.getRatio());
    }
}