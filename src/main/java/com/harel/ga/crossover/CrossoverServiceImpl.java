package com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import com.harel.ga.ChromosomeFactory;
import com.harel.ga.utils.RandomNumberProvider;
import lombok.Getter;

@Getter
public abstract class CrossoverServiceImpl<C extends Chromosome> implements CrossoverService<C> {
    private final ChromosomeFactory chromosomeFactory;
    private final RandomNumberProvider randomNumberProvider;
    private final double crossoverRate;

    public CrossoverServiceImpl(ChromosomeFactory chromosomeFactory,
                                RandomNumberProvider randomNumberProvider,
                                double crossoverRate) {
        this.chromosomeFactory = chromosomeFactory;
        this.randomNumberProvider = randomNumberProvider;
        this.crossoverRate = crossoverRate;
    }

    @Override
    public CrossoverService.Offsprings perform(C firstChromosome, C secondChromosome) {
        CrossoverService.Offsprings offsprings;
        if (randomNumberProvider.draw() < crossoverRate) {
            offsprings = createOffsprings(firstChromosome, secondChromosome);
        }
        else {
            offsprings = CrossoverService.Offsprings.builder()
                    .firstOffspring(chromosomeFactory.create(firstChromosome.getGenes()))
                    .secondOffspring(chromosomeFactory.create(secondChromosome.getGenes()))
                    .build();
        }

        return offsprings;
    }

    protected abstract Offsprings createOffsprings(C firstChromosome, C secondChromosome);


}
