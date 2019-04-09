package com.harel.ga.tuturial;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;

import static com.harel.ga.tuturial.TutorialGene.*;
import static com.harel.ga.tuturial.TutorialGene.Type.*;


@Slf4j
public class TutorialChromosomeValueCalculator {

    public double calc(List<TutorialGene> genes) {
        double result = 0;
        final Iterator<TutorialGene> iterator = genes.iterator();
        final TutorialGene numberGene = iterateToFindGeneType(iterator, NUMBER);
        if (numberGene != null) {
            result = numberGene.getIndex();
            while (iterator.hasNext()) {
                result = applyOperator(iterator, result);
            }
        }

        return result;
    }

    private TutorialGene iterateToFindGeneType(Iterator<TutorialGene> iterator, Type type) {
        TutorialGene gene = null;
        if (iterator.hasNext()) {
            do {
                gene = iterator.next();
            }
            while (iterator.hasNext() && gene.getType() != type);

            gene = (gene.getType() == type) ? gene : null;
        }

        return gene;
    }

    private double applyOperator(Iterator<TutorialGene> iterator, double result) {
        final TutorialGene operatorGene = iterateToFindGeneType(iterator, OPERATOR);
        if (operatorGene != null) {
            final TutorialGene numberGene = iterateToFindGeneType(iterator, NUMBER);
            if (numberGene != null) {
                result = applyOperatorWithFollowingNumber(result, operatorGene, numberGene);
            }
        }
        return result;
    }

    private double applyOperatorWithFollowingNumber(double result, TutorialGene operatorGene, TutorialGene numberGene) {
        if (operatorGene == PLUS) {
            result += numberGene.getIndex();
        }
        else if (operatorGene == MINUS) {
            result -= numberGene.getIndex();
        }
        else if (operatorGene == MULTIPLE) {
            result *= numberGene.getIndex();
        }
        else if (operatorGene == DIV) {
            if (numberGene.getIndex() != 0) {
                result /= numberGene.getIndex();
            }
        }
        return result;
    }
}
