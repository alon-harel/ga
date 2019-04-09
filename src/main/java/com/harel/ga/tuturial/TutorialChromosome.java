package com.harel.ga.tuturial;

import com.harel.ga.Chromosome;
import com.harel.ga.ChromosomeImpl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TutorialChromosome extends ChromosomeImpl<TutorialGene> {
    private final List<TutorialGene> genes = new ArrayList<>();

    private TutorialChromosome(List<TutorialGene> genes) {
        this.genes.addAll(genes);
    }

    public static TutorialChromosome from(List<TutorialGene> genes) {
        if (genes.size() != 9) {
            throw new ChromosomeCreationFailed(genes.size());
        }
        return new TutorialChromosome(genes);
    }


    public static class ChromosomeCreationFailed extends RuntimeException {
        ChromosomeCreationFailed(int geneCount) {
            super(String.format("Tutorial chromosome should have exactly 9 genes. Received %d genes.", geneCount));
        }
    }
}
