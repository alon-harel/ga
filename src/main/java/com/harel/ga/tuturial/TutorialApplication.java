package com.harel.ga.tuturial;

import com.harel.ga.*;
import com.harel.ga.crossover.ClassicCrossover;
import com.harel.ga.crossover.CrossoverServiceImpl;
import com.harel.ga.mutation.ClassicMutationService;
import com.harel.ga.selection.RouletteWheelSelection;
import com.harel.ga.selection.SelectionMethodology;
import com.harel.ga.tuturial.utils.TutorialProblemDefinition;
import com.harel.ga.utils.RandomNumberProviderImpl;

public class TutorialApplication {

    public static void main(String[] args) {
        final RandomNumberProviderImpl randomNumberProvider = new RandomNumberProviderImpl();
        final TutorialChromosomeFactory chromosomeFactory = new TutorialChromosomeFactory(randomNumberProvider);
        final TutorialProblemDefinition problemDefinition = new TutorialProblemDefinition(1000);
        final FitnessScoreCalculator fitnessScoreCalculator = new TutorialFitnessScoreCalculator(problemDefinition);
        final SelectionMethodology selectionMethodology = new RouletteWheelSelection(randomNumberProvider);
        final CrossoverServiceImpl crossoverService = new ClassicCrossover<TutorialChromosome>(chromosomeFactory, randomNumberProvider, 0.7);
        final ClassicMutationService mutationService = new ClassicMutationService<TutorialChromosome>(randomNumberProvider, 0.001);

        GaAlgorithm gaAlgorithm = new GaAlgorithm(
                chromosomeFactory,
                fitnessScoreCalculator,
                selectionMethodology,
                crossoverService,
                mutationService,
                100,
                100);

        gaAlgorithm.run();
    }
}
