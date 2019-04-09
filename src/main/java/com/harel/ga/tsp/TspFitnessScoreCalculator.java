package com.harel.ga.tsp;

import com.harel.ga.FitnessScoreCalculatorImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TspFitnessScoreCalculator extends FitnessScoreCalculatorImpl<Route> {

    protected void calc(Route route) {
        double distance = calcDistance(route);
        route.setDistance(distance);
        route.setFitnessScore(1 / distance);
    }

    private double calcDistance(Route route) {
        double distance = 0.0;

        for (int index = 0; index < route.getGenes().size() - 1; index++) {
            City currentCity = route.getGenes().get(index);
            City nextCity = route.getGenes().get(index + 1);
            distance += nextCity.distanceFrom(currentCity);
        }

        return distance;
    }
}
