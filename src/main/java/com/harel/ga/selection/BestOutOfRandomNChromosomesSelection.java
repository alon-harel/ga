package com.harel.ga.selection;

import com.harel.ga.Chromosome;
import com.harel.ga.utils.RandomNumberProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BestOutOfRandomNChromosomesSelection implements SelectionMethodology {

    private final int chromosomesToSelect;
    private final RandomNumberProvider randomNumberProvider;
    private List<Chromosome> chromosomes;

    public BestOutOfRandomNChromosomesSelection(int chromosomesToSelect,
                                                RandomNumberProvider randomNumberProvider) {
        this.chromosomesToSelect = chromosomesToSelect;
        this.randomNumberProvider = randomNumberProvider;
    }

    @Override
    public void init(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    @Override
    public Chromosome pick() {
        List<Chromosome> selectedRandomChromosomes = new ArrayList<>();
        for (int index = 0; index < chromosomesToSelect; index++) {
            int chromosomeIndex = randomNumberProvider.random(chromosomes.size());
            selectedRandomChromosomes.add(chromosomes.get(chromosomeIndex));
        }

        return Collections.max(selectedRandomChromosomes,
                Comparator.comparing(Chromosome::getFitnessScore));
    }
}
