package com.harel.ga.tsp;

import com.harel.ga.Chromosome;
import com.harel.ga.mutation.MutationService;
import com.harel.ga.utils.RandomNumberProvider;

import java.util.Collections;
import java.util.List;

public class TspMutationService implements MutationService<Route> {
    private final RandomNumberProvider randomNumberProvider;

    public TspMutationService(RandomNumberProvider randomNumberProvider) {
        this.randomNumberProvider = randomNumberProvider;
    }

    @Override
    public Chromosome mutate(Route chromosome) {
        final List<City> cities = chromosome.getGenes();
        int firstIndex = randomNumberProvider.random(cities.size());
        int secondIndex = randomNumberProvider.random(cities.size());
        Collections.swap(cities, firstIndex, secondIndex);

        return chromosome;
    }


}
