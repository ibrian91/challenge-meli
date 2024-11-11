package com.mercadolibre.mutant_detector.service;

import org.springframework.stereotype.Service;

import com.mercadolibre.mutant_detector.model.CharBase;
import com.mercadolibre.mutant_detector.model.Stats;

@Service
public class MutantDetector {
	
	private static int mutantCount = 0;
	private static int humanCount 	= 0;
	
	public static boolean isMutant (String[] dna) {
		int n = dna.length;
        char[][] dnaMatrix = new char[n][n];
        boolean isMutant = false;
        
        //convertimos en char el array de strings
        convertChar(dna, n, dnaMatrix);

        // buscamos la secuencia (dna)
        isMutant = searchSequence(n, dnaMatrix, isMutant);
        
        if (isMutant) {
            mutantCount++;
        } else {
            humanCount++;
        }
        
        return isMutant; 
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

	
	public Stats getStats() {
		float ratio =0;
		if (humanCount !=0) {
			 ratio = (float) mutantCount / humanCount;
		}
		
		return new Stats(mutantCount,humanCount,ratio);
	}

}
