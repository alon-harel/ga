package com.harel.ga.tsp;

import com.harel.ga.ChromosomeImpl;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Route extends ChromosomeImpl<City> {
    private double distance;
    private final List<City> cities = new ArrayList<>();

    private Route(List<City> cities) {
        this.cities.addAll(cities);
    }

    public static Route from(List<City> cities) {
        return new Route(cities);
    }

    @Override
    public List<City> getGenes() {
        return cities;
    }

    public void addCity(City city) {
        cities.add(city);
    }
}
