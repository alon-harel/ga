package com.harel.ga.tsp;

import com.harel.ga.ProblemDefinition;
import com.harel.ga.utils.RandomNumberProvider;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class TspProblemDefinition implements ProblemDefinition {
    public static final int MAX_COORDINATE = 201;
    private final Set<City> citiesToTravel = new HashSet<>();
    private final RandomNumberProvider randomNumberProvider;

    public TspProblemDefinition(RandomNumberProvider randomNumberProvider) {
        this.randomNumberProvider = randomNumberProvider;
    }

    public void init(int citiesNumber) {
        for (int index = 0; index < citiesNumber; index++) {
            City newCity;
            do {
                newCity = City.builder()
                        .xCoordinate(randomNumberProvider.random(MAX_COORDINATE))
                        .yCoordinate(randomNumberProvider.random(MAX_COORDINATE))
                        .build();
            }
            while (citiesToTravel.contains(newCity));
            citiesToTravel.add(newCity);
        }
    }
}
