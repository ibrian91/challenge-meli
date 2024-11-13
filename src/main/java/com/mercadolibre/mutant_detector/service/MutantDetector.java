package com.mercadolibre.mutant_detector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant_detector.model.CharBase;
import com.mercadolibre.mutant_detector.model.Stats;
import com.mercadolibre.mutant_detector.model.StatsEntity;
import com.mercadolibre.mutant_detector.repository.StatsRepository;

@Service
public class MutantDetector {
	
	@Autowired
    private StatsRepository statsRepository;
	
	//private static int count_mutant_dna = 0;
	//private static int count_human_dna 	= 0;
	
	public boolean isMutant (String[] dna) {
		int n = dna.length;
        char[][] dnaMatrix = new char[n][n];
        boolean isMutant = false;
        
        //convertimos en char el array de strings
        convertChar(dna, n, dnaMatrix);

        // buscamos la secuencia (dna)
        isMutant = searchSequence(n, dnaMatrix, isMutant);
        
        if (isMutant) {
            incrementMutantCount();
        } else {
            incrementHumanCount();
        }
        
        return isMutant; 
	}

	private void incrementMutantCount() {
        StatsEntity stats = statsRepository.findById(1L).orElse(new StatsEntity());
        stats.setCountMutantDna(stats.getCountMutantDna() + 1);
        stats.setRatio(calculateRatio(stats));
        statsRepository.save(stats);
    }

    private void incrementHumanCount() {
        StatsEntity stats = statsRepository.findById(1L).orElse(new StatsEntity());
        stats.setCountHumanDna(stats.getCountHumanDna() + 1);
        stats.setRatio(calculateRatio(stats));
        statsRepository.save(stats);
    }

	private static boolean searchSequence(int n, char[][] dnaMatrix, boolean isMutant) {
	    int contador = 0;
	    int i = 0;
	    boolean foundMutantSequence = false;

	    // RECORREMOS CON WHILE EN LUGAR DE USAR DOBLE FOR PARA SALIR DE LA ITERACION CUANDO SE CUMPLA LA CONDICION.
	    // Buscamos secuencias horizontales, verticales y diagonales
	    while (i < n && !foundMutantSequence) {
	        int j = 0;
	        
	        while (j < n && !foundMutantSequence) {
	            // Solamente analizamos los valores permitidos A, C, G
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
		// convierto el array de string en un array de caracteres
        for (int i = 0; i < n; i++) {
            dnaMatrix[i] = dna[i].toCharArray();
        }
	}

	private static boolean tieneSecuencia(char[][] dnaMatrix, int fila, int columna) {
		  int n = dnaMatrix.length;
	      char letter = dnaMatrix[fila][columna];

	      // Verificar secuencia horizontal
	      if (columna + 3 < n &&
	          dnaMatrix[fila][columna + 1] == letter &&
	          dnaMatrix[fila][columna + 2] == letter &&
	          dnaMatrix[fila][columna + 3] == letter) {
	            return true;
	        }

	        // Verificar secuencia vertical
	      if (fila + 3 < n &&
	            dnaMatrix[fila + 1][columna] == letter &&
	            dnaMatrix[fila + 2][columna] == letter &&
	            dnaMatrix[fila + 3][columna] == letter) {
	            return true;
	        }

	        // Verificar secuencia diagonal hacia la derecha
	       if (fila + 3 < n && columna + 3 < n &&
	            dnaMatrix[fila + 1][columna + 1] == letter &&
	            dnaMatrix[fila + 2][columna + 2] == letter &&
	            dnaMatrix[fila + 3][columna + 3] == letter) {
	            return true;
	        }

	        // Verificar secuencia diagonal hacia la izquierda
	       if (fila + 3 < n && columna - 3 >= 0 &&
	            dnaMatrix[fila + 1][columna - 1] == letter &&
	            dnaMatrix[fila + 2][columna - 2] == letter &&
	            dnaMatrix[fila + 3][columna - 3] == letter) {
	            return true;
	        }

	        return false;
	}

	
	private static float calculateRatio(StatsEntity stats) {
        if (stats.getCountHumanDna() == 0) {
            return 0;
        }
        return (float) stats.getCountMutantDna() / stats.getCountHumanDna();
    }
	
	public Stats getStats() {
        StatsEntity statsEntity = statsRepository.findById(1L).orElse(new StatsEntity());
        return new Stats(statsEntity.getCountMutantDna(), statsEntity.getCountHumanDna(), statsEntity.getRatio());
    }

}
