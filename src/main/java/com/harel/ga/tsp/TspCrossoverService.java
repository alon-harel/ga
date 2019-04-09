package com.harel.ga.tsp;

import com.harel.ga.crossover.CrossoverServiceImpl;
import com.harel.ga.utils.RandomNumberProvider;

import java.util.ArrayList;
import java.util.List;

public class TspCrossoverService extends CrossoverServiceImpl<Route> {
    public TspCrossoverService(TspChromosomeFactory chromosomeFactory,
                               RandomNumberProvider randomNumberProvider,
                               double crossoverRate) {
        super(chromosomeFactory, randomNumberProvider, crossoverRate);
    }

    @Override
    protected Offsprings createOffsprings(Route firstChromosome,
                                          Route secondChromosome) {
        int firstDrawIndex = getRandomNumberProvider().random(firstChromosome.getGenes().size());
        int secondDrawIndex = getRandomNumberProvider().random(firstChromosome.getGenes().size());
        int firstCuttingIndex = (firstDrawIndex <= secondDrawIndex) ? firstDrawIndex : secondDrawIndex;
        int secondCuttingIndex = (firstDrawIndex <= secondDrawIndex) ? secondDrawIndex : firstDrawIndex;

        List<City> firstOffSpringCities = getCitiesForRoute(firstChromosome, secondChromosome, firstCuttingIndex, secondCuttingIndex);
        List<City> secondOffSpringCities = getCitiesForRoute(secondChromosome, firstChromosome, firstCuttingIndex, secondCuttingIndex);

        return Offsprings.builder()
                .firstOffspring(getChromosomeFactory().create(firstOffSpringCities))
                .secondOffspring(getChromosomeFactory().create(secondOffSpringCities))
                .build();
    }

    private List<City> getCitiesForRoute(Route routeToBuildOffspringFrom, Route routeToPickCitiesFrom, int firstCuttingIndex, int secondCuttingIndex) {
        List<City> offspringCities = new ArrayList<>(routeToBuildOffspringFrom.getGenes().subList(
                firstCuttingIndex, secondCuttingIndex + 1));
        addCitiesBeforeCuttingIndex(offspringCities, firstCuttingIndex, routeToPickCitiesFrom);
        addCitiesAfterCuttingIndex(offspringCities, secondCuttingIndex, routeToPickCitiesFrom);

        return offspringCities;
    }

    private void addCitiesAfterCuttingIndex(List<City> offspring, int secondCuttingIndex, Route routeToPickCitiesFrom) {
        for (int index = secondCuttingIndex; index < routeToPickCitiesFrom.getGenes().size(); index++) {
            findAndAddMissingCity(routeToPickCitiesFrom, offspring, index + 1);
        }
    }

    private void findAndAddMissingCity(Route routeToPickCitiesFrom, List<City> offspring, int index) {
        routeToPickCitiesFrom.getGenes()
                .stream()
                .filter(city ->
                        !offspring.contains(city))
                .findFirst()
                .ifPresent(missingCityFromOffSpring -> offspring.add(index, missingCityFromOffSpring));

    }

    private void addCitiesBeforeCuttingIndex(List<City> offSpring, int firstCuttingIndex, Route routeToPickCitiesFrom) {
        for (int index = 0; index < firstCuttingIndex; index++) {
            findAndAddMissingCity(routeToPickCitiesFrom, offSpring, index);
        }
    }
}
