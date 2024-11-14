package com.mercadolibre.mutant_detector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.mutant_detector.model.DnaSequence;

@Repository
public interface DnaSequenceRepository extends JpaRepository<DnaSequence, Long> {

   
    boolean existsBySequence(String sequence);

    
    DnaSequence findBySequence(String sequence);
}