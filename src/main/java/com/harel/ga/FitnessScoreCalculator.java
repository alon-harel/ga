package com.harel.ga;

import java.util.List;

public interface FitnessScoreCalculator<C extends Chromosome> {
    void calc(List<C> chromosomes);
}
