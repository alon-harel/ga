package com.harel.ga.tsp;

import com.harel.ga.Chromosome;
import com.harel.ga.GaAlgorithm;
import com.harel.ga.crossover.CrossoverService;
import com.harel.ga.selection.BestOutOfRandomNChromosomesSelection;
import com.harel.ga.selection.SelectionMethodology;
import com.harel.ga.tsp.graph.GraphPresentation;
import com.harel.ga.utils.RandomNumberProvider;
import com.harel.ga.utils.RandomNumberProviderImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TspApplication {

    public static void main(String[] args) {

        final RandomNumberProvider randomNumberProvider = new RandomNumberProviderImpl();
        final TspProblemDefinition problemDefinition = new TspProblemDefinition(randomNumberProvider);
        problemDefinition.init(20);
        final TspChromosomeFactory chromosomeFactory = new TspChromosomeFactory(randomNumberProvider, new ArrayList<>(problemDefinition.getCitiesToTravel()));
        final TspFitnessScoreCalculator fitnessScoreCalculator = new TspFitnessScoreCalculator();
        //final SelectionMethodology selectionMethodology = new RouletteWheelSelection(randomNumberProvider);
        final SelectionMethodology selectionMethodology =
                new BestOutOfRandomNChromosomesSelection(5, randomNumberProvider);
        final CrossoverService crossoverService = new TspCrossoverService(chromosomeFactory, randomNumberProvider, 0.7);
        final TspMutationService mutationService = new TspMutationService(randomNumberProvider);
        int generationsCount = 100;
        int populationSize = 100;

        GaAlgorithm gaAlgorithm = new GaAlgorithm(
                chromosomeFactory,
                fitnessScoreCalculator,
                selectionMethodology,
                crossoverService,
                mutationService,
                generationsCount,
                populationSize);

        final List<Chromosome> fittestChromosomes = gaAlgorithm.run();

        log.info("\n\n\n");
        log.info("*************************************");
        final double firstGenerationBestDistance = ((Route) fittestChromosomes.get(0)).getDistance();
        log.info("First generation best distance: " + firstGenerationBestDistance);
        final double lastGenerationBestDistance = ((Route) fittestChromosomes.get(fittestChromosomes.size() - 1)).getDistance();
        log.info("Last generation best distance: " + lastGenerationBestDistance);
        double improvement = 1 - (lastGenerationBestDistance / firstGenerationBestDistance);
        log.info("Improvement rate: " + improvement);

       // new GraphPresentation().buildGraph(fittestChromosomes.get(0), "rgb(255,0,0)");
     //   new GraphPresentation().buildGraph(fittestChromosomes.get(fittestChromosomes.size() - 1), "rgb(0,100,255)");
    }
}
