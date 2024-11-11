package com.mercadolibre.mutant_detector.model;

public class Stats {
    private int mutants;
    private int humans;
    private float ratio;

    public Stats(int mutants, int humans, float ratio) {
        this.mutants = mutants;
        this.humans = humans;
        this.ratio = ratio;
    }

    public int getMutants() {
        return mutants;
    }

    public int getHumans() {
        return humans;
    }

    public float getRatio() {
        return ratio;
    }
}
