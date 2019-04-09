package com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import com.harel.ga.ChromosomeFactory;
import com.harel.ga.Gene;
import com.harel.ga.utils.RandomNumberProvider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassicCrossover<C extends Chromosome> extends CrossoverServiceImpl<C> {
    public ClassicCrossover(ChromosomeFactory chromosomeFactory, RandomNumberProvider randomNumberProvider, double crossoverRate) {
        super(chromosomeFactory, randomNumberProvider, crossoverRate);
    }

    protected CrossoverService.Offsprings createOffsprings(C firstChromosome, C secondChromosome) {
        CrossoverService.Offsprings offsprings;
        final int genesCount = firstChromosome.getGenes().size();
        int indexInChromosome = getRandomNumberProvider().random(genesCount + 1);

        Chromosome firstOffspring = createOffSpringFrom(firstChromosome, secondChromosome, indexInChromosome);
        Chromosome secondOffspring = createOffSpringFrom(secondChromosome, firstChromosome, indexInChromosome);

        offsprings = CrossoverService.Offsprings.builder().firstOffspring(firstOffspring).secondOffspring(secondOffspring).build();
        return offsprings;
    }

    private Chromosome createOffSpringFrom(C chromosome1, C chromosome2, int indexInChromosome) {
        final int genesCount = chromosome2.getGenes().size();

        final List<Gene> genesFromFirstChromosome = (List<Gene>) chromosome1.getGenes().subList(0, indexInChromosome);
        final List<Gene> genesFromSecondChromosome = (List<Gene>) chromosome2.getGenes().subList(indexInChromosome, genesCount);
        List<Gene> offspringGenes = Stream.concat(genesFromFirstChromosome.stream(), genesFromSecondChromosome.stream())
                .collect(Collectors.toList());

        return getChromosomeFactory().create(offspringGenes);
    }
}
