package com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface CrossoverService<C extends Chromosome> {

    CrossoverService.Offsprings perform(C firstChromosome, C secondChromosome);

    @Getter
    @ToString
    @EqualsAndHashCode
    @Builder
    class Offsprings {
        private Chromosome firstOffspring;
        private Chromosome secondOffspring;
    }
}
