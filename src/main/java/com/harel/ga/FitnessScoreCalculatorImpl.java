package com.harel.ga;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public abstract class FitnessScoreCalculatorImpl<C extends Chromosome> implements FitnessScoreCalculator<C> {

    @Override
    public void calc(List<C> chromosomes) {
        chromosomes.forEach(this::calc);
        logHighestScore(chromosomes);
    }

    abstract protected void calc(C chromosome);

    private void logHighestScore(List<C> chromosomes) {
         C chromosome = chromosomes
                .stream()
                .max(Comparator.comparing(Chromosome::getFitnessScore))
                .orElseThrow(NoSuchElementException::new);

         log.info("Best chromosome is: " + chromosome.toString());
    }
}
