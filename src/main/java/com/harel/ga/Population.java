package com.harel.ga;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private final List<Chromosome> chromosomes = new ArrayList<>();

    public static Population init(int populationSize, ChromosomeFactory chromosomeFactory) {
        Population population = new Population();
        for (int index = 0; index < populationSize; index++) {
            population.addChromosome(chromosomeFactory.create());
        }

        return population;
    }

    public static Population emptyPopulation() {
        return new Population();
    }

    public void addChromosome(Chromosome chromosome) {
        chromosomes.add(chromosome);
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }
}
