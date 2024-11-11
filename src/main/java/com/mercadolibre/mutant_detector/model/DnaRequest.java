package com.mercadolibre.mutant_detector.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa una solicitud de ADN.
 * Almacena una secuencia de ADN como un array de strings.
 */
public class DnaRequest {

    private String[] dna;

    // Constructor sin argumentos
    public DnaRequest() {
    }

    // Constructor con @JsonCreator y @JsonProperty para deserialización
    @JsonCreator
    public DnaRequest(@JsonProperty("dna") String[] dna) {
        this.dna = dna;
    }

    // Getter y Setter para dna
    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}