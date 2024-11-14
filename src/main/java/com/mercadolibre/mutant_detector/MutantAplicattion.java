package com.mercadolibre.mutant_detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadolibre.mutant_detector.service.MutantDetector;

@SpringBootApplication
public class MutantAplicattion {

    @Autowired
    private MutantDetector mutantDetector;  // Eliminamos 'static'

    public static void main(String[] args) {
        SpringApplication.run(MutantAplicattion.class, args);
    }

    // Método adicional para ejecutar la lógica de prueba
    @Autowired
    public void runTest() {
        String[] dnaMutant = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        };
        
        String[] dnaMutant2 = {
            "ATGCGA",
            "AYGTGC",
            "ATOTGT",
            "AGAUZG",
            "PCCCTA",
            "TCACTG"
        };

        String[] dnaNonMutant = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
        };

        System.out.println("Is mutant? " + mutantDetector.isMutant(dnaMutant)); // true
        System.out.println("Is mutant? " + mutantDetector.isMutant(dnaMutant2)); // false
        System.out.println("Is mutant? " + mutantDetector.isMutant(dnaNonMutant)); // false
    }
}