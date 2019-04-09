package com.harel.ga;

import com.harel.ga.crossover.CrossoverService;
import com.harel.ga.mutation.MutationService;
import com.harel.ga.selection.SelectionMethodology;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class GaAlgorithm {
    private final ChromosomeFactory chromosomeFactory;
    private final FitnessScoreCalculator fitnessScoreCalculator;
    private final SelectionMethodology selectionMethodology;
    private final CrossoverService crossoverService;
    private final MutationService mutationService;
    private final int generationsCount;
    private final int populationSize;

    public GaAlgorithm(ChromosomeFactory chromosomeFactory,
                       FitnessScoreCalculator fitnessScoreCalculator,
                       SelectionMethodology selectionMethodology,
                       CrossoverService crossoverService,
                       MutationService mutationService,
                       int generationsCount,
                       int populationSize) {
        this.chromosomeFactory = chromosomeFactory;
        this.fitnessScoreCalculator = fitnessScoreCalculator;
        this.selectionMethodology = selectionMethodology;
        this.crossoverService = crossoverService;
        this.mutationService = mutationService;
        this.generationsCount = generationsCount;
        this.populationSize = populationSize;
    }

    public List<Chromosome> run() {
        Population population = Population.init(populationSize, chromosomeFactory);

        List<Chromosome> bestChromosomeInGenerationHistory = new ArrayList<>();
        for (int index = 0; index < generationsCount; index++) {
            log.info("Generation number: " + (index + 1));
            fitnessScoreCalculator.calc(population.getChromosomes());
            bestChromosomeInGenerationHistory.add(getFittestChromosome(population));
            population = buildNextGeneration(population);
        }

        return bestChromosomeInGenerationHistory;
    }

    private Chromosome getFittestChromosome(Population population) {
       return population.getChromosomes()
                .stream()
                .max(Comparator.comparing(Chromosome::getFitnessScore))
                .orElseThrow(NoSuchElementException::new);
    }

    private Population buildNextGeneration(Population population) {
        Population nextGeneration = Population.emptyPopulation();

        selectionMethodology.init(population.getChromosomes());
        for (int index = 0; index < population.getChromosomes().size() / 2; index++) {
            Chromosome firstCandidate = selectionMethodology.pick();
            Chromosome secondCandidate = selectionMethodology.pick();

            addOffspringToNextGeneration(firstCandidate, secondCandidate, nextGeneration);
        }

        return nextGeneration;
    }

    private void addOffspringToNextGeneration(Chromosome firstCandidate,
                                              Chromosome secondCandidate,
                                              Population nextGeneration) {
        CrossoverService.Offsprings offsprings = crossoverService.perform(firstCandidate, secondCandidate);
        offsprings = mutate(offsprings);

        nextGeneration.addChromosome(offsprings.getFirstOffspring());
        nextGeneration.addChromosome(offsprings.getSecondOffspring());
    }

    private CrossoverService.Offsprings mutate(CrossoverService.Offsprings offsprings) {
        Chromosome firstChromosome = mutationService.mutate(offsprings.getFirstOffspring());
        Chromosome secondChromosome = mutationService.mutate(offsprings.getSecondOffspring());

        return CrossoverService.Offsprings.builder()
                .firstOffspring(firstChromosome)
                .secondOffspring(secondChromosome)
                .build();
    }
}
