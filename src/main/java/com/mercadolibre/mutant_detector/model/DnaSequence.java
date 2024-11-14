package com.mercadolibre.mutant_detector.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DnaSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Clave primaria

    private String sequence;  // Secuencia de ADN
    private String status;    // 'mutant' o 'human'

    // Constructor sin parámetros
    public DnaSequence() {}

    // Constructor con parámetros
    public DnaSequence(String sequence, String status) {
        this.sequence = sequence;
        this.status = status;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}