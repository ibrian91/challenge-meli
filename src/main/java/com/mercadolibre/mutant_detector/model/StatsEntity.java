package com.mercadolibre.mutant_detector.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StatsEntity {
	
	
	// Esta clase utiliza JPA para almacenar las estadisticas de adni de mutante - humano
    @Id
    private Long id = 1L; 
    
    private int countMutantDna;
    private int countHumanDna;
    private float ratio;
    
    // constructor por defecto
    public StatsEntity() {
    }

    //constructor parametrizado
    public StatsEntity(int countMutantDna, int countHumanDna, float ratio) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    public int getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(int countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public int getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(int countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
