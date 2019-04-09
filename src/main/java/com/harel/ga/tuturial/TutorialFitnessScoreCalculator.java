package com.harel.ga.tuturial;

import com.harel.ga.FitnessScoreCalculatorImpl;
import com.harel.ga.tuturial.utils.TutorialProblemDefinition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TutorialFitnessScoreCalculator extends FitnessScoreCalculatorImpl<TutorialChromosome> {

    private final TutorialChromosomeValueCalculator chromosomeValueCalculator =
            new TutorialChromosomeValueCalculator();
    private final TutorialProblemDefinition problemDefinition;

    public TutorialFitnessScoreCalculator(TutorialProblemDefinition problemDefinition) {
        this.problemDefinition = problemDefinition;
    }

    @Override
    protected void calc(TutorialChromosome chromosome) {
        final double value = chromosomeValueCalculator.calc(chromosome.getGenes());
        chromosome.setFitnessScore(calcFitness(value));
    }

    private double calcFitness(double value) {
        final double distance = Math.abs(value - problemDefinition.getTarget());
        return distance != 0 ? (problemDefinition.getTarget() / distance) : 100000.0;
    }
}
