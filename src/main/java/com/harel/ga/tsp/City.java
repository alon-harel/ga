package com.harel.ga.tsp;

import com.harel.ga.Gene;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class City implements Gene {
    private final int xCoordinate;
    private final int yCoordinate;

    @Override
    public List<Integer> getBits() {
        return null;
    }

    @Override
    public Gene duplicateWithMutationAt(int bitIndex) {
        return null;
    }

    public double distanceFrom(City otherCity) {
        return Math.hypot(xCoordinate - otherCity.getXCoordinate(),
                yCoordinate - otherCity.getXCoordinate());
    }
}
