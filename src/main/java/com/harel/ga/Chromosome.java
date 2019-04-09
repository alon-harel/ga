package com.harel.ga;

import java.util.List;

public interface Chromosome<T extends Gene> {
    List<T> getGenes();

    double getFitnessScore();

    void setFitnessScore(double fitnessScore);
}

