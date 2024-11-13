package com.mercadolibre.mutant_detector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadolibre.mutant_detector.service.MutantDetector;

@SpringBootApplication
public class MutantAplicattion {
	
	 @Autowired
	    private static MutantDetector mutantDetector; // Inyectamos la instancia de MutantDetector

	public static void main(String[] args) {
		SpringApplication.run(MutantAplicattion.class, args);
		
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

	        System.out.println("Is mutant? " + mutantDetector.isMutant(dnaMutant)); // Debería imprimir: true
	        System.out.println("Is mutant? " + mutantDetector.isMutant(dnaMutant2)); // Debería imprimir: false
	        System.out.println("Is mutant? " + mutantDetector.isMutant(dnaNonMutant)); // Debería imprimir: false
	   
		
	}

}
