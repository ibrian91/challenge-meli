package com.mercadolibre.mutant_detector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutant_detector.model.DnaRequest;
import com.mercadolibre.mutant_detector.model.Stats;
import com.mercadolibre.mutant_detector.service.MutantDetector;

@RestController
@RequestMapping("/mutant")

public class MutantController {
	
	@Autowired
	private MutantDetector mutantDetector;
	
    @PostMapping
    public ResponseEntity<?> isMutant(@RequestBody DnaRequest dnaRequest) {
        if (MutantDetector.isMutant(dnaRequest.getDna())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // GET endpoint para obtener estadísticas
    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
    	  Stats stats = mutantDetector.getStats();
          return ResponseEntity.ok(stats);
    }
	

}
