package com.mercadolibre.mutant_detector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutant_detector.model.Stats;
import com.mercadolibre.mutant_detector.service.MutantDetector;

@RestController
public class StatsController {
	
	@Autowired
	private MutantDetector mutantDetector;
	
	// Obtener estadisticas de los mutantes / no mutantes generando un nuevo objeto
	@GetMapping("/stats")
    public ResponseEntity<?> getStats() {
  	  Stats stats = mutantDetector.getStats();
        return ResponseEntity.ok(stats);
  }
	

}
