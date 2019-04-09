package com.harel.ga.mutation;

import com.harel.ga.Chromosome;
import com.harel.ga.Gene;
import com.harel.ga.utils.RandomNumberProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ClassicMutationService<C extends Chromosome> implements MutationService<C> {
    private final RandomNumberProvider randomNumberProvider;
    private final double mutationProbability;

    public ClassicMutationService(RandomNumberProvider randomNumberProvider, double mutationProbability) {
        this.randomNumberProvider = randomNumberProvider;
        this.mutationProbability = mutationProbability;
    }

    public Chromosome mutate(C chromosome) {
        for (int geneIndex = 0; geneIndex < chromosome.getGenes().size(); geneIndex++) {
            Gene gene = (Gene) chromosome.getGenes().get(geneIndex);
            List<Integer> bits = gene.getBits();

            for (int bitIndex = 0; bitIndex < bits.size(); bitIndex++) {
                if (performMutation()) {
                    //log.info("Mutation occurred.");
                    Gene mutedGene = gene.duplicateWithMutationAt(bitIndex);
                    //log.info(String.format("Orig gene: %s Mutated gene: %s.", gene.toString(), mutedGene.toString()));
                    chromosome.getGenes().set(geneIndex, mutedGene);
                }
            }
        }
        return chromosome;
    }

    private boolean performMutation() {
        int rand = randomNumberProvider.random(1000);
        return rand * 0.001 < mutationProbability;
    }
}
