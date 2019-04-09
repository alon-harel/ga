package com.harel.ga.tsp;

import com.harel.ga.ChromosomeFactory;
import com.harel.ga.utils.RandomNumberProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TspChromosomeFactory implements ChromosomeFactory<Route, City> {
    private final RandomNumberProvider randomNumberProvider;
    private final List<City> cities;

    public TspChromosomeFactory(RandomNumberProvider randomNumberProvider, List<City> cities) {
        this.randomNumberProvider = randomNumberProvider;
        this.cities = cities;
    }

    @Override
    public Route create() {
        Route route = new Route();

        List<City> citiesLeft = new ArrayList<>(cities);
        createFrom(route, citiesLeft);

        return route;
    }

    private void createFrom(Route route, List<City> citiesLeft) {
        while (!citiesLeft.isEmpty()) {
            int pickedCityIndex = 0;
            if (citiesLeft.size() > 1) {
                pickedCityIndex = randomNumberProvider.random(citiesLeft.size());
            }
            route.addCity(citiesLeft.get(pickedCityIndex));
            citiesLeft.remove(pickedCityIndex);
            createFrom(route, citiesLeft);
        }
    }

    @Override
    public Route create(List<City> cities) {
        validate(cities);
        return Route.from(cities);
    }

    private void validate(List<City> cities) {
        if (!cities.stream().sequential().allMatch(new HashSet<>()::add)) {
            throw new DuplicateCitiesInRouteException(cities);
        }
        if (cities.size() != this.cities.size()) {
            throw new MissingCitiesException(cities);
        }
    }

    public class RouteCreationException extends RuntimeException {
        RouteCreationException(String message) {
            super(message);
        }
    }

    public class DuplicateCitiesInRouteException extends RouteCreationException {
        private DuplicateCitiesInRouteException(List<City> cities) {
            super(String.format("The route to create %s has duplicate cities in it.", cities.toString()));
        }
    }

    public class MissingCitiesException extends RouteCreationException {
        MissingCitiesException(List<City> cities) {
            super(String.format("The route to create %s does not contain all cities in it.", cities.toString()));
        }
    }
}
