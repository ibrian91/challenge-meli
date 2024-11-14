package com.mercadolibre.mutant_detector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant_detector.repository.DnaSequenceRepository;
import com.mercadolibre.mutant_detector.repository.StatsRepository;
import com.mercadolibre.mutant_detector.model.CharBase;
import com.mercadolibre.mutant_detector.model.DnaSequence;
import com.mercadolibre.mutant_detector.model.Stats;
import com.mercadolibre.mutant_detector.model.StatsEntity;

@Service
public class MutantDetector {

    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    @Autowired
    private StatsRepository statsRepository;

    public boolean isMutant(String[] dna) {
        // Convertir la secuencia de ADN en un string (puedes usar otro formato si lo prefieres)
        String sequence = String.join(",", dna);

        // Verificar si la secuencia ya existe en la base de datos
        if (dnaSequenceRepository.existsBySequence(sequence)) {
            // Si ya existe, simplemente recuperamos el estado
            DnaSequence existingSequence = dnaSequenceRepository.findBySequence(sequence);
            return existingSequence.getStatus().equals("mutant");
        }

        // Lógica para determinar si el ADN es mutante o no
        boolean isMutant = detectMutant(dna);

        // Almacenamos la secuencia en la base de datos si no estaba previamente
        DnaSequence newDnaSequence = new DnaSequence(sequence, isMutant ? "mutant" : "human");
        dnaSequenceRepository.save(newDnaSequence);

        // Actualizamos los contadores de mutantes y humanos
        if (isMutant) {
            incrementMutantCount();
        } else {
            incrementHumanCount();
        }

        return isMutant;
    }

    private boolean detectMutant(String[] dna) {
        int n = dna.length;
        char[][] dnaMatrix = new char[n][n];
        boolean isMutant = false;
        
        // Convertimos en char el array de strings
        convertChar(dna, n, dnaMatrix);

        // Buscamos la secuencia (dna)
        isMutant = searchSequence(n, dnaMatrix, isMutant);
        
        return isMutant;
    }

    private static boolean searchSequence(int n, char[][] dnaMatrix, boolean isMutant) {
        int contador = 0;
        int i = 0;
        boolean foundMutantSequence = false;

        // Buscamos secuencias horizontales, verticales y diagonales
        while (i < n && !foundMutantSequence) {
            int j = 0;
            
            while (j < n && !foundMutantSequence) {
                if (valoresPermitidos(dnaMatrix[i][j])) {
                    if (tieneSecuencia(dnaMatrix, i, j)) {
                        contador++;
                        if (contador > 1) { 
                            isMutant = true;
                            foundMutantSequence = true;
                        }
                    }
                }
                j++;
            }
            
            i++;
        }
        
        return isMutant;
    }

    private static boolean valoresPermitidos(char letra) {
        try {
            CharBase.valueOf(String.valueOf(letra));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static void convertChar(String[] dna, int n, char[][] dnaMatrix) {
        // Convertir el array de string en un array de caracteres
        for (int i = 0; i < n; i++) {
            dnaMatrix[i] = dna[i].toCharArray();
        }
    }

    private static boolean tieneSecuencia(char[][] dnaMatrix, int fila, int columna) {
        int n = dnaMatrix.length;
        char letter = dnaMatrix[fila][columna];

        // Verificar secuencias horizontales, verticales, diagonales
        if (columna + 3 < n &&
            dnaMatrix[fila][columna + 1] == letter &&
            dnaMatrix[fila][columna + 2] == letter &&
            dnaMatrix[fila][columna + 3] == letter) {
            return true;
        }

        if (fila + 3 < n &&
            dnaMatrix[fila + 1][columna] == letter &&
            dnaMatrix[fila + 2][columna] == letter &&
            dnaMatrix[fila + 3][columna] == letter) {
            return true;
        }

        if (fila + 3 < n && columna + 3 < n &&
            dnaMatrix[fila + 1][columna + 1] == letter &&
            dnaMatrix[fila + 2][columna + 2] == letter &&
            dnaMatrix[fila + 3][columna + 3] == letter) {
            return true;
        }

        if (fila + 3 < n && columna - 3 >= 0 &&
            dnaMatrix[fila + 1][columna - 1] == letter &&
            dnaMatrix[fila + 2][columna - 2] == letter &&
            dnaMatrix[fila + 3][columna - 3] == letter) {
            return true;
        }

        return false;
    }

    private void incrementMutantCount() {
        StatsEntity stats = statsRepository.findById(1L).orElse(new StatsEntity(0, 0, 0));
        stats.setCountMutantDna(stats.getCountMutantDna() + 1);
        stats.setRatio((float) calculateRatio(stats)); // Actualiza la razón
        statsRepository.save(stats);
    }

    private void incrementHumanCount() {
        StatsEntity stats = statsRepository.findById(1L).orElse(new StatsEntity(0, 0, 0));
        stats.setCountHumanDna(stats.getCountHumanDna() + 1);
        stats.setRatio((float) calculateRatio(stats)); // Actualiza la razón
        statsRepository.save(stats);
    }

    private double calculateRatio(StatsEntity stats) {
        // Lógica para calcular la razón
        return (double) stats.getCountMutantDna() / (stats.getCountHumanDna() + stats.getCountMutantDna());
    }

public Stats getStats() {
        StatsEntity statsEntity = statsRepository.findById(1L).orElse(new StatsEntity());
        return new Stats(statsEntity.getCountMutantDna(), statsEntity.getCountHumanDna(), statsEntity.getRatio());
    }

}