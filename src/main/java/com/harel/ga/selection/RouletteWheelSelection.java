package com.harel.ga.selection;

import com.harel.ga.Chromosome;
import com.harel.ga.utils.RandomNumberProvider;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class RouletteWheelSelection implements SelectionMethodology {
    private final RandomNumberProvider randomNumberProvider;
    private List<Chromosome> chromosomes = new ArrayList<>();
    private double sumFitness = 0.0;

    public RouletteWheelSelection(RandomNumberProvider randomNumberProvider) {
        this.randomNumberProvider = randomNumberProvider;
    }

    @Override
    public void init(List<Chromosome> chromosomes) {
        this.chromosomes.clear();
        this.chromosomes = chromosomes;
        this.sumFitness = calcSumFitness(chromosomes);
    }

    private double calcSumFitness(List<Chromosome> chromosomes) {
        double sumFitness = 0;
        for (Chromosome chromosome : chromosomes) {
            sumFitness += chromosome.getFitnessScore();
            
        }
        return sumFitness;
    }

    @Override
    public Chromosome pick() {
        Chromosome pickedChromosome = null;
        double drawedProbability = randomNumberProvider.draw();

        double sumProbability = 0;
        for (Chromosome chromosome : chromosomes) {
            final double currentProbabiliaty = chromosome.getFitnessScore() / sumFitness;
            if (currentProbabiliaty + sumProbability >= drawedProbability) {
                pickedChromosome = chromosome;
                break;
            }
            sumProbability += currentProbabiliaty;
        }
        if (pickedChromosome == null) {
            log.error("sumFitness: " + sumFitness + ", drawedProbability:" + drawedProbability + ", sumProbability: " + sumProbability + "\n" + "chromosomes are: " + chromosomes);
        }

        return pickedChromosome;
    }
}
