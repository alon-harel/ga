package com.harel.ga.tuturial;

import com.harel.ga.ChromosomeFactory;
import com.harel.ga.tuturial.TutorialChromosome;
import com.harel.ga.tuturial.TutorialGene;
import com.harel.ga.utils.RandomNumberProvider;

import java.util.ArrayList;
import java.util.List;

public class TutorialChromosomeFactory implements ChromosomeFactory<TutorialChromosome, TutorialGene> {
    private final RandomNumberProvider randomNumberProvider;

    public TutorialChromosomeFactory(RandomNumberProvider randomNumberProvider) {
        this.randomNumberProvider = randomNumberProvider;
    }

    @Override
    public TutorialChromosome create() {
        List<TutorialGene> genes = new ArrayList<>();
        for (int index = 0; index < 9; index++) {
            genes.add(TutorialGene.from(randomNumberProvider.random(16)));
        }

        return create(genes);
    }

    @Override
    public TutorialChromosome create(List<TutorialGene> genes) {
        return TutorialChromosome.from(genes);
    }
}
