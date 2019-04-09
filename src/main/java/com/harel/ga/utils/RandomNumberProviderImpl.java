package com.harel.ga.utils;

import java.util.Random;

public class RandomNumberProviderImpl implements RandomNumberProvider {
    private static final Random RAND = new Random();

    @Override
    public int random(int drawUntil) {
        return RAND.nextInt(drawUntil);
    }

    @Override
    public double draw() {
        return RAND.nextDouble();
    }

}
